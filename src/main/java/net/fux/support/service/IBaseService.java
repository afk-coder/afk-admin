package net.fux.support.service;

import java.util.List;
import java.util.Map;

/**
 * Created by fuxj on 2019/3/7
 */
public interface IBaseService {

    <T> T getBySql(String sql, Class<T> entityClass, Object... params);

    <T> List<T> findBySql(String sql, Class<T> entityClass, Object... params);

    List<Map<String, Object>> queryForList(String sql, Object... params);

    <T> T queryForObject(String sql, Class<T> entityClass, Object... params);
}
