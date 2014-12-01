package com.blog.simple.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Utility class for standard DAO methods
 * @author Alvin Natawiguna
 *
 */
public final class DAOUtil {
	private DAOUtil() {
		// hiding constructor, since this is a utility class
	}
	
	public static PreparedStatement prepareStatement(
			Connection connection, String sql, boolean returnGeneratedKeys, Object...values)
		throws SQLException 
	{
		PreparedStatement ps = connection.prepareStatement(sql, 
			returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		setValues(ps, values);
		
		return ps;
	}
	
	public static void setValues(PreparedStatement ps, Object...values) throws SQLException {
		for(int i = 0; i < values.length; i++) {
			ps.setObject(i+1, values[i]);
		}
	}
	
	public static Date toSqlDate(java.util.Date date) {
		return (date != null) ? new Date(date.getTime()) : null;
	}
	
	public static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Closing Connection failed: " + e);
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println("Closing Statement failed: " + e);
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.err.println("Closing ResultSet failed: " + e);
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection connection, Statement statement) {
		close(connection);
		close(statement);
	}
	
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		close(connection);
		close(statement);
		close(resultSet);
	}
}
