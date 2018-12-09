package com.terwergreen.plugins.blog.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/blog")
public class BlogManageController {
    @RequestMapping
    public String BlogAdmin() {
        return "admin/index";
    }
}
