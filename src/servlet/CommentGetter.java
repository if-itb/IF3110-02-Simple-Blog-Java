package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constrain.Constant;

/**
 * Servlet implementation class CommentGetter
 */
@WebServlet("/CommentGetter")
public class CommentGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateString = "<div class=\"row\"><div class=\"col-xs-2 col-md-offset-1\"><div class=\"row\" style=\"font-size: 16px\"><strong>{0}</strong></div><br /><div class=\"row\"><a href=\"mailto:{1}\">{1}</a></div><br /><div class=\"row\">{2}</div></div><div class=\"col-xs-5 col-md-offset-1\">{3}</div></div><hr />";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentGetter() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String suserID = request.getParameter("id");
		int userID = Integer.parseInt(suserID);
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

		String stm = "SELECT * FROM `comment` WHERE `id_post` = ? ORDER BY `num` DESC";

		ResultSet rs = null;
		try {
			PreparedStatement pst = connection.prepareStatement(stm);
			pst.setInt(1, userID);
			pst.execute();
			rs = pst.getResultSet();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			while (rs.next()) {
				out.write(MessageFormat.format(templateString, rs.getString(5),
						rs.getString(6), rs.getString(4), rs.getString(3)));
			}

		} catch (SQLException e) {
			System.out.println("Database connection fail");
			System.out.println(e.getMessage());
			// failed database response
			failedMessage(response);
		}
	}

	private void failedMessage(HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write("Database failure");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("DOOMED in failedMessage()");
		}
	}

}
