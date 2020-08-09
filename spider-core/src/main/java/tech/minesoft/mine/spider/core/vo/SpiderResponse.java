package tech.minesoft.mine.spider.core.vo;

import lombok.Data;
import okhttp3.Response;

@Data
public class SpiderResponse {
    private Response response;

    public static SpiderResponse build(Response response) {
        SpiderResponse spiderResponse = new SpiderResponse();
        spiderResponse.setResponse(response);
        return spiderResponse;
    }
}
