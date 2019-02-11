package system.exception;

/**
 * @author Administrator
 * @create 6/12
 */
public class ActionException extends SysException {
    public ActionException() {
        super("请求出现错误");
    }

    public ActionException(String message) {
        super(message);
    }
}