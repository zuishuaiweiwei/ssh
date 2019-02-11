package system.filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import system.core.constant.Constant;
import system.core.permission.PermissionCheck;
import system.nsfw.user.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆验证和权限验证
 *
 * @author Administrator
 * @create 8/2
 */
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String flag = "login_";
        String nsfw = "nsfw";
        if (uri.contains(flag)) {
            //如果是登陆类的操作直接放行
            filterChain.doFilter(request, response);
        } else {
            //获取session中user的信息
            User user = (User) request.getSession(false).getAttribute(Constant.USER);
            if (user != null) {
                //判断是否是nsfw的业务
                if (uri.contains(nsfw)) {
                    //
                    WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                    //取出spring自己创建的ioc容器
                    PermissionCheck pk = (PermissionCheck) applicationContext.getBean("permissionCheck");
                    //判断是否有权限
                    if (pk.isAccessible(user, nsfw)) {
                        //有权限，放行
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/NotPermission.jsp");
                    }
                } else {
                    filterChain.doFilter(request, response);
                }
            } else {
                response.sendRedirect(request.getContextPath());
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}