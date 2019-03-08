package net.fux.support.service.impl;

import net.fux.support.dao.IBaseDao;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxj on 2019/3/8
 */
@Service("baseService")
public class BaseService implements IBaseService {

    @Resource(name = "baseDao")
    private IBaseDao baseDao;

    @Override
    public <T> T getBySql(String sql, Class<T> entityClass, Object... params) {
        return baseDao.getBySql(sql, entityClass, params);
    }

    @Override
    public <T> List<T> findBySql(String sql, Class<T> entityClass, Object... params) {
        return baseDao.findBySql(sql, entityClass, params);
    }
}