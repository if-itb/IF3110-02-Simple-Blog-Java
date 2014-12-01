package wbd.tubesII;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@WebServlet(name = "SaveComment", urlPatterns = {"/SaveComment"})
public class SaveCommentServlet extends HttpServlet {

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
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SaveCommentServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SaveCommentServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
        
        Comment comment = new Comment();
        comment.setEmail(request.getParameter("email"));
        comment.setName(request.getParameter("nama"));
        comment.setTanggal(new Date());
        comment.setKomentar(request.getParameter("komentar"));
        comment.setId(Integer.valueOf(request.getParameter("idpost")));
        System.out.println(CommentDAO.save(comment));
//        if (!CommentDAO.save(comment)) {
//            request.getSession().setAttribute("AddNewPostStatus", "Post dengan judul \"" + request.getParameter("judul") + "\" gagal disimpan");
//        } else {
//            request.getSession().setAttribute("AddNewPostStatus", "Post dengan judul \"" + request.getParameter("judul") + "\" berhasil disimpan");
//        }
//        response.sendRedirect("AddNewPostStatus.jsp");
//        processRequest(request, response);
        ArrayList<Comment> comments = CommentDAO.get(Integer.valueOf(request.getParameter("idpost")));
        StringBuffer sb = new StringBuffer();
        for (Comment komentar : comments) {
            String name = komentar.getName();
            String date = komentar.getTanggal().toString();
            String isiKomentar = komentar.getKomentar();
//            sb.append("Nama: " + name + "<br>");
//            sb.append("Tanggal: " + date + "<br>");
//            sb.append("Komentar: " + isiKomentar + "<br><br>");
            sb.append("<li class='art-list-item'><div class='art-list-item-title-and-time'><h2 class='art-list-title'><a href='#'>"+name+"</a></h2><div class='art-list-time'>"+date+"</div></div><p>"+isiKomentar+"</p></li>");
        }        
        response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(sb.toString());
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
