package net.fux.auth.service.impl;

import net.fux.auth.entity.User;
import net.fux.auth.service.UserService;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fuxj on 2019/3/6
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IBaseService baseService;

    @Override
    public User getUser(String username) {
        String sql = "select * from sys_user where name = ? ";
        return baseService.getBySql(sql, User.class, username);
    }
}
