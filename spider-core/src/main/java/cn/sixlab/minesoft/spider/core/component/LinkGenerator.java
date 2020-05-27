package cn.sixlab.minesoft.spider.core.component;

import cn.sixlab.minesoft.spider.core.vo.SpiderRequest;

import java.util.List;

public interface LinkGenerator {

    List<SpiderRequest> init();

    List<SpiderRequest> generate();

}
