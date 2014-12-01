package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

public class DatabaseAccess {

	private static DatabaseAccess instance;
	private Connection conn;
	private Statement statement;
	// JDBC driver name and database URL
	final String jdbcDriver;
	String DB_URL;

	// Database credentials
	String USER;
	String PASS;

	private DatabaseAccess() {

		this.jdbcDriver = "com.mysql.jdbc.Driver";
		this.DB_URL = "jdbc:mysql://localhost/simple_blog";

		this.USER = "root";
		this.PASS = "";

	}

	public void setUsername(String username) {
		this.USER = username;
	}

	public void setPassword(String password) {
		this.PASS = password;
	}

	public void setDatabase(String databaseName) {
		this.DB_URL = "jdbc:mysql://localhost/" + databaseName;
	}

	public static DatabaseAccess getInstance() {
		if (instance == null) {
			instance = new DatabaseAccess();
		}
		return instance;
	}

	public void openConnection() throws SQLException {
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			this.conn = DriverManager.getConnection(this.DB_URL, this.USER,
					this.PASS);
		} catch (SQLException se) {
			System.out.println("Cannot open connection");
			throw se;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC not found");
		}
		this.statement = this.conn.createStatement();
	}

	public void closeConnection() throws SQLException {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public ResultSet executeFetchQuery(String query) {
		ResultSet resultSet = null;
		try {
			resultSet = this.statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public void executeUpdateQuery(String query) {
                System.out.println(query);
		ResultSet resultSet = null;
		try {
			this.statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void insertRecords(String tableName, ArrayList<String> columns,
			ArrayList<String> values) throws SQLException {
		StringBuilder query = new StringBuilder("INSERT INTO " + tableName
				+ " (");
		for (int i = 0; i < columns.size(); i++) {
			query.append(columns.get(i));
			if (i != columns.size() - 1) {
				query.append(",");
			} else {
				query.append(")");
			}
		}
		query.append(" VALUES (");
		for (int i = 0; i < values.size(); i++) {
			query.append(values.get(i));
			if (i != values.size() - 1) {
				query.append(",");
			} else {
				query.append(")");
			}
		}
		query.append(";");
                System.out.println(query);
		this.statement.executeUpdate(query.toString());
	}

	public void deleteRecords(String tableName, String condition)
			throws SQLException {
		String query = "DELETE FROM " + tableName;
		if (condition != null) {
			query += " WHERE " + condition;
		}
		query += ";";
                System.out.println(query);
		this.statement.executeUpdate(query);
	}

	public ResultSet selectRecords(String tableName, ArrayList<String> columns,
			String condition) throws SQLException {
		StringBuilder query = new StringBuilder("SELECT ");
		for (int i = 0; i < columns.size(); i++) {
			query.append(columns.get(i));
			if (i != columns.size() - 1) {
				query.append(",");
			}
		}
		query.append(" FROM " + tableName);
		if (condition != null) {
			query.append(" WHERE " + condition);
		}
		query.append(";");
		ResultSet resultSet = this.statement.executeQuery(query.toString());
		return resultSet;
	}

	public ResultSet selectAllRecords(String tableName, String condition)
			throws SQLException {
		StringBuilder query = new StringBuilder("SELECT * FROM " + tableName);
		if (condition != null) {
			query.append(" WHERE " + condition);
		}
		query.append(";");
                System.out.println(query);
		ResultSet resultSet = this.statement.executeQuery(query.toString());
		return resultSet;
	}

	public void updateRecords(String tableName, ArrayList<String> column,
			ArrayList<String> value, String condition) throws SQLException {
		StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
		for (int i = 0; i < column.size(); i++) {
			query.append(column.get(i) + "=" + value.get(i));
			if (i != column.size() - 1) {
				query.append(",");
			}
		}
                if (condition!=null){
                    query.append(" WHERE " + condition);
                }
                query.append(";");
                System.out.println(query);
		this.statement.executeUpdate(query.toString());
	}
}