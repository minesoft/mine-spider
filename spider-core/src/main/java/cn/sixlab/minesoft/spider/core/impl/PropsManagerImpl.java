package cn.sixlab.minesoft.spider.core.impl;

import cn.sixlab.minesoft.spider.core.component.PropsManager;

import java.util.HashMap;
import java.util.Map;

public class PropsManagerImpl implements PropsManager {

    private Map<String, String> map = new HashMap<>();

    @Override
    public boolean exist(String key) {
        return map.containsKey(key);
    }

    @Override
    public void addProp(String key, String val) {
        map.put(key, val);
    }

    @Override
    public String getProp(String key) {
        return map.get(key);
    }

    @Override
    public String delProp(String key) {
        return map.remove(key);
    }

}
