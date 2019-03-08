package net.fux.auth.service;

import net.fux.auth.entity.SysUser;

/**
 * Created by fuxj on 2019/3/6
 */
public interface SysUserService {

    SysUser getUser(String username);
}
