//LogoutServelt.java(for logout)


package ExamplePackage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ExamplePackage.UserBean;

public class LogoutServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   public void doGet(HttpServletRequest request, HttpServletResponse response) 
              throws ServletException, java.io.IOException {

   try
   {       

        UserBean user = new UserBean();
        user.removeUsername();
        user.removePassword();
         HttpSession session=request.getSession(false);
         session.removeAttribute("currentSessionUser");
         session.invalidate();
         response.sendRedirect("LoginPage.jsp");
   }      
   catch (Throwable theException)        
   {
        System.out.println(theException); 
   }
  }
}
