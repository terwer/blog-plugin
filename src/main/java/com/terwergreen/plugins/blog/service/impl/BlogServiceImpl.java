package com.terwergreen.plugins.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.terwergreen.core.CommonService;
import com.terwergreen.plugins.blog.service.BlogService;
import com.terwergreen.pojo.SiteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务实现
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/08 20:33:11
 **/
@Service
public class BlogServiceImpl implements BlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private CommonService commonService;

    @Override
    public String getBlogInfo() {
        SiteConfig siteConfig = commonService.getSiteConfig();
        if (null != siteConfig) {
            return JSON.toJSONString(siteConfig);
        }
        return "no data";
    }
}
