package cn.sixlab.minesoft.spider.core;

import cn.sixlab.minesoft.spider.core.utils.Status;
import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class SpiderPool implements Runnable{
    private ExecutorService executorService;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private SpiderTask task;

    private int poolSize = 1;
    private int activeSize = 0;
    private Status status = Status.INIT;

    private SpiderPool(SpiderTask task, int poolSize) {
        this.task = task;
        this.poolSize = poolSize;
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.status = Status.WAITING;
    }

    public static SpiderPool build(SpiderTask task, int poolSize) {
        return new SpiderPool(task, poolSize);
    }

    public boolean isShutdown() {
        return executorService.isShutdown();
    }

    public void shutdown() {
        status = Status.WAITING;
        executorService.shutdown();
    }

    @Override
    public void run() {
        status = Status.RUNNING;

        while (status.equals(Status.RUNNING)) {
            if (activeSize >= poolSize) {
                try {
                    lock.lock();
                    while (activeSize >= poolSize) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
            activeSize++;
            executorService.execute(() -> {
                try {
                    task.run();
                } finally {
                    try {
                        lock.lock();
                        activeSize--;
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }
    }
}
