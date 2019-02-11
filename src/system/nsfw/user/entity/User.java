package system.nsfw.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @create 6/8
 */
public class User implements Serializable {
    private String id;
    private String dept;
    private String name;
    private String account;
    private String password;
    private String headImg;
    private Boolean gender;
    private String state;
    private String phone;
    private String email;

    private List<UserRole> userRoleList;

    private Date birthday;
    private String memo;
    /**
     * 用户有效
     */
    public final static String USER_STATE_VALID = "1";
    /**
     * 用户无效
     */
    public final static String USER_STATE_INVALID = "0";

    public User(String id, String name, String account, String password,
                String headImg, Boolean gender, String state, String phone,
                String email, Date birthday, String memo) {
        this.id = id;
        this.name = name;

        this.account = account;
        this.password = password;
        this.headImg = headImg;
        this.gender = gender;
        this.state = state;
        this.phone = phone;
        this.email = email;
        this.birthday = (Date) birthday.clone();
        this.memo = memo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public User() {
    }

    public Date getBirthday() {

        if (birthday != null) {
            return new Date(birthday.getTime());
        }
        return null;
    }


    public void setBirthday(Date birthday) {

        if (birthday != null) {
            this.birthday = (Date) birthday.clone();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", dept='" + dept + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", headImg='" + headImg + '\'' +
                ", gender=" + gender +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", memo='" + memo + '\'' +
                '}';
    }
}