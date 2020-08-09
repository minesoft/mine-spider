package tech.minesoft.mine.spider.core.component;


import tech.minesoft.mine.spider.core.utils.Content;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;

public interface SpiderExtractor {

    Content extract(SpiderRequest request, SpiderResponse response);

}
