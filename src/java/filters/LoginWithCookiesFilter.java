/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import services.CookieService;

public class LoginWithCookiesFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*HttpSession session = ((HttpServletRequest) request).getSession();
        User userIdentity = (User) session.getAttribute("userIdentity");
        if (userIdentity == null || userIdentity.getIsGuest()){
            CookieService.loginWithCookies((HttpServletRequest) request,(HttpServletResponse) response);
        }*/
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() { }

}
