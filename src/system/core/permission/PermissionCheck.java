package system.core.permission;

import system.nsfw.role.entity.Role;
import system.nsfw.role.entity.RolePrivilege;
import system.nsfw.user.entity.User;
import system.nsfw.user.entity.UserRole;
import system.nsfw.user.service.UserService;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @create 8/2
 */
public class PermissionCheck {

    @Resource
    UserService userService;

    /**
     * 判断user是否有权限
     *
     * @param user
     * @param flag 要操作的业务code
     * @return
     */
    public boolean isAccessible(User user, String flag) {

        //遍历权限列表
        for (UserRole list : user.getUserRoleList()) {
            //用户的每个角色
            Role role = list.getId().getRole();
            //遍历角色权限
            for (RolePrivilege pr : role.getPrivilege()) {
                //有权限返回true
                if (flag.equals(pr.getId().getCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}