package net.fux.support.service.impl;

import net.fux.support.dao.IBaseDao;
import net.fux.support.service.IBaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... params) {
        return baseDao.queryForList(sql, params);
    }

    @Override
    public <T> T queryForObject(String sql, Class<T> entityClass, Object... params) {
        return baseDao.queryForObject(sql, entityClass, params);
    }

    @Override
    public Integer executeSQL(String sql, Object[] params) {
        return baseDao.executeSQL(sql, params);
    }

    @Override
    public Map<String, Object> getRowSQL(String sql, Object[] params) {
        return baseDao.getRowSQL(sql, params);
    }

    @Override
    public List<Map<String, Object>> findRowSQL(String sql, Object[] params) {
        return baseDao.findRowSQL(sql, params);
    }

    @Override
    public <T> List<T> findVoSQL(String sql, List<?> params, Class<T> requireType) {
        return baseDao.findVoSQL(sql, params, requireType);
    }

    @Override
    public <T> T getEntity(Class<T> entityName, Serializable id) {
        return baseDao.getEntity(entityName, id);
    }

    @Override
    public <T> T getEntitySQL(String sql, List<Object> params, Class<T> entityClass) {
        return baseDao.getEntitySQL(sql, params, entityClass);
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        baseDao.saveOrUpdate(entity);
    }

    @Override
    public <T> void delete(T entity) {
        baseDao.delete(entity);
    }

    @Override
    public <T> void delete(Collection<T> entitys) {
        baseDao.delete(entitys);
    }

    @Override
    public <T> void delete(Class<T> entityName, Serializable id) {
        baseDao.delete(entityName, id);
    }

    @Override
    public <T> void delete(Class<T> entityName, Serializable[] ids) {
        baseDao.delete(entityName, ids);
    }
}
