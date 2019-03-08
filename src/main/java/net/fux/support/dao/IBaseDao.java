package net.fux.support.dao;

import java.util.List;

/**
 * Created by fuxj on 2019/3/7
 */
public interface IBaseDao {

    <T> T getBySql(String sql, Class<T> entityClass, Object... params);

    <T> List<T> findBySql(String sql, Class<T> entityClass, Object... params);
}
