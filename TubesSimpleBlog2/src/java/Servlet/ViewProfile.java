/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class ViewProfile extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" 
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\"" 
				+ " xmlns:h=\"http://xmlns.jcp.org/jsf/html\"" 
				+ "	xmlns:f=\"http://xmlns.jcp.org/jsf/core\">"
				+ "<link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\" /> "
                + "<title>User Profile</title>"
                + "<div class=\"header\">Simple Blog</div>"
                + "<h1> View Profile</h1>"
                + "<hr></hr>");
        HttpSession session = request.getSession(false);
        
        try {
        if(session!=null){
           out.print("<p>Welcome "+session.getAttribute("user") );
           out.print("<br/> Kamu " + session.getAttribute("role")+"</p>"
				   + "<div><br/><h:form><h:commandLink action=\"index\" value=\"Go to Homepage\">"
				   + "<f:param id=\"thisuser\" value=\""+ session.getAttribute("user") +"\"></f:param>"
				   + "<f:param id=\"thisrole\" value=\""+ session.getAttribute("role") +"\"></f:param>"
				   + "</h:commandLink></h:form></div>"
                   + "<div class=\"footer\">rita | cilvia | linda </div></html>");
        }   else{
        RequestDispatcher rd= request.getRequestDispatcher("Login.xhtml");
        rd.include(request, response);
        }
           
        }
        finally{}
     
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
