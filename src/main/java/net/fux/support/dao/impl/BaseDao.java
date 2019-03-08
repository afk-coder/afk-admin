package net.fux.support.dao.impl;

import net.fux.support.dao.IBaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxj on 2019/3/8
 */
@Repository("baseDao")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class BaseDao implements IBaseDao {

    @Resource
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

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
