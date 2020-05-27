package cn.sixlab.minesoft.spider.core.vo;

import lombok.Data;
import okhttp3.Request;

import java.util.HashMap;
import java.util.Map;

@Data
public class SpiderRequest {
    private Request request;
    private Map<String, Object> attributes = new HashMap<>();

    public SpiderRequest put(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    public static SpiderRequest build(Request request) {
        SpiderRequest spiderRequest = new SpiderRequest();
        spiderRequest.setRequest(request);
        return spiderRequest;
    }
}
