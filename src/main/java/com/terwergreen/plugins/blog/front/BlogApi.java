package com.terwergreen.plugins.blog.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * webFlux Api
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/08 20:33:11
 **/
@Controller
@RequestMapping("api/blog")
public class BlogApi {
    @RequestMapping
    @ResponseBody
    public String BlogAdmin() {
        return "blog api";
    }
}

