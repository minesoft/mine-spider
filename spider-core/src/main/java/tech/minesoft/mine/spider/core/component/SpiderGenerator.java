package tech.minesoft.mine.spider.core.component;

import tech.minesoft.mine.spider.core.vo.SpiderRequest;

import java.util.List;

public interface SpiderGenerator {

    List<SpiderRequest> generate(String spiderName);

}
