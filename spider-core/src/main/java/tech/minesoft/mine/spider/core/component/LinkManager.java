package tech.minesoft.mine.spider.core.component;

import tech.minesoft.mine.spider.core.vo.SpiderRequest;

import java.util.List;

public interface LinkManager {

    void addLinks(SpiderRequest... requests);

    void addLinks(List<SpiderRequest> requests);

    SpiderRequest nextRequest();

}
