package system.nsfw.user.entity;

import system.nsfw.role.entity.Role;

import java.io.Serializable;

/**
 * @author Administrator
 * @create 8/1
 */
public class UserRoleId implements Serializable {
    private Role role;
    private String user;

    public UserRoleId() {
    }

    public UserRoleId(Role role, String user) {

        this.role = role;
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleId that = (UserRoleId) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}