package cn.sixlab.minesoft.spider.core.vo;

import lombok.Data;
import okhttp3.Response;

@Data
public class SpiderResponse {
    private Response response;

    private String spiderName;
    private String url;

    public static SpiderResponse build(Response response) {
        SpiderResponse spiderResponse = new SpiderResponse();
        spiderResponse.setResponse(response);
        return spiderResponse;
    }
}
