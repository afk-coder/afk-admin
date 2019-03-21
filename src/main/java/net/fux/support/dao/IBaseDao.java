package net.fux.support.dao;

import net.fux.support.core.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxj on 2019/3/7
 */
public interface IBaseDao {

    <T> T getBySql(String sql, Class<T> entityClass, Object... params);

    <T> List<T> findBySql(String sql, Class<T> entityClass, Object... params);

    List<Map<String, Object>> queryForList(String sql, Object... params);

    <T> T queryForObject(String sql, Class<T> entityClass, Object... params);

    <T extends Page> T paginationSQL(String sql, Object[] params, int page, int rows, Class<T> requirePageType);

    Integer executeSQL(String sql, Object[] params);

    Map<String, Object> getRowSQL(String sql, Object[] params);

    List<Map<String, Object>> findRowSQL(String sql, Object[] params);

    <T> List<T> findVoSQL(String sql, List<?> params, Class<T> requireType);

    <T> T getEntity(Class<T> entityName, Serializable id);

    <T> T getEntitySQL(String sql, List<Object> params, Class<T> entityClass);

    <T> void saveOrUpdate(T entity);

    <T> void delete(T entity);

    <T> void delete(Collection<T> entitys);

    <T> void delete(Class<T> entityName, Serializable id);

    <T> void delete(Class<T> entityName, Serializable[] ids);

}
