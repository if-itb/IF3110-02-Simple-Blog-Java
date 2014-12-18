package Validator;

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import Database.UserData;
 
/**
 * Filter checks if user is logged in by any role.
 * If it is not set then request is being redirected to the login.xhml page.
 *
 * @author Riva Syafri Rachmatullah
 *
 */
public class LoginFilter implements Filter {
 
    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserData loginBean = (UserData)((HttpServletRequest)request).getSession().getAttribute("UserData");
        String contextPath = ((HttpServletRequest)request).getContextPath();
        String path = ((HttpServletRequest) request).getRequestURI().substring(contextPath.length());
        if (loginBean == null || !loginBean.is_logged_in() || 
                ((path.startsWith("/faces/admin.xhtml") ||
                path.startsWith("/faces/edit_user.xhtml")) && 
                !loginBean.userLoggedIn().getRole().equalsIgnoreCase("admin"))) {
            ((HttpServletResponse)response).sendRedirect(contextPath + "/faces/login.xhtml");
        }
        chain.doFilter(request, response);    
    }
 
    public void init(FilterConfig config) throws ServletException {}
 
    public void destroy() {}    
}