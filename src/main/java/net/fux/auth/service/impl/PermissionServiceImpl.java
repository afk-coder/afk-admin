package net.fux.auth.service.impl;

import net.fux.auth.entity.Permission;
import net.fux.auth.service.PermissionService;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private IBaseService baseService;

    @Override
    public List<Permission> getListByRoleId(Integer roleId) {
        String sql = "select p.* from sys_role_permission rp, sys_permission p where rp.role_id = ? ";
        return baseService.findBySql(sql, Permission.class, roleId);
    }
}
