package system.nsfw.role.dao;


import system.core.dao.BaseDao;
import system.nsfw.role.entity.Role;

import java.io.Serializable;

/**
 * @author Administrator
 * @create 7/31
 */
public interface RoleDao extends BaseDao<Role> {

    /**
     * 删除角色权限
     *
     * @param id
     */
    void deletePrivileges(Serializable id);
}