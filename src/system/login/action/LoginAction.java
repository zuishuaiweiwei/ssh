package system.login.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import system.core.constant.Constant;
import system.nsfw.user.entity.User;
import system.nsfw.user.entity.UserRole;
import system.nsfw.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @create 8/1
 */
public class LoginAction extends ActionSupport implements ModelDriven {
    public User user = new User();
    @Resource
    public UserService userService;

    /**
     * 跳转登陆页面
     *
     * @return
     */
    public String loginUi() {
        return "loginUi";
    }

    /**
     * 登陆
     *
     * @return
     */
    public String login() {
        if (user != null) {
            List<User> userList = userService.findUserByAccountAndPsd(user);
            Log log = LogFactory.getLog(getClass());
            if (userList != null && userList.size() > 0) {
                user = userList.get(0);
                List<UserRole> userRoleList = userService.getUserRoleListById(user.getId());
                user.setUserRoleList(userRoleList);
                ActionContext.getContext().getSession().put(Constant.USER, user);
                log.info("账户为" + userList.get(0).getAccount() + "的用户登陆了系统");
            }
            return "success";
        } else {
            return "error";
        }

    }

    /**
     * 退出登录
     *
     * @return
     */
    public String outLogin() {
        ActionContext.getContext().getSession().remove(Constant.USER);
        return "error";
    }

    @Override
    public Object getModel() {
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}