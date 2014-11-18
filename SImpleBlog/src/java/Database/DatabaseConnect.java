/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
@WebServlet(name = "DatabaseConnect", urlPatterns = {"/db"})
public class DatabaseConnect extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String title = "Database Result";
            /* TODO output your page here. You may use following sample code. */
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
            out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" 
                    + "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n");
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
        //JDBC Driver name and URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/simpleblog";
        
        //Database credentials
        final String USER = "root";
        final String PASSWORD = "";
        
        processRequest(request, response);
        
        try{
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            
            //Open a connection
            Connection conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASSWORD);
            
            //Execute SQL Query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM posting";
            ResultSet rs = stmt.executeQuery(sql);
            
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
            
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            
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
            
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
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
