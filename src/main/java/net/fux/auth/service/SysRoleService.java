package net.fux.auth.service;

import net.fux.auth.entity.SysRole;

import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
public interface SysRoleService {

    List<SysRole> getListByUserId(Integer userId);
}
