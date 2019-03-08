package net.fux.auth.controller;

import net.fux.auth.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by fuxj on 2019/3/6
 */
@Controller
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
//                e.printStackTrace();
                model.addAttribute("msg", "用户名不存在！");
                return "login";// 返回登录页面
            } catch (IncorrectCredentialsException e) {
//                e.printStackTrace();
                model.addAttribute("msg", "密码错误，请重新输入！");
                return "login";// 返回登录页面
            }
        }
        // 登录成功后会跳转到successUrl配置的链接，不用管下面返回的链接。
        return "redirect:/index";

//        SysUser user = sysUserService.getUser(name);
//        if(null == user) {
//            model.addAttribute("msg", "用户名不存在！");
//            return "login";// 返回登录页面
//        }
//        if (!password.equals(user.getPassword())) {
//            model.addAttribute("msg", "密码错误，请重新输入！");
//            return "login";// 返回登录页面
//        }
//        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
//        // 登录后存放进shiro token
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//        }
//        // 登录成功后会跳转到successUrl配置的链接，不用管下面返回的链接。
//        return "redirect:/index";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:/login";
    }
}
