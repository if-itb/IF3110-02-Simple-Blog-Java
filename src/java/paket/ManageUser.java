/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        
        
	Cookie[] cookies = request.getCookies();
      // Get an array of Cookies associated with this domain
        
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
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                    "xmlns:h=\"http://xmlns.jcp.org/jsf/html\"\n"+
                    "xmlns:f=\"http://xmlns.jcp.org/jsf/core\">");
            out.println("<f:view>");
            out.println("<head>");
            out.println("<script type=\"text/javascript\" src=\"assets/js/validate_delete.js\"></script>");
            out.println("<title>Simple Blog | Manajemen User</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            if (cookies!=null){
                String role1 = null;
                for(Cookie cookie : cookies){
                    if("role".equals(cookie.getName())){
                        role1 = cookie.getValue();
                    }
                }
                if ("admin".equals(role1)){
                            out.print("<table class='table table-striped'>");			
                            out.print("<thead>");	
                                    out.print("<tr>");	  
                                            out.print("<th>Nama</th>");
                                            out.print("<th>Email</th>");
                                            out.print("<th>Role</th>");
                                            out.print("<th>Tindakan</th>");
                                    out.print("<tr>");
                            out.print("</thead>");	
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
                                out.print("<td><p><a href=\"DeleteUser?param="+usrname+"\"><img src=\"assets/img/DeleteRed.png\"></a></p></td>");
                            out.print("<tr>\n");

                        }
                            out.print("</tbody>");
                        }
                        else{
                            out.println("<p>Anda harus masuk sebagai admin!</p>");
                        }
            }else{
                out.println("<p>Anda harus masuk sebagai admin!</p>");
            }
             out.println("</div>");
             out.println("</body>");
             out.println("</f:view>");
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
