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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tegar
 */
@WebServlet(name = "EditPostHandling", urlPatterns = {"/EditPostHandling"})
public class EditPostHandling extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
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
             String sql = "UPDATE post SET "
                     + "judul=\""
                     + request.getParameter("Judul")
                     + "\",tanggal=\""
                     + request.getParameter("Tanggal")
                     + "\",konten=\""
                     + request.getParameter("Konten")
                     + "\" WHERE post_id="
                     + request.getParameter("Post_Id");
             System.out.println(sql);
             stmt.executeUpdate(sql);
            if ("1".equals(request.getParameter("Role")))
            {
                String site = new String("http://localhost:8080/IF3110-02-Simple-Blog-Java%202/home-owner.jsp");                
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);

            }
            else if ("2".equals(request.getParameter("Role")))
            {
                String site = new String("http://localhost:8080/IF3110-02-Simple-Blog-Java%202/home-editor.jsp");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
            else
            {
                String site = new String("http://localhost:8080/IF3110-02-Simple-Blog-Java%202/home-admin.jsp");            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditPostHandling.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPostHandling.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditPostHandling.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditPostHandling.class.getName()).log(Level.SEVERE, null, ex);
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
