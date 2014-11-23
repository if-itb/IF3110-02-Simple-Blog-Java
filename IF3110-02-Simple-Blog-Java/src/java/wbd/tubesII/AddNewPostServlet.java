package wbd.tubesII;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asep Saepudin
 */
@WebServlet(name = "AddNewPost", urlPatterns = {"/AddNewPost"})
public class AddNewPostServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewPostServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewPostServlet at " + request.getContextPath() + "</h1>");
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
        
        if (request.getSession().getAttribute("currentUser") == null ||
                ((User)request.getSession().getAttribute("currentUser")).getRole().equals("Editor")) {
            response.sendRedirect("UserLogged.jsp");
        } else {            
            response.sendRedirect("AddNewPost.jsp");
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
        
        try {            
            Post post = new Post();
            post.setJudul(request.getParameter("judul"));
            post.setTanggal(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("tanggal")));            
            post.setKonten(request.getParameter("konten"));
            post.setStatus("Unpublished");
            
            if (!PostDAO.add(post)) {
                 request.getSession().setAttribute("AddNewPostStatus", "Post dengan judul \"" + request.getParameter("judul") + "\" gagal disimpan");
            } else {
                request.getSession().setAttribute("AddNewPostStatus", "Post dengan judul \"" + request.getParameter("judul") + "\" berhasil disimpan");
            }
            response.sendRedirect("AddNewPostStatus.jsp");
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AddNewPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
