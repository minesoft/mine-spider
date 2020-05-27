package cn.sixlab.minesoft.spider.core;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class SpiderPool {
    private ExecutorService executorService;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int poolSize = 1;
    private int activeSize = 0;

    private SpiderPool(int poolSize) {
        this.poolSize = poolSize;
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public static SpiderPool build(int poolSize){
        return new SpiderPool(poolSize);
    }

    public void execute(Runnable runnable) throws NullPointerException {
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
                runnable.run();
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

    public boolean isShutdown() {
        return executorService.isShutdown();
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
