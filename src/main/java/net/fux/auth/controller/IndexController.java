package net.fux.auth.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fuxj on 2019/3/7
 */
@Controller
public class IndexController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        //判断是否登录，未登录跳转登录页面
        if (!subject.isAuthenticated()) {
            return "login";
        }
        return "index";
    }
}
