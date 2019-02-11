package system.core.dao.impl;

import org.hibernate.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import system.core.dao.BaseDao;
import system.core.page.PageResult;
import system.core.utils.QueryHelper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Administrator
 * @create 6/8
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];

    }

    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void delete(Serializable id) {
        getHibernateTemplate().delete(getObjectById(id));
    }

    @Override
    public T getObjectById(Serializable id) {
        return getHibernateTemplate().get(clazz, id);
    }

    @Override
    public List<T> getList() {
        return getSessionFactory().getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    @Override
    public List<T> getList(QueryHelper queryHelper) {
        Query query = getSessionFactory().getCurrentSession().createQuery(queryHelper.getHql());
        int i = 0;
        for (Object per : queryHelper.getParameters()) {
            query.setParameter(i++, per);
        }
        return query.list();
    }

    @Override
    public PageResult getPageResult(QueryHelper queryHelper, int pageNum, int pageSize) {
        Query query = getSessionFactory().getCurrentSession().createQuery(queryHelper.getHql());
        int i = 0;
        for (Object per : queryHelper.getParameters()) {
            query.setParameter(i++, per);
        }
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        Query tquery = getSessionFactory().getCurrentSession().createQuery(queryHelper.getTotalCountHql());
        i = 0;
        for (Object per : queryHelper.getParameters()) {
            tquery.setParameter(i++, per);
        }
        long totalCount = (long) tquery.uniqueResult();

        return new PageResult(totalCount, pageNum, pageSize, query.list());
    }
}