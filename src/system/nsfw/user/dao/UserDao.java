package system.nsfw.user.dao;

import system.core.dao.BaseDao;
import system.nsfw.user.entity.User;
import system.nsfw.user.entity.UserRole;

import java.util.List;


/**
 * @author Administrator
 * @create 6/8
 */
public interface UserDao extends BaseDao<User> {
    /**
     * 账号唯一性检验
     *
     * @param account
     * @param id
     * @return
     */
    Boolean doVerify(String account, String id);

    /**
     * 保存用户权限
     *
     * @param userRole
     */
    void saveUserRole(UserRole userRole);

    /**
     * 删除用户的权限
     *
     * @param userRole
     */
    void deleteUserRole(String id);

    /**
     * 获取用户的权限列表
     *
     * @param id
     * @return
     */
    List<UserRole> getUserRoleListById(String id);

    /**
     * 通过账号密码查找用户
     *
     * @param user
     * @return
     */
    List<User> findUserByAccountAndPsd(User user);
}
