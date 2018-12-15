package com.terwergreen.plugins.blog.front;

import com.alibaba.fastjson.JSON;
import com.terwergreen.core.CommonService;
import com.terwergreen.exception.WebException;
import com.terwergreen.plugins.blog.pojo.Post;
import com.terwergreen.plugins.blog.service.PostService;
import com.terwergreen.pojo.SiteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 博客
 *
 * @author terwer
 * @version 1.0
 * 18-12-12 上午3:33
 **/
@Controller
@RequestMapping("blog")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private PostService postService;

    @RequestMapping
    public String blog(Model model, String s, Integer page) throws WebException {
        SiteConfig siteConfig = null;
        List<Post> dingPostList = null;
        List<Post> postList = null;
        try {
            siteConfig = commonService.getSiteConfig();
            model.addAttribute("siteConfig", siteConfig);
            if (null == siteConfig) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }
            model.addAttribute("s", s);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "themes/" + siteConfig.getWebtheme() + "/index";
    }

    @RequestMapping("post/{postId}.html")
    public String post(Model model, @PathVariable String postId) throws WebException {
        //去除后缀
        postId = postId.replace(".html", "");
        model.addAttribute("postId", postId);
        //查询分类
        //logger.debug("分类为：" + categoryId);
        SiteConfig siteConfig = null;
        try {
            siteConfig = commonService.getSiteConfig();
            model.addAttribute("siteConfig", siteConfig);
            logger.info("获取文章成功:siteConfigDTO=" + JSON.toJSONString(siteConfig));
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "themes/" + siteConfig.getWebtheme() + "/post";
    }

    @RequestMapping("/essay")
    public String essay(Model model) {
        SiteConfig siteConfig = commonService.getSiteConfig();
        model.addAttribute("siteConfig", siteConfig);
        return "themes/" + siteConfig.getWebtheme() + "/essay";
    }
}
