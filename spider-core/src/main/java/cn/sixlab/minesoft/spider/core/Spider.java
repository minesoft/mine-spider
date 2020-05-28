package cn.sixlab.minesoft.spider.core;

import cn.sixlab.minesoft.spider.core.component.LinkGenerator;
import cn.sixlab.minesoft.spider.core.component.LinkManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Spider {

    private int threadNum = 1;

    private SpiderTask task;
    private SpiderPool pool;

    public void start() {
        pool = SpiderPool.build(task, threadNum);

        Thread thread = new Thread(pool);
        thread.setDaemon(false);
        thread.start();
    }

    public void stop() {
        pool.shutdown();
    }

    public static Spider build(SpiderTask task) {
        return build(task, 1);
    }

    public static Spider build(SpiderTask task, int threadNum) {
        Spider spider = new Spider();
        spider.task = task;
        spider.threadNum = threadNum;

        LinkManager linkManager = spider.task.getLinkManager();
        LinkGenerator generator = spider.task.getGenerator();
        linkManager.addLinks(generator.init());

        return spider;
    }

}
