package org.wbd.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import org.wbd.model.*;

public class DatabaseHelper {
	public static final String URL = "jdbc:mysql://localhost:3306/simple_blog";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	
	private static DatabaseHelper instance;
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet result;
	
	private DatabaseHelper() {
		conn = null;
		statement = null;
		result = null;
	}
	
	public static DatabaseHelper getInstance() {
		if(instance == null) {
			instance = new DatabaseHelper();
		}
		return instance;
	}
	
	public ArrayList<User> getUsers() {
		try {
			connectDatabase(URL, USER, PASSWORD);
			
			String query = "SELECT * FROM user";
			ArrayList<User> users = new ArrayList<User>();
			statement = conn.prepareStatement(query);
			statement.executeQuery();
			result = statement.getResultSet();
			
			while(result.next()) {
				User user = new User();
				user.setUsername(result.getString(2));
				user.setPassword(result.getString(3));
				user.setRole(result.getString(4));
				users.add(user);
			}
			
			clearResult();
			clearStatement();
			disconnectDatabase();
			return users;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public ArrayList<Post> getPosts(String username) {
		try {
			connectDatabase(URL, USER, PASSWORD);
			
			String query = "SELECT post.title, post.date, post.content"
					+ " FROM user JOIN post"
					+ "WHERE user.username = ? AND"
					+ "user.id = post.user_id";
			ArrayList<Post> posts = new ArrayList<Post>();
			
			statement = conn.prepareStatement(query);
			statement.setString(1, username);
			statement.executeQuery();
			result = statement.getResultSet();
			
			while(result.next()) {
				Post post = new Post();
				post.setAuthor(username);
				post.setTitle(result.getString(1));
				post.setDate(Date.valueOf(result.getString(2)));
				post.setContent(result.getString(3));
				posts.add(post);
			}
			
			clearResult();
			clearStatement();
			disconnectDatabase();
			return posts;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public ArrayList<Comment> getComments(String username, String postTitle) {
		try {
			connectDatabase(URL, USER, PASSWORD);
			
			String query = "SELECT comment.sender, comment.date, comment.content"
					+ " FROM user JOIN post JOIN comment"
					+ "WHERE user.username = ? AND"
					+ "post.title = ? AND"
					+ "user.id = post.user_id AND"
					+ "post.id = comment.post_id";
			ArrayList<Comment> comments = new ArrayList<Comment>();
			
			statement = conn.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, postTitle);
			statement.executeQuery();
			result = statement.getResultSet();
			
			while(result.next()) {
				Comment comment = new Comment();
				comment.setSender(result.getString(1));
				comment.setDate(result.getDate(2));
				comment.setContent(result.getString(3));
				comments.add(comment);
			}
			
			clearResult();
			clearStatement();
			disconnectDatabase();
			return comments;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	private void connectDatabase(String url, String user, String password) {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			Logger logger = Logger.getLogger(DriverManager.class.getName());
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	private void disconnectDatabase() {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			Logger logger = Logger.getLogger(DriverManager.class.getName());
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	private void clearStatement() {
		try {
			if(statement != null) {
				statement.close();
			}
		} catch (SQLException ex) {
			Logger logger = Logger.getLogger(PreparedStatement.class.getName());
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	private void clearResult() {
		try {
			if(result != null) {
				result.close();
			}
		} catch (SQLException ex) {
			Logger logger = Logger.getLogger(ResultSet.class.getName());
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
}
