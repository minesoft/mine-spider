package tech.minesoft.mine.spider.impl.extracter;

import tech.minesoft.mine.spider.core.component.SpiderExtractor;
import tech.minesoft.mine.spider.core.utils.Content;
import tech.minesoft.mine.spider.core.utils.HttpUtils;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;

public class HtmlExtracterImpl implements SpiderExtractor {

    @Override
    public Content extract(SpiderRequest request, SpiderResponse response) {

        String html = HttpUtils.parseResponse(response.getResponse());

        Content content = new Content(request);

        if (null != html && !"".equals(html)) {
            //    1
        }

        return content;
    }

}
