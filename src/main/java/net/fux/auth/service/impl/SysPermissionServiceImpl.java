package net.fux.auth.service.impl;

import net.fux.auth.entity.SysPermission;
import net.fux.auth.service.SysPermissionService;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private IBaseService baseService;

    @Override
    public List<SysPermission> getListByRoleId(Integer roleId) {
        String sql = "select p.* from sys_role_permission rp, sys_permission p where rp.role_id = ? ";
        return baseService.findBySql(sql, SysPermission.class, roleId);
    }
}
