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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KBK
 */
public class Post extends HttpServlet {

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
            sql = "SELECT * FROM blog WHERE id = " + request.getParameter("id");
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
               // Retrieve by column name
               int id  = rs.getInt("id");
               String judul = rs.getString("judul");
               String tanggal = rs.getString("tanggal");
               String konten = rs.getString("konten");
               
               out.println("<article class=\"art simple post\">");
               out.println("<header class=\"art-header\">");
               out.println("<div class=\"art-header-inner\" style=\"margin-top: 0px; opacity: 1;\">");
               out.println("<time class=\"art-time\">"+tanggal+"</time>");
               out.println("<h2 class=\"art-title\">"+judul+"</h2>");
               out.println("<p class=\"art-subtitle\"></p>");
               out.println("</div>");
               out.println("</header>");
               out.println("<div class=\"art-body\">");
               out.println("<div class=\"art-body-inner\">");
               out.println("<p>"+konten+"</p>");
               out.println("<hr />");
               out.println("<h2>Komentar</h2>");
               out.println("<div id=\"contact-area\">");
               out.println("<form method=\"post\" action=\"#\">");
               out.println("<label for=\"nama\">Nama:</label>");
               out.println("<input type=\"text\" name=\"nama\" id=\"nama\">");
               out.println("<label for=\"email\">Email:</label>");
               out.println("<input type=\"text\" name=\"email\" id=\"email\">");
               out.println("<label for=\"komentar\">Komentar:</label><br>");
               out.println("<textarea name=\"komentar\" rows=\"20\" cols=\"20\" id=\"komentar\"></textarea>");
               out.println("<input type=\"submit\" name=\"submit\" value=\"Kirim\" class=\"submit-button\">");
               out.println("</form>");
               out.println("</div>");
               out.println("<button id=\"loadKomentarButton\">Dapatkan Daftar Komentar</button>");
               out.println("<div id=\"comments\">");
               out.println("</div>");
               out.println("</div>");
               out.println("</div>");
               out.println("</article>");
               
              
               
            }
            
            
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
