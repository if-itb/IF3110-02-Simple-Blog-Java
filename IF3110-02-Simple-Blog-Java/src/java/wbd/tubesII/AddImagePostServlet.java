package wbd.tubesII;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Asus
 */
@WebServlet(name = "AddImagePost", urlPatterns = {"/AddImagePost"})
@MultipartConfig(fileSizeThreshold=1024*1024*2,
                 maxFileSize=1024*1024*10,     
                 maxRequestSize=1024*1024*50,
                 location="E:\\workspace\\NetBeansProjects\\IF3110-02-Simple-Blog-Java\\IF3110-02-Simple-Blog-Java\\build\\web\\upload") // DEVELOPMENT ONLY!!! NEVER EVER EVER EVER USE THIS PATH IN PRODUCTION!!! EVER!!!
public class AddImagePostServlet extends HttpServlet {
    private static final String SAVE_DIR = "upload";
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
            out.println("<title>Servlet AddImagePostServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddImagePostServlet at " + request.getContextPath() + "</h1>");
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
            response.sendRedirect("PublishedPosts");
        } else {            
            Post editedPost = PostDAO.getPost(Integer.valueOf(id));
            if (editedPost != null) {
                request.getSession().setAttribute("editedImagePost", editedPost);
                response.sendRedirect("AddImagePost.jsp");
            } else {
                response.sendRedirect("PublishedPosts");
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
        
        String appPath = request.getServletContext().getRealPath("");
        System.out.println("APPPATH = " + appPath);
        String savePath = appPath + File.separator + SAVE_DIR;
        System.out.println("SAVEPATH = " + savePath);
        
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }                               
        Part part = request.getPart("file");        
        System.out.println(part.getName());
        System.out.println(part.getSize());
        System.out.println(part.getSubmittedFileName());
        String fileName = extractFileName(part);
        System.out.println("WRITEPATH = " + savePath + File.separator + fileName);
        System.out.println(fileName);
        part.write(fileName);        
        
        if (PostDAO.addImage(fileName, Integer.valueOf(request.getParameter("id")))) {
            request.getSession().setAttribute("ImagePostStatus", "Gambar post berhasil disimpan");
            response.sendRedirect("ImagePostStatus.jsp");
        } else {
            request.getSession().setAttribute("ImagePostStatus", "Gambar post tidak berhasil disimpan");
            response.sendRedirect("ImagePostStatus.jsp");
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
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
