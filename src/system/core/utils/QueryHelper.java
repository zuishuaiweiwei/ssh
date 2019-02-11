package system.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件查询工具类
 *
 * @author Administrator
 * @create 8/5
 */
public class QueryHelper {

    private String Where = "";
    private String From = "";
    private String OrderBy = "";
    private List<Object> parameters = new ArrayList();
    /**
     * 降序
     */
    public static String ORDER_BY_DESC = "DESC";
    /**
     * 升序
     */
    public static String ORDER_BY_ASC = "ASC";

    public QueryHelper(Class clazz) {
        this.From = "FROM " + clazz.getSimpleName();
    }

    /**
     * 添加where语句
     *
     * @param ret 字段名+条件 如 name = ?
     * @param obj ？的值
     */
    public void addWhere(String ret, Object... obj) {
        if (Where.length() > 1) {
            Where += " AND " + ret;
        } else {
            Where = " WHERE " + ret;
        }
        if (obj != null) {
            for (Object per : obj) {
                parameters.add(per);
            }
        }
    }

    /**
     * 添加排序规则
     *
     * @param name   字段名
     * @param method 排序方法 类的常量值
     */
    public void addOrderBy(String name, String method) {
        if (OrderBy.length() > 1) {
            OrderBy += "," + name + " " + method;
        } else {
            OrderBy = " ORDER BY " + name + " " + method;
        }
    }

    /**
     * 获取hql的参数集合
     *
     * @return
     */
    public List<Object> getParameters() {
        return parameters;
    }

    /**
     * 获取hql
     *
     * @return
     */
    public String getHql() {
        return From + Where + OrderBy;
    }

    /**
     * 获取总记录数
     */
    public String getTotalCountHql() {
        return "SELECT COUNT(*) " + From + Where;
    }
}