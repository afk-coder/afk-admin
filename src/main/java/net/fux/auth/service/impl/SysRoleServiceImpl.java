package net.fux.auth.service.impl;

import net.fux.auth.entity.SysRole;
import net.fux.auth.service.SysRoleService;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private IBaseService baseService;

    @Override
    public List<SysRole> getListByUserId(Integer userId) {
        String sql = "select r.* from sys_user_role ur, sys_role r where ur.user_id = ? ";
        return baseService.findBySql(sql, SysRole.class, userId);
    }
}
