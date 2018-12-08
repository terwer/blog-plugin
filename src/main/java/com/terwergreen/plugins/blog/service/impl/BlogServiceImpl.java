package com.terwergreen.plugins.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.terwergreen.core.CommonDAO;
import com.terwergreen.plugins.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务实现
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/08 20:33:11
 **/
@Service
public class BlogServiceImpl implements BlogService {
    private static final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public String getBlogInfo() {
        List list = null;
        try {
            Map paramMap = new HashMap();
            paramMap.put("optionGroup", "siteConfig");
            list = commonDAO.queryListByMap("getOptionByGroup", paramMap);
        } catch (Exception e) {
            log.error("获取配置项异常", e);
            return "current version:BuguCMS 2.0.0";
        }
        //多个结果返回List，只有一个结果的时候直接返回
        if (!CollectionUtils.isEmpty(list)) {
            return JSON.toJSONString(list);
        }
        return "no data";
    }
}
