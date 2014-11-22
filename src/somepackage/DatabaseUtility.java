package somepackage;

import java.sql.*;
import java.util.*;

public class DatabaseUtility {
	private static DatabaseUtility instance = new DatabaseUtility();
	Connection con;

	public UserDetails findUser(String username, String password) {
		ResultSet rs = null;
		PreparedStatement pst = null;

		if (con == null)
			con = getConnection();

		String stm = "Select * from user";
		UserDetails result = null;
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();

			while (rs.next()) {
				if (rs.getString(2).equals(username)
						&& rs.getString(3).equals(password))
					result = new UserDetails(rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Connection getConnection() {
		Connection connection = null;

		String url = "jdbc:mysql://localhost:3306/wbd_db";
		String user = "WBD_USER";
		String password = "QKC3zwhJ";

		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection completed");
		} catch (SQLException exception) {
			System.out.println("Connection FAILEDDDDD!!!!");
			System.out.println(exception.getMessage());
		}
		return connection;
	}

	private DatabaseUtility() {
	}

	public static DatabaseUtility getInstance() {
		return instance;
	}

}
