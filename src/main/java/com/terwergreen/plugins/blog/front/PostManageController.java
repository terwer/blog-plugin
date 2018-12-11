package com.terwergreen.plugins.blog.front;

import com.terwergreen.core.CommonService;
import com.terwergreen.exception.WebException;
import com.terwergreen.plugins.blog.pojo.Post;
import com.terwergreen.plugins.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文章管理
 *
 * @author Terwer
 * @version 1.0
 * 2018/7/4 10:11
 **/
@Controller
@RequestMapping(value = "/blog/postManage")
public class PostManageController {
    private static final Logger logger = LoggerFactory.getLogger(PostApi.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private PostService postService;

    @RequestMapping("new")
    public String add(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            initPage(model, 0);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_edit";
    }

    @RequestMapping("edit/{postId}")
    public String edit(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("postId") Integer postId) throws Exception {
        try {
            initPage(model, postId);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_edit";
    }

    @RequestMapping("list")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            model.addAttribute("siteConfig", commonService.getSiteConfig());
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_list";
    }

    /**
     * 初始化页面
     *
     * @param model
     */
    private void initPage(Model model, Integer postId) throws Exception {
        try {
            model.addAttribute("siteConfig", commonService.getSiteConfig());
            Post post = new Post();
            if (postId > 0) {
                post = postService.getPostById(postId);
            }
            model.addAttribute("post", post);
            model.addAttribute("postId", postId);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
    }
}
