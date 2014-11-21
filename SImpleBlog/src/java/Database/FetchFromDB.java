/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rikysamuel
 */
@WebServlet(name = "DatabaseConnect", urlPatterns = {"/FetchFromDB"})
public class FetchFromDB extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        //JDBC Driver name and URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:/simpleblog/";
        
        Connection conn;
        
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n");
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
        
            //Open a connection
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/simpleblog","root","");
            try ( //Execute SQL Query
                    Statement stmt = (Statement) conn.createStatement()) {
                String sql = "SELECT * FROM posting";
                // Extract data from result set
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM posting")) {
                    // Extract data from result set
                    while(rs.next()){
                        //Retrieve by column name
                        int id  = rs.getInt("Id");
                        String judul = rs.getString("Judul");
                        Date tanggal = rs.getDate("Tanggal");
                        String content = rs.getString("Content");
                        String status = rs.getString("Status");
                        
                        //Display values
                        out.println("ID: " + id + "<br>");
                        out.println(", Judul: " + judul + "<br>");
                        out.println(", Tanggal: " + tanggal + "<br>");
                        out.println(", Content: " + content + "<br>");
                        out.println(", Status: " + status + "<br>");
                    }
                    out.println("</body></html>");
                }
            }
            conn.close();
            
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }

}
