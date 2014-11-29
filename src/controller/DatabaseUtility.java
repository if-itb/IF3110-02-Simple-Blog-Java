package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import constrain.Constant;
import entities.UserDetails;

public class DatabaseUtility {
	private static void addCharEntity(Integer aIdx, StringBuilder aBuilder) {
		String padding = "";
		if (aIdx <= 9) {
			padding = "00";
		} else if (aIdx <= 99) {
			padding = "0";
		} else {
			// no prefix
		}
		String number = padding + aIdx.toString();
		aBuilder.append("&#" + number + ";");
	}

	public static DatabaseUtility getInstance() {
		return instance;
	}

	private static DatabaseUtility instance = new DatabaseUtility();

	private Connection con;

	private DatabaseUtility() {
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
						&& rs.getString(3).equals(password)) {
					result = new UserDetails(rs.getString(4), rs.getString(5),
							rs.getInt(1));
					result.setRole(rs.getInt(6));
					result.setPassword(rs.getString(3));
					result.setUsername(rs.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * addCharEntity and forHTML from:
	 * http://www.javapractices.com/topic/TopicAction.do?Id=96
	 */

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

	public Connection getLiveConnection() {
		return con;
	}

}
