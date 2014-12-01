package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseUtility;

/**
 * Servlet implementation class PostUpdater
 */
@WebServlet("/PostUpdater")
public class PostUpdater extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostUpdater() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String date = request.getParameter("date");

		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		
		Connection con = dbUtil.getLiveConnection();
		String query = "UPDATE `post` SET `judul`=?, `isi`=?, `waktu`=? WHERE `id`=?";
		PreparedStatement pst;
		System.out
				.printf("UPDATE `post` SET `judul`=%s, `isi`=%s, `waktu`=%s WHERE `id`=%d\n",
						title, content, date, id);

		try {
			pst = con.prepareStatement(query);
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, date);
			pst.setInt(4, id);
			pst.execute();
			response.sendRedirect("index.jsf");
			
		} catch (SQLException e) {
			System.out.println("Query Failed");
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
