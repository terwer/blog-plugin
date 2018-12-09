package com.terwergreen.plugins.blog.front;

import com.terwergreen.core.CommonService;
import com.terwergreen.pojo.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private CommonService commonService;

    @RequestMapping
    public String blog(Model model) {
        SiteConfig siteConfig = commonService.getSiteConfig();
        model.addAttribute("siteConfig",siteConfig);
        return "themes/default/index";
    }
}
