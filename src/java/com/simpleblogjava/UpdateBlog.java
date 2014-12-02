/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simpleblogjava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KBK
 */
public class UpdateBlog extends HttpServlet {

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
        Statement stmt = null;
        Connection conn = null;
        
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
        final String DB_URL="jdbc:mysql://localhost/simpleblog";
        
        //  Database credentials
        final String USER = "root";
        final String PASS = "";
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Execute SQL query
            stmt = conn.createStatement();
            String sql;
            
            
            
            /* TODO output your page here. You may use following sample code. */
            String id_blog = request.getParameter("id");
            String judul = request.getParameter("judul");
            String tanggal = request.getParameter("tanggal");
            String konten = request.getParameter("konten");
            
            if(tanggal.isEmpty()){
                // Get current date
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
                tanggal = ft.format(dNow);
            }
            else{
                String[] splittedTanggal = tanggal.split("/");
                Date dNow = new Date();
                Date dInput = new Date(Integer.parseInt(splittedTanggal[0]), Integer.parseInt(splittedTanggal[1]), Integer.parseInt(splittedTanggal[2]));
            
                if(dInput.before(dNow)){
                    sql = "UPDATE `simpleblog`.`blog` SET `judul` = "+judul+", `tanggal` = "+tanggal+", `konten` = "+konten+""
                            + " WHERE `blog`.`id` = "+id_blog;
                    stmt.execute(sql);

                    response.setHeader("Location", "http://localhost:8080/simpleblog/index.jsp");
                }
                else{
                    response.sendRedirect("http://localhost:8080/simpleblog/new_post.jsp");
                }
            }
            

        }catch(Exception e){
            e.printStackTrace();
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
