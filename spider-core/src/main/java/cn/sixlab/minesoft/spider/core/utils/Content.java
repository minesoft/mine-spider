package cn.sixlab.minesoft.spider.core.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Content extends LinkedHashMap<String, Object> {

    private String spiderName;
    private String url;

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.put("spiderName", spiderName);
        this.spiderName = spiderName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.put("url", url);
        this.url = url;
    }

    public Content() { }

    public Content(String spiderName, String url) {
        this.spiderName = spiderName;
        this.url = url;
    }

    public Content add(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Content addAll(Map<? extends String, ?> m) {
        super.putAll(m);
        return this;
    }
}
