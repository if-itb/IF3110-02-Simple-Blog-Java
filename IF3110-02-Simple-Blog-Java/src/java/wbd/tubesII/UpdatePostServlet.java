package wbd.tubesII;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "UpdatePost", urlPatterns = {"/UpdatePost"})
public class UpdatePostServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdatePostServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePostServlet at " + request.getContextPath() + "</h1>");
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
        
        String id = request.getParameter("id");
        
        if (request.getSession().getAttribute("currentUser") == null) {        
            response.sendRedirect("Login.jsp");
        } else {                   
            Post editedPost = PostDAO.getPost(Integer.valueOf(id));
            if (editedPost != null) {
                request.getSession().setAttribute("editedPost", editedPost);
                response.sendRedirect("EditPost.jsp");            
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
        
        try {            
            Post post = new Post();
            post.setId(Integer.valueOf(request.getParameter("id")));
            post.setJudul(request.getParameter("judul"));
            post.setTanggal(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("tanggal")));
            post.setKonten(request.getParameter("konten"));
            post.setStatus(request.getParameter("status"));
            
            if (!PostDAO.update(post)) {
                 request.getSession().setAttribute("UpdatePostStatus", "Post dengan judul \"" + request.getParameter("judul") + "\" gagal di-update");
            } else {
                request.getSession().setAttribute("UpdatePostStatus", "Post dengan judul \"" + request.getParameter("judul") + "\" berhasil di-update");
            }
            response.sendRedirect("UpdatePostStatus.jsp");
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
