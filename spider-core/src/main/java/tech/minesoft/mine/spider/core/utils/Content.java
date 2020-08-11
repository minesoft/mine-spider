package tech.minesoft.mine.spider.core.utils;

import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
public class Content{

    private SpiderRequest request;
    private List<SpiderRequest> requestList;
    public final LinkedHashMap<String, Object> result = new LinkedHashMap<>();

    public Content(SpiderRequest request) {
        this.request = request;
    }
}
