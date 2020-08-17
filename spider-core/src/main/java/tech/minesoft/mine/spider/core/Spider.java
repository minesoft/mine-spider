package tech.minesoft.mine.spider.core;

import lombok.extern.slf4j.Slf4j;
import tech.minesoft.mine.spider.core.component.*;
import tech.minesoft.mine.spider.core.impl.DownloaderImpl;
import tech.minesoft.mine.spider.core.impl.LinkManagerImpl;
import tech.minesoft.mine.spider.core.utils.Content;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;

import java.util.List;
import java.util.UUID;

@Slf4j
public class Spider {
    public static class SpiderBuilder {
        private final Spider spider;
        public SpiderBuilder(Spider spider) {
            this.spider = spider;
        }

        public SpiderBuilder setSpiderName(String spiderName) {
            spider.spiderName = spiderName;
            return this;
        }

        public SpiderBuilder setGenerator(SpiderGenerator generator) {
            spider.generator = generator;
            return this;
        }

        public SpiderBuilder setExtractor(SpiderExtractor extractor) {
            spider.extractor = extractor;
            return this;
        }

        public SpiderBuilder setSaver(SpiderSaver saver) {
            spider.saver = saver;
            return this;
        }

        public SpiderBuilder setManager(LinkManager manager) {
            spider.manager = manager;
            return this;
        }

        public SpiderBuilder setDownloader(Downloader downloader) {
            spider.downloader = downloader;
            return this;
        }

        public Spider build(){
            if (spider.spiderName == null) {
                spider.spiderName = UUID.randomUUID().toString();
            }
            if (spider.generator == null) {
                throw new RuntimeException("链接生成器未设置");
            }
            if (spider.extractor == null) {
                throw new RuntimeException("内容提取器未设置");
            }
            if (spider.saver == null) {
                throw new RuntimeException("内容保存器未设置");
            }
            if (spider.manager == null) {
                spider.manager = new LinkManagerImpl();
            }
            if (spider.downloader == null) {
                spider.downloader = new DownloaderImpl();
            }

            return spider;
        }
    }

    public static SpiderBuilder builder(){
        return new SpiderBuilder(new Spider());
    }

    public String spiderName;
    // 需要自己实现
    private SpiderGenerator generator;
    private SpiderExtractor extractor;
    private SpiderSaver saver;

    // 内置推荐自己实现
    private LinkManager manager;

    // 纯内置
    private Downloader downloader;

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }

    public void setGenerator(SpiderGenerator generator) {
        this.generator = generator;
    }

    public void setExtractor(SpiderExtractor extractor) {
        this.extractor = extractor;
    }

    public void setSaver(SpiderSaver saver) {
        this.saver = saver;
    }

    public void linkTask(){
        List<SpiderRequest> links = generator.generate(spiderName);

        manager.addLinks(links);
    }

    public void crawTask(){
        log.info("爬虫开始:"+spiderName);
        SpiderRequest request = manager.nextRequest();

        if(null!=request){
            log.info("爬虫开始爬取:"+spiderName);
            SpiderResponse response = downloader.download(request);

            if(null!=request){
                log.info("爬虫爬取结果开始处理:"+spiderName);
                Content content = extractor.extract(request, response);

                if(null!=content){
                    log.info("爬虫结果保存:"+spiderName);
                    saver.saveContent(content);

                    manager.addLinks(content.getRequestList());
                }
            }
        }
        log.info("爬虫完成:"+spiderName);
    }

    public void addLink(SpiderRequest... requests){
        manager.addLinks(requests);
    }
}
