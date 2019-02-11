package system.nsfw.role.service.impl;

import org.springframework.stereotype.Service;
import system.core.service.impl.BaseServiceImpl;
import system.nsfw.role.dao.RoleDao;
import system.nsfw.role.entity.Role;
import system.nsfw.role.service.RoleService;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @create 7/31
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {


    private RoleDao roleDao;

    @Resource
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }


    @Override
    public void update(Role role) {
        roleDao.deletePrivileges(role.getRoleId());
        roleDao.update(role);
    }


}