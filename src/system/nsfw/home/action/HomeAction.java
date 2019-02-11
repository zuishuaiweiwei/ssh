package system.nsfw.home.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Administrator
 * @create 8/1
 */
public class HomeAction extends ActionSupport {


    public String frame() {
        return "frame";
    }

    public String left() {
        return "left";
    }

    public String top() {
        return "top";
    }

}