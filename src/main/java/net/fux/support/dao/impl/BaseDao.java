package net.fux.support.dao.impl;

import net.fux.support.core.LocalSQLUtil;
import net.fux.support.core.LowerCaseRowMapper;
import net.fux.support.core.Page;
import net.fux.support.core.PageContext;
import net.fux.support.dao.IBaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxj on 2019/3/8
 */
@Repository("baseDao")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BaseDao implements IBaseDao {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private static final String EXPRESS_COUNT_SQL = "select count(1) from ({0}) t";

    public BaseDao() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public <T> T getBySql(String sql, Class<T> entityClass, Object... params) {
        List list = findList(sql, entityClass, params);
        if(null != list && list.size() > 0) {
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> findBySql(String sql, Class<T> entityClass, Object... params) {
        return findList(sql, entityClass, params);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... params) {
        return jdbcTemplate.queryForList(sql, params);
    }

    @Override
    public <T> T queryForObject(String sql, Class<T> entityClass, Object... params) {
        return jdbcTemplate.queryForObject(sql, entityClass, params);
    }

    @Override
    public <T extends Page> T paginationSQL(String sql, Object[] params, int page, int rows, Class<T> requirePageType) {
        String countSql = MessageFormat.format(EXPRESS_COUNT_SQL, sql);
        sql = LocalSQLUtil.generateLocalPageSQL(sql, page, rows);
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, params);
        PageContext pageContext = new PageContext(page, rows, result, queryForObject(countSql, Integer.class, params));
        try {
            return requirePageType.getConstructor(PageContext.class).newInstance(pageContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer executeSQL(String sql, Object[] params) {
        return this.jdbcTemplate.update(sql, new ArgumentPreparedStatementSetter(params));
    }

    @Override
    public Map<String, Object> getRowSQL(String sql, Object[] params) {
        try {
            return this.jdbcTemplate.queryForObject(sql, params, LowerCaseRowMapper.getInstance());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> findRowSQL(String sql, Object[] params) {
        return this.jdbcTemplate.query(sql, params, LowerCaseRowMapper.getInstance());
    }

    @Override
    public <T> List<T> findVoSQL(String sql, List<?> params, Class<T> requireType) {
        return jdbcTemplate.query(sql, null == params ? null : params.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(requireType));
    }

    @Override
    public <T> T getEntity(Class<T> entityName, Serializable id) {
        return (T) getSession().get(entityName, id);
    }

    @Override
    public <T> T getEntitySQL(String sql, List<Object> params, Class<T> entityClass) {
        SQLQuery query = getSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0, size = params.size(); i < size; i++) {
                query.setParameter(i, params.get(i));
            }
        }
        query.addEntity(entityClass);
        return (T) query.list().get(0);
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        try {
            getSession().saveOrUpdate(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public <T> void delete(T entity) {
        try {
            getSession().delete(entity);
            getSession().flush();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public <T> void delete(Collection<T> entitys) {
        for (Object entity : entitys) {
            getSession().delete(entity);
        }
        getSession().flush();
    }

    @Override
    public <T> void delete(Class<T> entityName, Serializable id) {
        delete(getEntity(entityName, id));
        getSession().flush();
    }

    @Override
    public <T> void delete(Class<T> entityName, Serializable[] ids) {
        if (ids == null || ids.length == 0)
            return;
        for (Serializable id : ids) {
            delete(entityName, id);
        }
        getSession().flush();
    }

    private <T> List<T> findList(String sql, Class<T> entityClass, Object... params) {
        SQLQuery query = getSession().createSQLQuery(sql);
        if(params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        query.addEntity(entityClass);
        return query.list();
    }
}
