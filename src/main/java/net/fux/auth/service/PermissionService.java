package net.fux.auth.service;

import net.fux.auth.entity.Permission;

import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
public interface PermissionService {

    List<Permission> getListByRoleId(Integer roleId);
}
