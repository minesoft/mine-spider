package cn.sixlab.minesoft.spider.core.utils;

import cn.sixlab.minesoft.spider.core.vo.SpiderResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtils {
    private static OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static SpiderResponse request(Request request) {
        return request(request, "default");
    }

    public static SpiderResponse request(Request request, String spiderName) {
        try {
            Response response = client.newCall(request).execute();

            SpiderResponse spiderResponse = SpiderResponse.build(response);

            spiderResponse.setUrl(request.url().toString());
            spiderResponse.setSpiderName(spiderName);

            return spiderResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseResponse(Response response) {
        if (response != null) {
            log.info("请求结束:" + response.request().url());
            log.info("请求code:" + response.code());
            log.info("请求message:" + response.message());

            if (response.isSuccessful()) {
                if (response.body() != null) {
                    try {
                        String text = response.body().string();
                        // log.info("返回：" + text);
                        return text;
                    } catch (IOException e) {
                        log.error("请求IO异常：", e);
                    }
                }
                log.error("请求体为空");
            } else {
                log.error("请求失败");
            }
        }
        return "";
    }
}
