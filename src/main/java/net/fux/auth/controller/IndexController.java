package net.fux.auth.controller;

import net.fux.auth.entity.User;
import net.fux.auth.service.IndexService;
import net.fux.auth.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxj on 2019/3/7
 */
@Controller
public class IndexController {

    @Resource
    private IndexService indexService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        //判断是否登录，未登录跳转登录页面
        if (!subject.isAuthenticated()) {
            return "redirect:/login";
        }
        User user = (User) subject.getSession().getAttribute(CustomRealm.SESSION_USER_KEY);
        List<Map<String, Object>> list = indexService.findPermissionByUserId(user.getId());
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
