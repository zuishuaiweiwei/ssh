package system.exception;

/**
 * @author Administrator
 * @create 6/12
 */
public class SysException extends Exception {
    private String errorMessages;

    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public SysException() {
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}