package controller;

import java.sql.*;

import constrain.Constant;
import entities.UserDetails;

public class DatabaseUtility {
	private static DatabaseUtility instance = new DatabaseUtility();
	Connection con;

	public UserDetails findUser(String username, String password) {
		ResultSet rs = null;
		PreparedStatement pst = null;

		if (con == null)
			con = getConnection();

		String stm = "SELECT * FROM user";
		UserDetails result = null;
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();

			while (rs.next()) {
				if (rs.getString(2).equals(username)
						&& rs.getString(3).equals(password))
					result = new UserDetails(rs.getString(4), rs.getString(5), rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet execute(String stm) {
		ResultSet rs = null;
		PreparedStatement pst = null;

		if (con == null)
			con = getConnection();

		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	

	private Connection getConnection() {
		Connection connection = null;

		String url = Constant.DATABASE_URL;
		String user = Constant.DATABASE_USER;
		String password = Constant.DATABASE_PASS;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Database connection completed");
		} catch (SQLException exception) {
			System.out.println("Database connection fail");
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
