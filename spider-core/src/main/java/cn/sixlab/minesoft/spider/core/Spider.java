package cn.sixlab.minesoft.spider.core;

import cn.sixlab.minesoft.spider.core.component.LinkGenerator;
import cn.sixlab.minesoft.spider.core.component.LinkManager;
import cn.sixlab.minesoft.spider.core.utils.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Spider {

    private Status status = Status.INIT;
    private int threadNum = 1;

    private SpiderTask task;
    private SpiderPool pool;

    public void run() {
        if (canRun()) {
            task.runOnce();
        }
    }

    public void start() {
        pool = SpiderPool.build(threadNum);
        pool.execute(task);
    }

    public void stop() {
        task.stop();
        pool.shutdown();
        this.status = Status.WAITING;
    }

    private boolean canRun() {
        return Status.WAITING.equals(status);
    }

    public static Spider build(SpiderTask task) {
        return build(task, 1);
    }

    public static Spider build(SpiderTask task, int threadNum) {
        Spider spider = new Spider();
        spider.task = task;
        spider.threadNum = threadNum;
        spider.status = Status.WAITING;

        LinkManager linkManager = spider.task.getLinkManager();
        LinkGenerator generator = spider.task.getGenerator();
        linkManager.addLinks(generator.init());

        return spider;
    }

}
