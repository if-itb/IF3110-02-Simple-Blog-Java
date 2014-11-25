package controller;

import java.sql.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import constrain.Constant;
import entities.UserDetails;

public class DatabaseUtility {
	private static DatabaseUtility instance = new DatabaseUtility();
	private Connection con;

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

	/*addCharEntity and forHTML from: http://www.javapractices.com/topic/TopicAction.do?Id=96*/
	
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

	public static String forHTML(String aText) {
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(
				aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '&') {
				result.append("&amp;");
			} else if (character == '\"') {
				result.append("&quot;");
			} else if (character == '\t') {
				addCharEntity(9, result);
			} else if (character == '!') {
				addCharEntity(33, result);
			} else if (character == '#') {
				addCharEntity(35, result);
			} else if (character == '$') {
				addCharEntity(36, result);
			} else if (character == '%') {
				addCharEntity(37, result);
			} else if (character == '\'') {
				addCharEntity(39, result);
			} else if (character == '(') {
				addCharEntity(40, result);
			} else if (character == ')') {
				addCharEntity(41, result);
			} else if (character == '*') {
				addCharEntity(42, result);
			} else if (character == '+') {
				addCharEntity(43, result);
			} else if (character == ',') {
				addCharEntity(44, result);
			} else if (character == '-') {
				addCharEntity(45, result);
			} else if (character == '.') {
				addCharEntity(46, result);
			} else if (character == '/') {
				addCharEntity(47, result);
			} else if (character == ':') {
				addCharEntity(58, result);
			} else if (character == ';') {
				addCharEntity(59, result);
			} else if (character == '=') {
				addCharEntity(61, result);
			} else if (character == '?') {
				addCharEntity(63, result);
			} else if (character == '@') {
				addCharEntity(64, result);
			} else if (character == '[') {
				addCharEntity(91, result);
			} else if (character == '\\') {
				addCharEntity(92, result);
			} else if (character == ']') {
				addCharEntity(93, result);
			} else if (character == '^') {
				addCharEntity(94, result);
			} else if (character == '_') {
				addCharEntity(95, result);
			} else if (character == '`') {
				addCharEntity(96, result);
			} else if (character == '{') {
				addCharEntity(123, result);
			} else if (character == '|') {
				addCharEntity(124, result);
			} else if (character == '}') {
				addCharEntity(125, result);
			} else if (character == '~') {
				addCharEntity(126, result);
			} else {
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

}
