package net.fux.auth.service;

import net.fux.auth.entity.User;
import net.fux.support.vo.BootstrapTable;
import net.fux.support.vo.ResultVo;
import net.fux.support.vo.SearchVo;

/**
 * Created by fuxj on 2019/3/6
 */
public interface UserService {

    User getUser(String username);

    BootstrapTable list(SearchVo search);

    ResultVo saveOrUpdate(User user);

    User getUserById(Integer id);
}
