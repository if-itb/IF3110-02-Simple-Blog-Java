/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tegar.databasehandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "AddCommentHandling", urlPatterns = {"/AddCommentHandling"})
public class AddCommentHandling extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // JDBC driver name and database URL
            String JDBC_DRIVER="com.mysql.jdbc.Driver";  
            String DB_URL="jdbc:mysql://localhost/simpleblog_withjava";
            //  Database credentials
            String USER = "root";
            String PASS = "";
            // Register JDBC driver
             Class.forName("com.mysql.jdbc.Driver");
             Connection conn = null;
             Statement stmt = null;
             // Open a connection
             conn = DriverManager.getConnection(DB_URL,USER,PASS);

             // Execute SQL query
             stmt = conn.createStatement();
             String sql,sql2;
             /*java.util.Calendar cal = java.util.Calendar.getInstance();
             java.util.Date utilDate = cal.getTime();
             java.sql.Date sqlDate = new Date(utilDate.getTime());*/
             java.util.Date utilDate1 = new java.util.Date();
             java.sql.Date sqlDate = new java.sql.Date(utilDate1.getTime());
             sql = "INSERT INTO komentar (post_id, komentar_nama, komentar_email, komentar_konten) VALUES ("
                     + request.getParameter("post_id")
                     + ","
                     + "\'"
                     + request.getParameter("Nama")
                     + "\'"
                     + ","
                     + "\'"
                     + request.getParameter("Email")
                     + "\'"
                     + ","
                     + "\'"
                     + request.getParameter("Komentar")
                     + "\')" 
                     ;
            System.out.println(sql);
             stmt.executeUpdate(sql);
             /*String site = new String("http://localhost:8080/IF3110-02-Simple-Blog-Java%202/");
             response.setStatus(response.SC_MOVED_TEMPORARILY);
             response.setHeader("Location", site);*/
            if (request.getParameter("user_id").isEmpty())
            {
                String site = new String("http://localhost:8080/IF3110-02-Simple-Blog-Java%202/PostGuest.jsp?post_id="
                        + request.getParameter("post_id")
                        + "");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
            else
            {
                String site = new String("http://localhost:8080/IF3110-02-Simple-Blog-Java%202/post.jsp?"
                        + "user_id="
                        + request.getParameter("user_id")
                        + "&"
                        + "post_id="
                        + request.getParameter("post_id")
                        + "");
                System.out.println(site);            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
            
//            sql = "SELECT * FROM komentar WHERE post_id='" + request.getParameter("post_id") + "'";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery(); 
//            while (rs.next())
//            {
//                out.println("<li class='art-list-item' id='komen'>");
//                   out.println("<div class='art-list-item-title-and-time'>");
//                       out.println("<h2 class='art-list-title'> <a href='#'>");
//                           out.println(rs.getString("komentar_nama"));                                                
//                       out.println("</a> </h2>");
//                       out.println("<div class='art-list-time'>");
//                           out.println(rs.getString("komentar_tanggal"));
//                       out.println("</div>");
//                   out.println("</div>");
//                   out.println("<p>");
//                       out.println(rs.getString("komentar_konten"));
//                   out.println("</p>");
//               out.println("</li>");
//            }
//            
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCommentHandling</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCommentHandling at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
        } catch (SQLException ex) {
            out.println("SQLEX : " + ex.getMessage());
            Logger.getLogger(AddCommentHandling.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            out.println("CNFEX : " + ex.getMessage());
            Logger.getLogger(AddCommentHandling.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
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
            throws ServletException, IOException {
        processRequest(request, response);
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
