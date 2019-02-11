package system.nsfw.complain.dao;

import system.core.dao.BaseDao;
import system.nsfw.complain.entity.Complain;

import java.util.List;

/**
 * @author Administrator
 * @create 8/7
 */
public interface ComplainDao extends BaseDao<Complain> {
    /**
     * 获取统计数据
     *
     * @param year
     * @return
     */
    List getannualStatisticChart(String year);
}