package system.nsfw.user.entity;

import java.io.Serializable;

/**
 * @author Administrator
 * @create 8/1
 */
public class UserRole implements Serializable {
    private UserRoleId id;

    public UserRole(UserRoleId id) {
        this.id = id;
    }

    public UserRole() {
    }

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }
}