package system.core.action;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @create 6/12
 */
public class SysResultSuport extends StrutsResultSupport {

    @Override
    protected void doExecute(String s, ActionInvocation actionInvocation) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        BaseAction action = (BaseAction) actionInvocation.getAction();
        System.out.println("SysResultSuport");
    }
}