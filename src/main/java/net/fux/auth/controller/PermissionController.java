package net.fux.auth.controller;

import net.fux.auth.entity.Permission;
import net.fux.auth.service.PermissionService;
import net.fux.support.vo.JsTreeJson;
import net.fux.support.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public List<JsTreeJson> list() {
        return permissionService.list();
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addView(HttpServletRequest request, Model model) {
        String parentId = request.getParameter("parentId");
        if(StringUtils.isNotBlank(parentId)) {
            Permission permission = permissionService.getPermissionById(Integer.valueOf(parentId));
            model.addAttribute("permission", permission);
        }
        return "/auth/permission/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo add(Permission permission) {
        return permissionService.saveOrUpdate(permission);
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updateView(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Permission permission = permissionService.getPermissionById(Integer.valueOf(id));
        model.addAttribute("permission", permission);
        return "/auth/permission/update";
    }

    @RequestMapping(value ="update", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo update(Permission permission) {
        return permissionService.saveOrUpdate(permission);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo delete(Integer id) {
        return permissionService.delete(id);
    }

}
