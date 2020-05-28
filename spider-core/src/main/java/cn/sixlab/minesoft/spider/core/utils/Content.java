package cn.sixlab.minesoft.spider.core.utils;

import cn.sixlab.minesoft.spider.core.vo.SpiderRequest;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class Content extends LinkedHashMap<String, Object> {

    private String spiderName;
    private String url;
    private List<SpiderRequest> requestList;

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
