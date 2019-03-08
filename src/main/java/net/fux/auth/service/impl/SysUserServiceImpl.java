package net.fux.auth.service.impl;

import net.fux.auth.entity.SysUser;
import net.fux.auth.service.SysUserService;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fuxj on 2019/3/6
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private IBaseService baseService;

    @Override
    public SysUser getUser(String username) {
        String sql = "select * from sys_user where name = ? ";
        return baseService.getBySql(sql, SysUser.class, username);
    }
}
