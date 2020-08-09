package tech.minesoft.mine.spider.impl.extracter;

import tech.minesoft.mine.spider.core.component.SpiderExtractor;
import tech.minesoft.mine.spider.core.utils.Content;
import tech.minesoft.mine.spider.core.utils.HttpUtils;
import tech.minesoft.mine.spider.core.utils.JsonUtils;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;

import java.util.Map;

public class JsonExtracterImpl implements SpiderExtractor {

    @Override
    public Content extract(SpiderRequest request, SpiderResponse response) {

        String json = HttpUtils.parseResponse(response.getResponse());

        Content content = new Content(request);

        if (null != json && !"".equals(json)) {
            Map map = JsonUtils.toBean(json, Map.class);
            content.result.putAll(map);
        }

        return content;
    }

}
