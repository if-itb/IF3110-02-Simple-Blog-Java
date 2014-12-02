package filter;

import controller.UserController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 * 
 * @author Ahmad Zaky
 * Filter untuk halaman New Post (new_post.xhtml)
 */
public class NewPostFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        UserController userController = (UserController) req.getSession().getAttribute("userController");
        User user = userController == null ? null : userController.getUser();
        /*
         * Post hanya bisa ditambah oleh admin dan owner
        */        
        if (user != null && (user.getRole().equals(User.ROLE_ADMIN) || user.getRole().equals(User.ROLE_OWNER))) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("index.xhtml");
        }        
    }

    @Override
    public void destroy() {
        
    }
}
