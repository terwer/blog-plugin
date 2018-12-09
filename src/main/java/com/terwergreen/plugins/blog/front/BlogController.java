package com.terwergreen.plugins.blog.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping
    public String blog() {
        return "themes/default/index";
    }
}
