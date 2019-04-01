package net.fux.auth.service.impl;

import net.fux.auth.entity.User;
import net.fux.auth.service.UserService;
import net.fux.support.service.IBaseService;
import net.fux.support.vo.BootstrapTable;
import net.fux.support.vo.ResultVo;
import net.fux.support.vo.SearchVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

    @Override
    public BootstrapTable list(SearchVo search) {
        String sql = "select * from sys_user ";
        return baseService.paginationSQL(sql, new Object[]{}, search.getPage(), search.getRows(), BootstrapTable.class);
    }

    @Override
    public ResultVo saveOrUpdate(User user) {
        try {
            if(null != user.getId()) {
                user.setUpdateTime(new Date());
            } else {
                user.setCreateTime(new Date());
            }
            user.setIsEnabled("Y");
            user.setPassword("123456");
            baseService.saveOrUpdate(user);
            return ResultVo.successInfo("操作成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("操作过程中出现异常!" );
        }
    }

    @Override
    public User getUserById(Integer id) {
        String sql = "select * from sys_user where id = ? ";
        return baseService.getBySql(sql, User.class, id);
    }
}
