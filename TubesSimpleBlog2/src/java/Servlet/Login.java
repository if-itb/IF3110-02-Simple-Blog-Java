/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class Login extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        System.out.println("masuk login");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String uname= request.getParameter("uname");
        String pass= request.getParameter("pass");
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "datapost";
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,"root","");
            String strQuery = "select * from user where username='" + uname + "' and password = '" + pass + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(strQuery);

            if(rs.next())
            {
               String msg = "login Successful";
               HttpSession session=request.getSession();
               session.setAttribute("user",uname);
               session.setAttribute("role",rs.getString("role"));
               session.setAttribute("email",rs.getString("email"));
                out.print(msg);
               int i = 0;
               Cookie[] cookies = request.getCookies();
               boolean found = false;
               while(i<cookies.length && !found)  
               {
                   if(cookies[i].getName().equalsIgnoreCase("user"))
                   {
                        found = true;
                   }
                   else
                   {
                        i++;
                   }
               }
               if(found)
               {
                   cookies[i].setMaxAge(0);
                   // Menciptakan cookies untuk guest      
                   Cookie guest = new Cookie("user",uname); 
                   // Meng - set agar cookie hilang setelah 24 jam
                   guest.setMaxAge(60*60*2); 
                   // Menambahkan cookie ke header response
                   response.addCookie(guest);
               }
               else
               {
                   // Menciptakan cookies untuk guest      
                   Cookie guest = new Cookie("user","guest"); 
                   // Meng - set agar cookie hilang setelah 24 jam
                   guest.setMaxAge(60*60*2); 
                   // Menambahkan cookie ke header response
                   response.addCookie(guest);
               }
               
               String site = new String("ViewProfile");
               response.setStatus(response.SC_MOVED_TEMPORARILY);
               response.setHeader("Location", site);
            }
            else
            {
                String msg = "login unsuccessful";
                RequestDispatcher rd = request.getRequestDispatcher("Login.xhtml");
                rd.include (request,response);
                 out.print(msg);
            }
            rs.close();
            st.close();
           
        /*try  {
            if(uname.equalsIgnoreCase("admin")&& pass.equalsIgnoreCase("admin")){
            HttpSession session = request.getSession();
            session.setAttribute("user", uname);
            RequestDispatcher rd = request.getRequestDispatcher("ViewProfile");
            rd.forward(request,response);
            }else{
                RequestDispatcher rd= request.getRequestDispatcher("Login.xhtml");
                rd.include(request, response);
            }
          */         
        }
       finally {} 
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
