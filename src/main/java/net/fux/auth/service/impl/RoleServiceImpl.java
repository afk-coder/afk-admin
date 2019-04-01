package net.fux.auth.service.impl;

import net.fux.auth.entity.Role;
import net.fux.auth.service.RoleService;
import net.fux.support.service.IBaseService;
import net.fux.support.vo.BootstrapTable;
import net.fux.support.vo.SearchVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private IBaseService baseService;

    @Override
    public List<Role> getListByUserId(Integer userId) {
        String sql = "select r.* from sys_user_role ur, sys_role r where ur.user_id = ? ";
        return baseService.findBySql(sql, Role.class, userId);
    }

    @Override
    public BootstrapTable list(SearchVo search) {
        return null;
    }
}
