package cn.sixlab.minesoft.spider.core.impl;

import cn.sixlab.minesoft.spider.core.component.Saver;
import cn.sixlab.minesoft.spider.core.utils.Content;
import cn.sixlab.minesoft.spider.core.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaverImpl implements Saver {

    @Override
    public void saveContent(Content content) {
        log.info("开始存储>>> [" + content.getSpiderName() + "]" + content.getUrl());

        log.info(JsonUtils.toJson(content));

        log.info("存储完成>>> [" + content.getSpiderName() + "]" + content.getUrl());
    }

}
