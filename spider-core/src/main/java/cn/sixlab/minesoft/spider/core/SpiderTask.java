package cn.sixlab.minesoft.spider.core;

import cn.sixlab.minesoft.spider.core.component.*;
import cn.sixlab.minesoft.spider.core.impl.*;
import cn.sixlab.minesoft.spider.core.utils.Content;
import cn.sixlab.minesoft.spider.core.vo.SpiderRequest;
import cn.sixlab.minesoft.spider.core.vo.SpiderResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Data
public class SpiderTask {

    private String spiderName;

    private LinkGenerator generator;

    private Downloader downloader = new DownloaderImpl(UUID.randomUUID().toString());
    private Extracter extracter = new TextExtracterImpl();
    private Saver saver = new SaverImpl();

    private LinkManager linkManager = new LinkManagerImpl();
    private PropsManager propsManager = new PropsManagerImpl();
    private int waitingSeconds = 60;

    private SpiderTask() {
    }

    public SpiderTask(String spiderName, LinkGenerator generator) {
        this.spiderName = spiderName;
        this.generator = generator;
    }

    public void run() {
        SpiderRequest request = linkManager.nextRequest();
        if (request == null) {
            linkManager.addLinks(generator.generate());
            request = linkManager.nextRequest();
        }

        if (null != request) {
            SpiderResponse response = downloader.download(request);

            if (null != response) {
                Content content = extracter.extract(response);

                if (null != content) {
                    if (null != content.getRequestList()) {
                        linkManager.addLinks(content.getRequestList());
                    }

                    saver.saveContent(content);
                }
            }
        }
        try {
            Thread.sleep(waitingSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Spider build() {
        return Spider.build(this, 1);
    }

    public Spider build(int threadNum) {
        return Spider.build(this, threadNum);
    }
}
