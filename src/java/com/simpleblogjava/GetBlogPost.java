/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simpleblogjava;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author KBK
 */
public class GetBlogPost extends HttpServlet {

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
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        String title = "Database Result";
//        String docType =
//            "<!doctype html public \"-//w3c//dtd html 4.0 " +
//            "transitional//en\">\n";
//        out.println(docType +
//            "<html>\n" +
//            "<head><title>" + title + "</title></head>\n" +
//            "<body bgcolor=\"#f0f0f0\">\n" +
//            "<h1 align=\"center\">" + title + "</h1>\n");
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Execute SQL query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM blog";
            ResultSet rs = stmt.executeQuery(sql);
            
            
            // Extract data from result set
            while(rs.next()){
               //Retrieve by column name
               int id  = rs.getInt("id");
               String judul = rs.getString("judul");
               String tanggal = rs.getString("tanggal");
               String konten = rs.getString("konten");

               //Display values
               out.println("<li class=\"art-list-item\">");
               out.println("<div class=\"art-list-item-title-and-time\">");
               out.println("<h2 class=\"art-list-title\"><a href=\"post.jsp?id="+id+"\">"+judul+"</a></h2>");
               out.println("<div class=\"art-list-time\">"+tanggal+"</div>");
               out.println("</div>");
               
               out.println("<p>"+konten+"</p>");
               out.println("<h2 class=\"art-list-title\"><a href=\"edit.jsp?id="+id+"\">Edit</a></h2>");
               out.println("</p>");
               
               out.println("<p>");
               out.println("<form method=\"post\" action=\"delete\">");
               out.println("<input type=\"hidden\" id=\"id\" name=\"id\" value=\""+id+">");
               out.println("<input type=\"submit\" class=\"a\" value=\"Hapus\" onclick=\"return confirm('Apakah Anda akan menghapus entri blog ini?')\">");
               out.println("</form>");
               out.println("</p>");
               
               out.println("</li>");
               
//               out.println("ID: " + id + "<br>");
//               out.println("Judul: " + judul + "<br>");
//               out.println("Tanggal: " + tanggal + "<br>");
//               out.println("Konten: " + konten + "<br>");
            }
//            out.println("</body></html>");

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
