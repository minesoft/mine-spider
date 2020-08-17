package tech.minesoft.mine.spider.core.vo;

import lombok.Data;
import okhttp3.Request;

import java.util.HashMap;
import java.util.Map;

@Data
public class SpiderRequest {
    private String spiderName;
    private Request request;
    private Map<String, Object> attributes = new HashMap<>();

    public SpiderRequest put(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    public Object get(String key){
        return attributes.get(key);
    }

    public static SpiderRequest build(String spiderName, Request request) {
        SpiderRequest spiderRequest = new SpiderRequest();
        spiderRequest.setSpiderName(spiderName);
        spiderRequest.setRequest(request);
        return spiderRequest;
    }

    public String getUrl(){
        return request.url().toString();
    }
}
