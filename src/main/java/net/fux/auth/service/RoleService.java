package net.fux.auth.service;

import net.fux.auth.entity.Role;

import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
public interface RoleService {

    List<Role> getListByUserId(Integer userId);
}
