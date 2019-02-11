package system.exception;

/**
 * @author Administrator
 * @create 6/12
 */
public class ServiceException extends SysException {
    public ServiceException() {
        super("业务操作错误");
    }

    public ServiceException(String message) {
        super(message);
    }
}