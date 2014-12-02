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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KBK
 */
public class LoadComments extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Execute SQL query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM komentar WHERE id_blog = " + request.getParameter("id_blog");
            ResultSet rs = stmt.executeQuery(sql);
            
            out.println("<ul class=\"art-list-body\">");
            while(rs.next()){
               // Retrieve by column name
               // nama kolom masih belum benar!
//               int id  = rs.getInt("id");
               String nama = rs.getString("nama");
               String tanggal = rs.getString("tanggal");
               String konten = rs.getString("konten");
               
               out.println("<li class=\"art-list-item\">");
               out.println("<div class=\"art-list-item-title-and-time\">");
               out.println("<h2 class=\"art-list-title\"><a href=\"post.html\">"+nama+"</a></h2>");
               out.println("<div class=\"art-list-time\">"+tanggal+"</div>");
               out.println("</div>");
               out.println("<p>"+konten+"</p>");
               out.println("</li>");
            }
            out.println("</ul>");
            
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
         try{
            if(stmt!=null)
               stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
               if(conn!=null)
               conn.close();
            }catch(SQLException se){
               se.printStackTrace();
            }//end finally try
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
