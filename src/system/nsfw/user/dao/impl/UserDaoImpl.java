package system.nsfw.user.dao.impl;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import system.core.dao.impl.BaseDaoImpl;
import system.nsfw.user.dao.UserDao;
import system.nsfw.user.entity.User;
import system.nsfw.user.entity.UserRole;

import java.util.List;


/**
 * @author Administrator
 * @create 6/8
 */

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    /**
     * 账号唯一性
     *
     * @param account
     * @param id
     * @return
     */
    @Override
    public Boolean doVerify(String account, String id) {
        String sql = " from User where account=? ";
        if (StringUtils.isNotBlank(id)) {
            sql += "AND id!=?";
        }
        Query query = getSessionFactory().getCurrentSession().createQuery(sql);
        query.setParameter(0, account);
        if (StringUtils.isNotBlank(id)) {
            query.setParameter(1, id);
        }
        boolean empty = query.list().isEmpty();
        int size = query.list().size();
        if (query.list().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        getSessionFactory().getCurrentSession().save(userRole);
    }

    @Override
    public void deleteUserRole(String id) {
        Query query = getSessionFactory().getCurrentSession().createQuery("DELETE FROM UserRole WHERE id.user=?");
        query.setParameter(0, id);
        query.executeUpdate();
    }

    @Override
    public List<UserRole> getUserRoleListById(String id) {
        Query query = getSessionFactory().getCurrentSession().createQuery("FROM UserRole WHERE id.user=?");
        query.setParameter(0, id);
        List<UserRole> list = query.list();
        return list;
    }

    @Override
    public List<User> findUserByAccountAndPsd(User user) {
        Query query = getSessionFactory().getCurrentSession().createQuery("FROM User WHERE account=? and password=?");
        query.setParameter(0, user.getAccount());
        query.setParameter(1, user.getPassword());
        return query.list();
    }


}