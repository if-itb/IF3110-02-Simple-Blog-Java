package filter;

import controller.PostController;
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
import model.Post;
import model.User;

/**
 * 
 * @author Ahmad Zaky
 * Filter untuk halaman Edit Post (edit_post.xhtml)
 */
public class EditPostFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        PostController postController = (PostController) req.getSession().getAttribute("postController");
        Post post = postController == null ? null : postController.getPost();
        UserController userController = (UserController) req.getSession().getAttribute("userController");
        User user = userController == null ? null : userController.getUser();
        
        /*
         * Post hanya bisa diedit oleh:
         * * admin dan editor, atau
         * * owner jika post tersebut telah dipublish
        */
        if (user != null && post != null && (user.getRole().equals(User.ROLE_ADMIN) || user.getRole().equals(User.ROLE_EDITOR) || user.getRole().equals(User.ROLE_OWNER)&& post.getPublished()==1)) {
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
