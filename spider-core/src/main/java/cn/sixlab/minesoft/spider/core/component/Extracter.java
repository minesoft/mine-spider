package cn.sixlab.minesoft.spider.core.component;


import cn.sixlab.minesoft.spider.core.utils.Content;
import cn.sixlab.minesoft.spider.core.vo.SpiderResponse;

public interface Extracter {

    Content extract(SpiderResponse response);

}
