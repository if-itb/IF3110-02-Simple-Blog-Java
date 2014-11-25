/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.database.MySQL;

/**
 *
 * @author christangga
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		MySQL mysql = new MySQL();

		User user = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<User> users = mysql.getAllUsers();
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				user = u;
				break;
			}
		}
		
		if (user != null) {
			Cookie usernameCookie = new Cookie("username", user.getUsername());
			Cookie passwordCookie = new Cookie("password", user.getPassword());
			Cookie roleCookie = new Cookie("role", user.getRole());
			usernameCookie.setMaxAge(30 * 60);
			passwordCookie.setMaxAge(30 * 60);
			roleCookie.setMaxAge(30 * 60);
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);
			response.addCookie(roleCookie);
			response.sendRedirect("index.xhtml");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.xhtml");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
