package com.terwergreen.plugins.blog.front;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.terwergreen.core.CommonService;
import com.terwergreen.exception.WebException;
import com.terwergreen.plugins.blog.pojo.Post;
import com.terwergreen.plugins.blog.pojo.PostMeta;
import com.terwergreen.plugins.blog.service.PostService;
import com.terwergreen.plugins.blog.util.PostStatusEnum;
import com.terwergreen.plugins.blog.util.PostTypeEmum;
import com.terwergreen.pojo.SiteConfig;
import com.terwergreen.util.Constants;
import com.terwergreen.util.MarkdownUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String blog(Model model, String q, Integer page) throws WebException {
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
            //获得当前登陆用户对应的对象。
            Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (currentUser != "anonymousUser") {
            }

            Map paramMap = new HashMap();
            paramMap.put("postStatus", PostStatusEnum.POST_STATUS_PUBLISH.getName());
            paramMap.put("postType", PostTypeEmum.POST_TYPE_POST.getName());
            if (!org.springframework.util.StringUtils.isEmpty(q)) {
                if (PostTypeEmum.POST_TYPE_NOTE.getName().equals(q)) {
                    paramMap.put("postType", PostTypeEmum.POST_TYPE_NOTE.getName());
                } else {
                    paramMap.put("search", q);
                }
            }
            if (null == page) {
                page = 1;
            }
            PageInfo postListInfo = postService.getPostsByPage(page, Constants.DEFAULT_PAGE_SIZE, paramMap);
            postList = postListInfo.getList();

            Map dingParamMap = new HashMap();
            dingParamMap.put("postType", PostTypeEmum.POST_TYPE_POST.getName());
            dingParamMap.put("metaKey", "ding");
            dingPostList = postService.getRecentPosts(dingParamMap);

            model.addAttribute("siteConfig", siteConfig);
            // model.addAttribute("sysUser", sysUser);
            model.addAttribute("dingPostList", dingPostList);
            model.addAttribute("page", postListInfo.getNextPage());
            model.addAttribute("postList", postList);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfig) + ",postList=" + postList);
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

        //查询分类
        //logger.debug("分类为：" + categoryId);
        SiteConfig siteConfig = null;
        try {
            siteConfig = commonService.getSiteConfig();
            model.addAttribute("siteConfig", siteConfig);
            //获得当前登陆用户对应的对象。
            Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (currentUser != "anonymousUser") {
            }

            //文章ID和文章别名分开处理
            Post post;
            if (StringUtils.isNumeric(postId)) {
                logger.debug("文章ID为：" + postId);
                post = postService.getPostById(Integer.parseInt(postId));
            } else {
                logger.debug("文章别名为：" + postId);
                post = postService.getPostBySlug(postId);
            }

            //将Markdown转换为Html显示
            String htmlContent = MarkdownUtil.md2html(post.getPostContent());
            post.setPostContent(htmlContent);
            // model.addAttribute("sysUser", sysUser);
            model.addAttribute("post", post);

            if (post == null) {
                logger.error("文章不存在");
                throw new WebException("文章不存在");
            } else {
                PostMeta viewCountPostMeta = new PostMeta();
                viewCountPostMeta.setPostId(post.getPostId());
                viewCountPostMeta.setMetaKey("view_count");
                viewCountPostMeta.setMetaValue(String.valueOf(post.getViewCount() + 1));
                postService.saveOrUpdatePostMeta(viewCountPostMeta);
            }
            logger.info("获取文章成功:siteConfigDTO=" + JSON.toJSONString(siteConfig) + ",post=" + post);
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
