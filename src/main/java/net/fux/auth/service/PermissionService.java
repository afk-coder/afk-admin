package net.fux.auth.service;

import net.fux.auth.entity.Permission;
import net.fux.support.vo.BootstrapTable;
import net.fux.support.vo.JsTreeJson;
import net.fux.support.vo.ResultVo;
import net.fux.support.vo.SearchVo;

import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
public interface PermissionService {

    List<Permission> getListByRoleId(Integer roleId);

    List<JsTreeJson> list();

    Permission getPermissionById(Integer id);

    ResultVo saveOrUpdate(Permission permission);

    ResultVo delete(Integer id);
}
