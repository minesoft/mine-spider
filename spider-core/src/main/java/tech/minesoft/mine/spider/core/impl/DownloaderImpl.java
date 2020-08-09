package tech.minesoft.mine.spider.core.impl;

import tech.minesoft.mine.spider.core.component.Downloader;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;
import tech.minesoft.mine.spider.core.utils.HttpUtils;

public class DownloaderImpl implements Downloader {
    private final String spiderName;

    public DownloaderImpl(String spiderName) {
        this.spiderName = spiderName;
    }

    @Override
    public SpiderResponse download(SpiderRequest request) {
        return HttpUtils.request(request.getRequest(), spiderName);
    }

}
