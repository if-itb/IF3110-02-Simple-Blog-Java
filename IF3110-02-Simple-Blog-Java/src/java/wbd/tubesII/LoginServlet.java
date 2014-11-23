package wbd.tubesII;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 *
 * @author Asus
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        try {                   
            HttpSession session = null;
            String sessionCookie = CookieManager.getCookieValue(request, "JSESSIONID");
            String persistentCookie = CookieManager.getCookieValue(request, "wbdahey");
            
            if (sessionCookie != null) {
                if (persistentCookie == null) {
                    CookieManager.addCookie(response, "wbdahey", sessionCookie, 3600*24*365*1000);
                }
            }
            
            if (persistentCookie != null) {
                HashMap<String, HttpSession> activeUsers = (HashMap<String, HttpSession>) request.getServletContext().getAttribute("activeUsers");
                session = activeUsers.get(persistentCookie);
            }
            
            if (session == null) {
                session = request.getSession();
            }            
            
            User user = (User)session.getAttribute("currentUser");
            if (user == null) {
                response.sendRedirect("Login.jsp");
            } else {
                session.setAttribute("currentUser", user);
                response.sendRedirect("UserLogged.jsp");
            }
            
//            String emailUser = "";
//            Cookie[] cookies = request.getCookies();
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("wbdahey")) {
//                    emailUser = cookie.getValue();
//                    break;
//                }
//            }
            
//            if (!emailUser.equals("")) {
//                request.getSession().setAttribute("currentUser", user);
//                response.sendRedirect("UserLogged.jsp");
//            } else {
//                response.sendRedirect("Login.jsp");
//            }                
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
            User user = new User();
            user.setEmail(URLDecoder.decode(request.getParameter("email"), "UTF-8"));
            user.setPassword(request.getParameter("password"));                                            
            
            user = UserDAO.login(user);
            
            if (user.getValid()) {                
                Cookie wbdCookie = new Cookie("wbdahey", user.getEmail());
                wbdCookie.setMaxAge(3600*24*365*1000);                
                response.addCookie(wbdCookie);
                                                
                request.getSession().setAttribute("currentUser", user);
//                this.getServletConfig().getServletContext().setAttribute("currentUser", user);
                        
                response.sendRedirect("UserLogged.jsp");
            } else if (!user.getValid()) {                
                response.sendRedirect("InvalidLogin.jsp");
            }
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
