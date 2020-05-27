package cn.sixlab.minesoft.spider.core.component;

import cn.sixlab.minesoft.spider.core.vo.SpiderRequest;

import java.util.List;

public interface LinkManager {

    void addLinks(SpiderRequest... requests);

    void addLinks(List<SpiderRequest> requests);

    SpiderRequest nextRequest();

}
