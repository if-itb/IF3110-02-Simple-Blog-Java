package com.blog.simple.servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			java.util.Date date = new SimpleDateFormat("dd/M/yyyy", Locale.ENGLISH).parse(request.getParameter("Tanggal"));
            DateFormat df = new SimpleDateFormat("yyyy-M-dd");
            String tanggal = df.format(date);
			String connectionURL = "jdbc:mysql://localhost/db_simpleblog";
            java.sql.Connection conn = null; 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionURL, "root", "");
            if(!conn.isClosed()) {
            	java.sql.Statement stmt = conn.createStatement ();
            	stmt.executeUpdate("UPDATE post SET judul='"+request.getParameter("Judul")+"', tanggal='"+tanggal+"', konten='"+request.getParameter("Konten")+"' WHERE id="+request.getParameter("Id"));
            	stmt.close();
            }
            conn.close();
        }catch(Exception ex){
            System.out.println("Unable to connect to database"+ex);
        }
		response.sendRedirect("Home.jsp");
	}
}
