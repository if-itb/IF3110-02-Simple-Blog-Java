/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author A46CB
 */
public class add_comment extends HttpServlet {

    private String databaseURL, username, password;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Retrieve the database-URL, username, password from webapp init parameters
        super.init(config);
        ServletContext context = config.getServletContext();
        databaseURL = "jdbc:mysql://localhost:3306/simpleblog";
        username = "root";
        password = "";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_post = Integer.parseInt(request.getParameter("id_post"));
        String nama = request.getParameter("nama");
        String email = request.getParameter("email");
        String komentar = request.getParameter("komentar");
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();	
        String tanggal = dateFormat.format(date);
        
         PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        String comment = "";
        try {
             try {
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(Exception e) {
                    out.println("Unable to load Driver");
                }
            // Step 1: Create a database "Connection" object
            conn = DriverManager.getConnection(databaseURL, username, password);
            
            // Step 2: Create a "Statement" object inside the "Connection"
            stmt = conn.createStatement();

            // Step 3: Execute a SQL SELECT query
            String sqlStr = "INSERT INTO komentar (nama, email, tanggal, komentar, id_post) VALUES ('"+nama+"', '"+email+"', '"+tanggal+"', '"+komentar+"', '"+id_post+"')";
            
            try {
            stmt.executeUpdate(sqlStr); // Send the query to the server
            }catch (SQLException ex) {
                out.print(ex.toString());
            }
            
            
                comment = comment + "<li class=\"art-list-item\"><div class=\"art-list-item-title-and-time\"><h2 class=\"art-list-title\">";
                comment = comment + "<a href=\"#\">"+nama+"</a></h2>";
                comment = comment + "<div class=\"art-list-time\">"+tanggal+"</div></div><p>"+komentar+"</p></li>";
            
      
            out.print(comment);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Unable to connect to database");
        } finally {
            out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
