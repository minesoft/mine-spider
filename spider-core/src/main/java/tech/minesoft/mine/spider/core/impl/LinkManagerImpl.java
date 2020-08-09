package tech.minesoft.mine.spider.core.impl;

import tech.minesoft.mine.spider.core.component.LinkManager;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkManagerImpl implements LinkManager {

    private static LinkedList<SpiderRequest> linkList = new LinkedList<>();

    @Override
    public void addLinks(SpiderRequest... requests) {
        if (null != requests && requests.length > 0) {
            linkList.addAll(Arrays.asList(requests));
        }
    }

    @Override
    public void addLinks(List<SpiderRequest> requests) {
        if (null != requests && requests.size() > 0) {
            linkList.addAll(requests);
        }
    }

    @Override
    public SpiderRequest nextRequest() {
        return linkList.poll();
    }

}
