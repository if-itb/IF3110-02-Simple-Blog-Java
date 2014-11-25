/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akhfa
 */
@WebServlet("/ButtonControllerServlet")
public class ButtonControllerServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        
        if(request.getParameter("addButton")!=null)
        {
            User tambahUser = new User(request.getParameter("username"), request.getParameter("password"), 
                                        request.getParameter("nama"), request.getParameter("email"), request.getParameter("role"));
            try {
                tambahUser.masukDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(ButtonControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("admin/user_management.jsp");
        }
        else if(request.getParameter("editButton") != null)
        {
            User tambahUser = new User(request.getParameter("username"), request.getParameter("password"), 
                                        request.getParameter("nama"), request.getParameter("email"), request.getParameter("role"));
            try {
                tambahUser.updateUser();
            } catch (SQLException ex) {
                Logger.getLogger(ButtonControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("admin/user_management.jsp");
        }
        else if(request.getParameter("delUser")!=null)
        {
            User hapusUser = new User();
            try
            {
                hapusUser.setUsername(request.getParameter("username"));
                hapusUser.deleteUser();
            }
            catch (SQLException ex) {
                Logger.getLogger(ButtonControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("admin/user_management.jsp");
        }
    }
    
}
