/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author akhfa
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
 
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        // get request parameters for userID and password
        username = request.getParameter("username");
        password = request.getParameter("password");
        
        User userLogin = new User(username, password);
        try {
            if(userLogin.successLogin()){
                Cookie loginCookie = new Cookie("username",username);
                Cookie roleCookie = new Cookie("role", userLogin.getRole());
                //setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(30*60);
                roleCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                response.addCookie(roleCookie);
                response.sendRedirect("admin/index.jsp");
            }else{
                response.sendRedirect("/IF3110-02-Simple-Blog-Java/login/index.html");
                PrintWriter out= response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
//                rd.include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
}
