package tech.minesoft.mine.spider.core.component;


import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;

public interface Downloader {

    default SpiderResponse get(String url, String spiderName) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        SpiderRequest spiderRequest = SpiderRequest.build(spiderName, request);

        return download(spiderRequest);
    }

    default SpiderResponse post(String url, Map<String, String> data,String spiderName) {
        FormBody.Builder builder = new FormBody.Builder();
        if (null != data && !data.isEmpty()) {
            for (String key : data.keySet()) {
                String val = data.get(key);
                if (null != val) {
                    builder.add(key, val);
                }
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        SpiderRequest spiderRequest = SpiderRequest.build(spiderName, request);

        return download(spiderRequest);
    }

    default SpiderResponse postBody(String url, String body, String mediaType, String spiderName) {
        return postBody(url, body, MediaType.parse(mediaType), spiderName);
    }

    default SpiderResponse postBody(String url, String body, MediaType mediaType, String spiderName) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, body))
                .build();

        SpiderRequest spiderRequest = SpiderRequest.build(spiderName, request);

        return download(spiderRequest);
    }

    SpiderResponse download(SpiderRequest request);

}
