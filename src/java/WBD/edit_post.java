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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "edit_post", urlPatterns = {"/edit_post"})
public class edit_post extends HttpServlet {

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
    
    public edit_post() throws SQLException, ClassNotFoundException
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
            out.println("<title>Servlet edit_post</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet edit_post at " + request.getContextPath() + "</h1>");
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
            String post_id = request.getParameter("id");
            String sql = "SELECT * FROM `post` WHERE ID = '" + post_id + "'";
            
            ResultSet rs = stmt.executeQuery(sql);
            String[] record = new String[5];
                
            while (rs.next())
            {
                record[0] = rs.getString("ID");
                record[1] = rs.getString("Judul");
                record[2] = rs.getString("Tanggal");
                record[3] = rs.getString("Konten");
                record[4] = rs.getString("Status");
            }
            
            request.setAttribute("edit_data", record);
            response.sendRedirect("edit_post.jsp?edit=success");
        } catch (SQLException ex) {
            Logger.getLogger(edit_post.class.getName()).log(Level.SEVERE, null, ex);
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
            
            String id_ = request.getParameter("id_post");
            String judul_edit = request.getParameter("Judul");
            String tanggal_edit = request.getParameter("Tanggal");
            String konten_edit = request.getParameter("Konten");
            
            System.out.println(id_);
            System.out.println(judul_edit);
            System.out.println(tanggal_edit);
            System.out.println(konten_edit);
            String sql = "UPDATE `post` SET Judul='"+judul_edit+"', Tanggal='"+tanggal_edit+"', Konten='"+konten_edit+"' WHERE ID = '"+id_+"'";
            stmt.executeUpdate(sql);
            
            sql = "SELECT * FROM `post` WHERE ID = '"+id_+"'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next())
            {
                if (rs.getString("Status").equals("U"))
                {
                    response.sendRedirect("publish_post.jsp?edit=success");
                }
                else if (rs.getString("Status").equals("P"))
                {
                    response.sendRedirect("index.jsp?edit=success");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(edit_post.class.getName()).log(Level.SEVERE, null, ex);
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
