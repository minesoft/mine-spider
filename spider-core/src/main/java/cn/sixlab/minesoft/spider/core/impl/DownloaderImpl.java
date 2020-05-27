package cn.sixlab.minesoft.spider.core.impl;

import cn.sixlab.minesoft.spider.core.component.Downloader;
import cn.sixlab.minesoft.spider.core.vo.SpiderRequest;
import cn.sixlab.minesoft.spider.core.vo.SpiderResponse;
import cn.sixlab.minesoft.spider.core.utils.HttpUtils;

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
