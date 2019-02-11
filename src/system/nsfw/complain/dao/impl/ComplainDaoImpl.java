package system.nsfw.complain.dao.impl;

import org.hibernate.SQLQuery;
import system.core.dao.impl.BaseDaoImpl;
import system.nsfw.complain.dao.ComplainDao;
import system.nsfw.complain.entity.Complain;

import java.util.List;

/**
 * @author Administrator
 * @create 8/7
 */
public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

    @Override
    public List getannualStatisticChart(String year) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT imonth ,COUNT(comp_id) ")
                .append("FROM ssh_0607_month LEFT JOIN ssh_0607_complain ON imonth=MONTH(comp_time) ")
                .append("AND YEAR(comp_time)=? ")
                .append("GROUP BY imonth");
        SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sb.toString());
        query.setParameter(0, year);
        return query.list();
    }
}