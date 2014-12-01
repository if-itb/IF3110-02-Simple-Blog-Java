    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WBD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tony
 */

@WebServlet(name = "login", urlPatterns = {"/login"})



public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /* JDBC Driver name and database URL */
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/simpleblog2";
    
    /* Database credentials */
    static final String USER = "root";
    static final String PWD = "";
    
    Connection conn = null;
    Statement stmt = null;
    
    public login() throws SQLException, ClassNotFoundException
    {
        /* Register JDBC Driver */
        Class.forName("com.mysql.jdbc.Driver");
        
        /* Open a connection */
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PWD);
        System.out.println("Connected to database successfully...");
        
        /* Execute a query */
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            //out.println("<title>Servlet java</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet java at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try 
        {
            //  processRequest(r   equest, response);
            Boolean loginSuccess = false;
        
            String usernameInput = request.getParameter("username");
            String passwordInput = request.getParameter("password");
            
            String status = "";
            
            String sql = "SELECT `Username`,`Password`,`Status` FROM `user`";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next())
            {
                String username =rs.getString("Username");
                String password = rs.getString("Password");
                
                if (username.equals(usernameInput))
                {
                    if (password.equals(passwordInput))
                    {
                        loginSuccess = true;
                        break;
                    }
                }
            }
            
            if (loginSuccess)
            {
                Cookie cUser = new Cookie("username",usernameInput);
                Cookie cStatus = new Cookie("status",rs.getString("Status"));
                
                cUser.setMaxAge(60);
                cStatus.setMaxAge(60);
                
                response.addCookie(cUser);
                response.addCookie(cStatus);
                
                response.sendRedirect("index.jsp");
            }
            else
            {
                response.sendRedirect("index.jsp?invalid=yes");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
