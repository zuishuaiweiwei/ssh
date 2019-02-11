package system.core.service.impl;

import system.core.dao.BaseDao;
import system.core.page.PageResult;
import system.core.service.BaseService;
import system.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @create 8/4
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    public BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public T getObjectById(Serializable id) {
        return baseDao.getObjectById(id);
    }

    @Override
    public List<T> getList() {
        return baseDao.getList();
    }

    @Override
    public List<T> getList(QueryHelper queryHelper) {
        return baseDao.getList(queryHelper);
    }

    @Override
    public PageResult getPageResult(QueryHelper queryHelper, int pageNum, int pageSize) {
        return baseDao.getPageResult(queryHelper, pageNum, pageSize);
    }


}