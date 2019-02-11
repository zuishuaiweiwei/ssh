package system.nsfw.role.entity;


import java.io.Serializable;

/**
 * 角色权限类
 *
 * @author Administrator
 * @create 6/13
 */
public class RolePrivilege implements Serializable {
    private RolePrivilegeId id;

    public RolePrivilege() {
    }

    public RolePrivilege(RolePrivilegeId id) {
        this.id = id;
    }

    public RolePrivilegeId getId() {
        return id;
    }

    public void setId(RolePrivilegeId id) {
        this.id = id;
    }
}