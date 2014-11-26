//LoginServlet.java(takes input from LoginPage.jsp and controls the flow based on validation with database)


package ExamplePackage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2562294252731783855L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, java.io.IOException {
			try {
				UserBean user = new UserBean();
				user.setUserName(request.getParameter("un"));
				user.setPassword(request.getParameter("pw"));
				user = UserDAO.login(user);
				if (user.isValid()) {
					HttpSession session = request.getSession(true);
					session.setAttribute("currentSessionUser",user);
					response.sendRedirect("userLogged.jsp"); //logged-in page
				}
				else response.sendRedirect("invalidLogin.jsp"); //error page
			}
			catch (Throwable theException) {
				System.out.println(theException);
			}
       }
}