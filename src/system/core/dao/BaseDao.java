package system.core.dao;

import system.core.page.PageResult;
import system.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @create 6/8
 */
public interface BaseDao<T> {
    /**
     * 保存entity的信息
     *
     * @param entity
     */
    void save(T entity);

    /**
     * 更新entity的信息
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 删除entity的信息
     *
     * @param id
     */
    void delete(Serializable id);

    /**
     * 通过id查找entity
     *
     * @param id
     * @return
     */
    T getObjectById(Serializable id);

    /**
     * 获取entity的列表
     *
     * @return
     */
    List<T> getList();

    /**
     * 条件查询
     *
     * @param queryHelper
     * @return
     */
    List<T> getList(QueryHelper queryHelper);

    /**
     * 分页查询
     *
     * @param pageNum     当前页
     * @param pageSize    页大小
     * @param queryHelper 查询助手
     * @return
     */
    PageResult getPageResult(QueryHelper queryHelper, int pageNum, int pageSize);
}