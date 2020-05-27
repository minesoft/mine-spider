package cn.sixlab.minesoft.spider.impl.extracter;

import cn.sixlab.minesoft.spider.core.component.Extracter;
import cn.sixlab.minesoft.spider.core.utils.Content;
import cn.sixlab.minesoft.spider.core.vo.SpiderResponse;
import cn.sixlab.minesoft.spider.core.utils.HttpUtils;
import cn.sixlab.minesoft.spider.core.utils.JsonUtils;

import java.util.Map;

public class JsonExtracterImpl implements Extracter {

    @Override
    public Content extract(SpiderResponse response) {

        String json = HttpUtils.parseResponse(response.getResponse());

        Content content = new Content(response.getSpiderName(), response.getUrl());

        if (null != json && !"".equals(json)) {
            Map map = JsonUtils.toBean(json, Map.class);
            content.addAll(map);
        }

        return content;
    }

}
