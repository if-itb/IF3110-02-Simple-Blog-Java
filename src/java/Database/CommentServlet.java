package Database;

import Model.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Riva Syafri Rachmatullah
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {

    private MySQL db = new MySQL();
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
            out.println("INI KEMBALIANNYA, 5000");
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
        String col[] = {"pid", "name", "email", "content", "time"};
        String val[] = new String[5];
        val[0] = request.getParameter("id");
        val[1] = request.getParameter("name");
        val[2] = request.getParameter("email");
        val[3] = request.getParameter("content");
        java.util.Date date = new java.util.Date();
        val[4] = new Timestamp(date.getTime()).toString();
        this.db.Insert("comment", col, val);
        response.getOutputStream().print("true");
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            this.db.Where("pid=", request.getParameter("id"));
            ResultSet Data = this.db.Select("comment");
            boolean isExist = Data.first();
            List<Comment> ListComment = new LinkedList();
            while (isExist) {
                int id = Data.getInt("id");
                int pid = Data.getInt("pid");
                String name = Data.getString("name");
                String email = Data.getString("email");
                String content = Data.getString("content");
                Timestamp time = Data.getTimestamp("time");
                Comment comment = new Comment(id, pid, name, email, content, time);
                ListComment.add(comment);
                isExist = Data.next();
            }
            JSONArray array = new JSONArray(ListComment);
            response.getOutputStream().print(array.toString());
        } catch (Exception e) {
            response.getOutputStream().print("{\"error\": \"" + e.getMessage() + "\"}");
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
