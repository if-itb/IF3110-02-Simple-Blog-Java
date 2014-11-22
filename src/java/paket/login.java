/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author TOSHIBA
 */
public class login extends HttpServlet {

    private String databaseURL, username, password;
    String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException{
        
        super.init(config);
      ServletContext context = config.getServletContext();
      databaseURL = context.getInitParameter("databaseURL");
      username = context.getInitParameter("usrname");
      password = context.getInitParameter("passwrd");
        
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
        PrintWriter out = response.getWriter();
        Connection conn = null;
        Statement stmnt = null;
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
                out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT * FROM `usr` WHERE usrname =" + "'" + request.getParameter("usrname") + "'";
            
            ResultSet rset = stmnt.executeQuery(sqlStr);
            
            if(!rset.first()){
                out.print("username tidak ada");
            }else{
                String passwrd1 = rset.getString("passwrd");
                String role = rset.getString("role");
                if (passwrd1.equals(request.getParameter("passwrd"))){
                    //create session
                    HttpSession session = request.getSession();
                    
                    session.setAttribute("owner", request.getParameter("usrname"));
                    session.setAttribute("role", role);
                    String name = rset.getString("nama_usr");
                    //set session age
                    Cookie cookie = new Cookie ("user", name);
                    cookie.setMaxAge(3600);
                    Cookie cookie2 = new Cookie("role", role);
                    cookie2.setMaxAge(3600);
                    response.addCookie(cookie);
                    response.addCookie(cookie2);
                    
                    out.print("success");
                    response.sendRedirect("index.xhtml");
                }else{
                    out.print("password salah!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Unable to connect to database");
            out.print(ex.toString());
        }finally {
            out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmnt != null) {
                    stmnt.close();
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
        doGet(request, response);
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
