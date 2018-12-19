package com.terwergreen.plugins.blog;

import com.terwergreen.plugins.BugucmsPlugin;
import org.pf4j.PluginException;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 插件
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/08 20:33:11
 **/
public class BlogPlugin extends BugucmsPlugin {
    private static final Logger logger = LoggerFactory.getLogger(BlogPlugin.class);

    public BlogPlugin(PluginWrapper wrapper) {
        super(wrapper);
        logger.info("BlogPlugin registerBeans");
    }

    @Override
    public void start() throws PluginException {
        super.start();
        logger.info("BlogPlugin started");
    }

    @Override
    public void stop() {
        super.stop();
        logger.info("BlogPlugin stoped");
    }
}
