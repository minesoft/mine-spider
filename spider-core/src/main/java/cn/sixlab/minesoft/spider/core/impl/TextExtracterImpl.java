package cn.sixlab.minesoft.spider.core.impl;

import cn.sixlab.minesoft.spider.core.component.Extracter;
import cn.sixlab.minesoft.spider.core.utils.Content;
import cn.sixlab.minesoft.spider.core.utils.HttpUtils;
import cn.sixlab.minesoft.spider.core.vo.SpiderResponse;

public class TextExtracterImpl implements Extracter {

    @Override
    public Content extract(SpiderResponse response) {

        String text = HttpUtils.parseResponse(response.getResponse());

        Content content = new Content(response.getSpiderName(), response.getUrl());

        content.add("result", text);

        return content;
    }

}
