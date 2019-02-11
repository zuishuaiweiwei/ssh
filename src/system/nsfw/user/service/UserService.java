package system.nsfw.user.service;


import system.core.service.BaseService;
import system.nsfw.user.entity.User;
import system.nsfw.user.entity.UserRole;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.util.List;

/**
 * @author Administrator
 * @create 6/8
 */
public interface UserService extends BaseService<User> {


    /**
     * 导出数据到excel文档
     *
     * @param userList
     * @param outputStream
     */
    void exportForExcle(List<User> userList, ServletOutputStream outputStream);

    /**
     * 导入excel文档
     *
     * @param headImgs
     * @param headImgsFileName
     */
    void importExcel(File headImgs, String headImgsFileName);

    /**
     * 账号唯一验证
     *
     * @param account
     * @param id
     * @return
     */
    boolean doVerify(String account, String id);

    /**
     * 保存用户和权限
     *
     * @param user
     * @param userRoleIds
     */
    void saveUserAndRole(User user, String... userRoleIds);

    /**
     * 更新用户和权限
     *
     * @param user
     * @param userRoleIds
     */
    void updateUserAndRole(User user, String... userRoleIds);

    /**
     * 获取用户的权限列表
     *
     * @param id
     * @return
     */
    List<UserRole> getUserRoleListById(String id);

    /**
     * 根据用户名和密码查找用户
     *
     * @param user
     * @return
     */
    List<User> findUserByAccountAndPsd(User user);

    /**
     * 删除用户的角色
     *
     * @param id
     */
    void deleteUserRole(String id);
}