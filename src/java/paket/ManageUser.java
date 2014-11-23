/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TOSHIBA
 */
public class ManageUser extends HttpServlet {

    String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
    String uName = "root";
    String pass = "";
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


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
        PrintWriter out = response.getWriter();
        Connection conn = null;
        Statement stmnt = null;
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
                out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT `usrname`, `nama_usr`, `email_usr`, `role` FROM `usr`";
            ResultSet rSet = stmnt.executeQuery(sqlStr);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script type=\"text/javascript\" src=\"assets/js/validate_delete.js\"></script>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            
            out.print("<table class='table table-striped'>");			
		out.print("<thead>");	
			out.print("<tr>");	  
				out.print("<th>Nama</th>");
                                out.print("<th>Email</th>");
                                out.print("<th>Role</th>");
                                out.print("<th>Tindakan</th>");
                        out.print("<tr>");
		out.print("<thead>");	
                    out.print("<tbody>");
                    
            while (rSet.next()){
                String usrname = rSet.getString("usrname");
                String nama_usr = rSet.getString("nama_usr");
                String email_usr = rSet.getString("email_usr");
                String role = rSet.getString("role");
                
                out.print("<tr>");
                    out.print("<td>"+nama_usr+"</td>");
                    out.print("<td>"+email_usr+"</td>");
                    out.print("<td>"+role+"</td>");
                    out.print("<td><p><a href=\"javascript:void(0)\" onclick=\"validatedelete("+usrname+")\">Hapus</a></p></td>");
                out.print("<tr>\n");
                
            }
                    out.print("</tbody>");
            out.println("</table>");
          out.println("</div>");
          out.println("</body>");
          out.println("</html>");
        }catch(Exception ex){
            ex.printStackTrace();
            out.println("Unable to connect to database");
            out.print(ex.toString());
        }finally {
            out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmnt != null) {
                    stmnt.close();
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
