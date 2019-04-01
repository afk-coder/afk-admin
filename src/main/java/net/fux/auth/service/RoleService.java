package net.fux.auth.service;

import net.fux.auth.entity.Role;
import net.fux.support.vo.BootstrapTable;
import net.fux.support.vo.SearchVo;

import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
public interface RoleService {

    List<Role> getListByUserId(Integer userId);

    BootstrapTable list(SearchVo search);
}
