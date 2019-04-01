package net.fux.auth.controller;

import net.fux.auth.entity.User;
import net.fux.auth.service.UserService;
import net.fux.support.vo.BootstrapTable;
import net.fux.support.vo.ResultVo;
import net.fux.support.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * Created by fuxj on 2019-3-21
 */
@Controller
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listView() {
        return "/auth/user/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public BootstrapTable list(SearchVo search) {
        return userService.list(search);
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addView(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id)) {
            User user = userService.getUserById(Integer.valueOf(id));
            model.addAttribute("user", user);
        }
        return "/auth/user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo add(User user) {
        return userService.saveOrUpdate(user);
    }
}
