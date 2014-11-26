package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constrain.Constant;

/**
 * Servlet implementation class CommentPoster
 */
@WebServlet("/CommentPoster")
public class CommentPoster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentPoster() {
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
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		Date now = new Date(System.currentTimeMillis());
		String snow = new SimpleDateFormat("yyyy-MM-dd").format(now);

		Connection connection = null;

		String url = Constant.DATABASE_URL;
		String user = Constant.DATABASE_USER;
		String password = Constant.DATABASE_PASS;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, password);

		} catch (SQLException exception) {
			System.out.println("Database connection fail");
			System.out.println(exception.getMessage());
			// failed database response
			failedMessage(response);
			return;
		}

		String stm = "INSERT INTO `comment`(`id_post`,`isi`,`waktu`,`name`,`email`) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setInt(1, id);
			pst.setString(2, content);
			pst.setString(3, snow);
			pst.setString(4, name);
			pst.setString(5, email);
			pst.execute();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("SUCCESS");

		} catch (SQLException e) {
			System.out.println("Database update fail");
			System.out.println(e.getMessage());
			// failed database response
			failedMessage(response);
		}
	}

	private void failedMessage(HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Doomed in CommentPoster.failedMessage()");
		}
	}

}
