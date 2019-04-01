package net.fux.auth.controller;

import net.fux.auth.service.RoleService;
import net.fux.support.vo.BootstrapTable;
import net.fux.support.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fuxj on 2019-3-21
 */
@Controller
@RequestMapping("/auth/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listView() {
        return "/auth/role/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public BootstrapTable list(SearchVo search) {
        return roleService.list(search);
    }
}
