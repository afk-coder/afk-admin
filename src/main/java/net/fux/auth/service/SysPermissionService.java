package net.fux.auth.service;

import net.fux.auth.entity.SysPermission;

import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
public interface SysPermissionService {

    List<SysPermission> getListByRoleId(Integer roleId);
}
