package system.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色类
 *
 * @author Administrator
 * @create 6/13
 */
public class Role implements Serializable {
    private String roleId;
    private String name;
    private String state;
    private Set<RolePrivilege> privilege;
    public final static String ROLE_STATE_VALID = "1";
    public final static String ROLE_STATE_INVALID = "0";

    public Role() {
    }

    public Role(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RolePrivilege> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Set<RolePrivilege> privilege) {
        this.privilege = privilege;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}