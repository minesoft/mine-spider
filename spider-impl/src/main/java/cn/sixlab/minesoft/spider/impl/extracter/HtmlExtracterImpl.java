package cn.sixlab.minesoft.spider.impl.extracter;

import cn.sixlab.minesoft.spider.core.component.Extracter;
import cn.sixlab.minesoft.spider.core.utils.Content;
import cn.sixlab.minesoft.spider.core.utils.HttpUtils;
import cn.sixlab.minesoft.spider.core.vo.SpiderResponse;

public class HtmlExtracterImpl implements Extracter {

    @Override
    public Content extract(SpiderResponse response) {

        String html = HttpUtils.parseResponse(response.getResponse());

        Content content = new Content(response.getSpiderName(), response.getUrl());

        if (null != html && !"".equals(html)) {
        //    1
        }

        return content;
    }

}
