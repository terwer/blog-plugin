package com.terwergreen.plugins.blog.front;

import com.terwergreen.plugins.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/29 1:23
 **/
@Controller
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String blog() {
        return blogService.getBlogInfo();
    }
}
