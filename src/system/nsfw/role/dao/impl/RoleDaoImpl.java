package system.nsfw.role.dao.impl;


import org.hibernate.Query;
import system.core.dao.impl.BaseDaoImpl;
import system.nsfw.role.dao.RoleDao;
import system.nsfw.role.entity.Role;

import java.io.Serializable;


/**
 * @author Administrator
 * @create 7/31
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public void deletePrivileges(Serializable id) {
        Query query = getSessionFactory().getCurrentSession().createQuery("DELETE FROM RolePrivilege where id.role.roleId=?");
        query.setParameter(0, id);
        query.executeUpdate();

    }
}