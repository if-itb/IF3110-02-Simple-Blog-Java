package wbd.tubesII;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asep Saepudin
 */
@WebServlet(name = "UpdateUser", urlPatterns = {"/UpdateUser"})
public class UpdateUserServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String email = URLDecoder.decode(request.getParameter("email"), "UTF-8");
        
        if (request.getSession().getAttribute("currentUser") == null ||
                !((User)request.getSession().getAttribute("currentUser")).getRole().equals("Admin") ||
                email.equals("") || email == null) {        
            response.sendRedirect("Login.jsp");
        } else {                   
            User editedUser = UserDAO.getUser(email);
            if (editedUser != null) {
                request.getSession().setAttribute("editedUser", editedUser);
                response.sendRedirect("EditUser.jsp");            
            } else {
                response.sendRedirect("Login.jsp");
            }
        }
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
               
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setRole(request.getParameter("role"));
        
        if (UserDAO.update(user)) {
            request.getSession().setAttribute("updateUser", "User dengan id " + request.getParameter("id") + " berhasil di-update");
            response.sendRedirect("SuccessfullyUpdated.jsp");
        } else {
            request.getSession().setAttribute("updateUser", "Gagal meng-update user dengan id " + request.getParameter("id"));
            response.sendRedirect("UpdateFailed.jsp");                
        }
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
