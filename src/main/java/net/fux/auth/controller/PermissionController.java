package net.fux.auth.controller;

import net.fux.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by fuxj on 2019/3/14
 */
@Controller
@RequestMapping("/auth/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listView() {
        return "/auth/permission/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> list() {
        return null;
    }
}
