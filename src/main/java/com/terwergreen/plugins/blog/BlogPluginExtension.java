package com.terwergreen.plugins.blog;

import com.terwergreen.plugins.PluginInterface;
import com.terwergreen.plugins.blog.front.BlogApi;
import com.terwergreen.plugins.blog.front.BlogController;
import com.terwergreen.plugins.blog.front.BlogManageController;
import com.terwergreen.plugins.blog.front.PostApi;
import com.terwergreen.plugins.blog.service.impl.BlogServiceImpl;
import com.terwergreen.plugins.blog.service.impl.PostServiceImpl;
import org.pf4j.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.reactive.function.server.RouterFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扩展点
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/08 20:33:11
 **/
@Extension
public class BlogPluginExtension implements PluginInterface {
    private static final Logger logger = LoggerFactory.getLogger(BlogPluginExtension.class);
    private GenericApplicationContext applicationContext;

    public BlogPluginExtension(GenericApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        logger.info("BlogPluginExtension contructor");
        // 注册插件依赖
        registerBeans();
    }

    private void registerBeans() {
        applicationContext.registerBean(BlogController.class);
        applicationContext.registerBean(BlogManageController.class);
        applicationContext.registerBean(BlogApi.class);
        applicationContext.registerBean(BlogServiceImpl.class);
        applicationContext.registerBean(PostApi.class);
        applicationContext.registerBean(PostServiceImpl.class);
    }

    @Override
    public String identify() {
        return "BlogPlugin identify in plugin";
    }

    @Override
    public List<?> reactiveRoutes() {
        return new ArrayList<RouterFunction<?>>() {{
        }};
    }

    @Override
    public Map data() {
        Map dataMap = new HashMap();
        dataMap.put("blog", "Blog");
        return dataMap;
    }
}
