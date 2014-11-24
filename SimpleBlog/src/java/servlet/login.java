/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A46CB
 */
public class login extends HttpServlet {

     private String databaseURL, username, password;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Retrieve the database-URL, username, password from webapp init parameters
        super.init(config);
        ServletContext context = config.getServletContext();
        databaseURL = "jdbc:mysql://localhost:3306/simpleblog";
        username = "root";
        password = "";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String userpassword = request.getParameter("password");
        String role = "";
        String nama = "";
	
         PrintWriter out = response.getWriter();
        boolean isUserExist = false;
        Connection conn = null;
        Statement stmt = null;
        String comment = "";
        try {
             try {
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(Exception e) {
                    out.println("Unable to load Driver");
                }
            // Step 1: Create a database "Connection" object
            conn = DriverManager.getConnection(databaseURL, username, password);
            
            // Step 2: Create a "Statement" object inside the "Connection"
            stmt = conn.createStatement();

            // Step 3: Execute a SQL SELECT query
            String sqlStr = "SELECT * FROM user WHERE email='"+email+"' AND password='"+userpassword+"'";
            
            ResultSet rset = stmt.executeQuery(sqlStr); 
            while (rset.next()) {
                role = rset.getString("role");
                nama = rset.getString("nama");
                isUserExist = true;
            }
      
            if (isUserExist) {                
                // Step 4: Set cookie and session
                HttpSession session = request.getSession();
                 session.setAttribute("email", email);
                 session.setAttribute("nama", nama);
                 session.setAttribute("role", role);
                 //setting session to expiry in 30 mins
                 //session.setMaxInactiveInterval(30 * 60);
                 Cookie cookie_email = new Cookie("email", email);
                 cookie_email.setMaxAge(2592000);
                 response.addCookie(cookie_email);
                 Cookie cookie_nama = new Cookie("nama", nama);
                 cookie_nama.setMaxAge(2592000);
                 response.addCookie(cookie_nama);
                 Cookie cookie_role = new Cookie("role", role);
                 cookie_role.setMaxAge(2592000);
                 response.addCookie(cookie_role);
                 
                 response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Unable to connect to database");
        } finally {
            out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
