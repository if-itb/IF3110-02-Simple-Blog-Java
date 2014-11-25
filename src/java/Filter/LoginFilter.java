package Filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevinyu
 */

public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String isLogin = (String) req.getSession().getAttribute("isLogin");
        if ((isLogin!=null) && (isLogin.equals("yes"))) {
            chain.doFilter(request,response);
        }
        else {
            HttpServletResponse res = (HttpServletResponse)response;
            res.sendRedirect("/Simple-Blog/login.jsf");
        }
    }

    @Override
    public void destroy() {
    }
    
}
