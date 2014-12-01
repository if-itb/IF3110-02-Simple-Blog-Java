/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WBD;

import static WBD.login.DB_URL;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
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
 *
 * @author Tony
 */
@WebServlet(name = "komentar", urlPatterns = {"/komentar"})
public class komentar extends HttpServlet {

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
    
    public komentar() throws SQLException, ClassNotFoundException
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
            out.println("<title>Servlet komentar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet komentar at " + request.getContextPath() + "</h1>");
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
        try {
            String id_post = request.getParameter("id");
            long time = System.currentTimeMillis()/1000L;
            
            String sql = "SELECT * FROM `comment` WHERE Post_ID = '" +id_post+ "' ORDER BY Tanggal DESC";
            ResultSet rs = stmt.executeQuery(sql);
            
            PrintWriter out = new PrintWriter(response.getWriter());
            
            while (rs.next())
            {
                String[] record = new String[6];
                
                record[0] = rs.getString("ID");
                record[1] = rs.getString("Post_ID");
                record[2] = rs.getString("Nama");
                record[3] = rs.getString("Email");
                record[4] = rs.getString("Komentar");
                record[5] = rs.getString("Tanggal");
                out.print("<ul class= \"art-list-body\">"
                    + "<li class =\"art-list-item\">"
                    + "<div class =\"art-list-item-title-and-time\">"
                    + "<h2 class =\"art-list-title\">"
                    + record[2]
                    +"</h2>"
                    +"<div class =\"art-list-time\">");
                
                time = System.currentTimeMillis()/1000L;
                long deltaTime = time - Long.parseLong(record[5]);
                if (deltaTime <5)
                {
                    out.print("Just now");
                }
                else if (deltaTime < 10)
                {
                    out.print("few seconds ago");
                }
                else if (deltaTime < 60)
                {
                    out.print(deltaTime + " seconds ago");
                }
                else if (deltaTime < 3600)
                {
                    out.print(deltaTime/60+" minutes ago");
                }
                else if (deltaTime < 3600*24)
                {
                    out.println(deltaTime/(60*24)+" hours ago");
                }
                else
                {
                    Date date = new Date(Long.parseLong(record[5])*1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                    String formattedDate = sdf.format(date);
                    out.println(formattedDate);
                }
                out.print("</div>"
                          +"</div>" + "<p>" + record[4].replaceAll("\n", "<br>")+"</p>"
                          +"</li>"+"</ul>");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(komentar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException {
        try {
            String id_post = request.getParameter("ID");
            String nama = request.getParameter("Name");
            String email = request.getParameter("Email");
            String comment = request.getParameter("Comment");
            long time = System.currentTimeMillis()/1000L;
            
            String sql = "INSERT INTO `comment` VALUES (NULL,'"+id_post+"','"+nama+"','"+email+"','"+comment+"','"+time+"')";
            stmt.executeUpdate(sql);
            
            PrintWriter out = new PrintWriter(response.getWriter());
            
            sql = "SELECT * FROM `comment` WHERE Post_ID = '" +id_post+ "' ORDER BY Tanggal DESC";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next())
            {
                String[] record = new String[6];
                
                record[0] = rs.getString("ID");
                record[1] = rs.getString("Post_ID");
                record[2] = rs.getString("Nama");
                record[3] = rs.getString("Email");
                record[4] = rs.getString("Komentar");
                record[5] = rs.getString("Tanggal");
                out.print("<ul class= \"art-list-body\">"
                    + "<li class =\"art-list-item\">"
                    + "<div class =\"art-list-item-title-and-time\">"
                    + "<h2 class =\"art-list-title\">"
                    + record[2]
                    +"</h2>"
                    +"<div class =\"art-list-time\">");
                
                time = System.currentTimeMillis()/1000L;
                long deltaTime = time - Long.parseLong(record[5]);
                if (deltaTime <5)
                {
                    out.print("Just now");
                }
                else if (deltaTime < 10)
                {
                    out.print("few seconds ago");
                }
                else if (deltaTime < 60)
                {
                    out.print(deltaTime+" seconds ago");
                }
                else if (deltaTime < 3600)
                {
                    out.print(deltaTime/60+" minutes ago");
                }
                else if (deltaTime < 3600*24)
                {
                    out.println(deltaTime/(60*24)+" hours ago");
                }
                else
                {
                    Date date = new Date(Long.parseLong(record[5])*1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                    String formattedDate = sdf.format(date);
                    out.println(formattedDate);
                }
                out.print("</div>"
                          +"</div>" + "<p>" + record[4].replaceAll("\n", "<br>")+"</p>"
                          +"</li>"+"</ul>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(komentar.class.getName()).log(Level.SEVERE, null, ex);
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
