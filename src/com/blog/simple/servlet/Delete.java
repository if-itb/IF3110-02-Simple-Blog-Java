package com.blog.simple.servlet;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            String connectionURL = "jdbc:mysql://localhost/db_simpleblog";
            java.sql.Connection conn = null; 
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            conn = DriverManager.getConnection(connectionURL, "root", "");
            if(!conn.isClosed()) {
            	java.sql.Statement stmt = conn.createStatement ();
                stmt.executeUpdate("DELETE FROM post WHERE id="+request.getParameter("id"));
				stmt.close();
            }
            conn.close();
        }catch(Exception ex){
            System.out.println("Unable to connect to database"+ex);
        }
		response.sendRedirect("Home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
