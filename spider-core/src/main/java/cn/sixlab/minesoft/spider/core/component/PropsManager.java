package cn.sixlab.minesoft.spider.core.component;

public interface PropsManager {

    boolean exist(String key);

    void addProp(String key, String val);

    String getProp(String key);

    String delProp(String key);

}
