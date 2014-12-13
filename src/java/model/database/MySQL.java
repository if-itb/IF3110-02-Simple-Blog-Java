/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Comment;
import model.Post;
import model.User;

/**
 *
 * @author christangga
 */
public class MySQL {

	private static final String DATABASE_NAME = "simple_blog_jsf";

	private static final String TABLE_USER = "user";
	private static final String TABLE_POST = "post";
	private static final String TABLE_COMMENT = "comment";

	private Connection connect;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public MySQL() {
		open();
	}

	public Connection getConnect() {
		return connect;
	}

	public Statement getStatement() {
		return statement;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	private void open() {
		try {
			// this will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// setup the connection with the DB.
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost/simple_blog_jsf", "root", "");

			// statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			// preparedStatements can use variables and are more efficient
			//preparedStatement = connect.prepareStatement();
			// resultSet gets the result of the SQL query
			//resultSet = preparedStatement.executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// User
	public boolean createUser(String email, String username, String password, String role) {
		// kalo user di delete terus di create ulang masih bisa, harusnya ga bisa
		try {
			List<User> users = getAllUsers();
			for (User user : users) {
				if (user.getUsername().equalsIgnoreCase(username)) {
					return false;
				}
			}

			preparedStatement = connect.prepareStatement("INSERT INTO " + TABLE_USER + "(email, username, password, role, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, role);
			preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		try {
			preparedStatement = connect.prepareStatement("SELECT id, email, username, password, role, deleted_at FROM " + TABLE_USER + " ORDER BY username");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getString("deleted_at"));
				if (resultSet.getString("deleted_at") == null) {
					User user = new User();

					user.setId(resultSet.getInt("id"));
					user.setEmail(resultSet.getString("email"));
					user.setUsername(resultSet.getString("username"));
					user.setPassword(resultSet.getString("password"));
					user.setRole(resultSet.getString("role"));
					users.add(user);
				}
			}
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}

		return users;
	}

	public User getUser(int id) {
		User user = new User();
		try {
			preparedStatement = connect.prepareStatement("SELECT email, username, password, role, deleted_at FROM " + TABLE_USER + " WHERE id = " + id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("deleted_at") == null) {
					user.setId(id);
					user.setEmail(resultSet.getString("email"));
					user.setUsername(resultSet.getString("username"));
					user.setPassword(resultSet.getString("password"));
					user.setRole(resultSet.getString("role"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public boolean updateUser(int id, String email, String username, String password, String role) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE " + TABLE_USER + " SET email = ?, username = ?, password = ?, role = ?, updated_at = ? WHERE id = " + id);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, role);
			preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean deleteUser(int id) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE " + TABLE_USER + " SET deleted_at = ? WHERE id = " + id);
			preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	// Post
	public boolean createPost(String title, long date, String content) {
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO " + TABLE_POST + "(title, date, content, status, created_at, updated_at) VALUES(?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, title);
			preparedStatement.setDate(2, new Date(date));
			preparedStatement.setString(3, content);
			preparedStatement.setBoolean(4, false);
			preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public List<Post> getAllPosts() {
		List<Post> posts = new ArrayList<>();

		try {
			preparedStatement = connect.prepareStatement("SELECT id, title, date, content, status, deleted_at FROM " + TABLE_POST + " ORDER BY updated_at DESC");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("deleted_at") == null) {
					Post post = new Post();

					post.setId(resultSet.getInt("id"));
					post.setTitle(resultSet.getString("title"));
					post.setDate(resultSet.getDate("date").getTime());
					post.setContent(resultSet.getString("content"));
					post.setStatus(resultSet.getBoolean("status"));
					posts.add(post);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return posts;
	}

	public List<Post> getAllPublishedPosts() {
		List<Post> posts = new ArrayList<>();

		try {
			preparedStatement = connect.prepareStatement("SELECT id, title, date, content, deleted_at FROM " + TABLE_POST + " WHERE status = 1 ORDER BY updated_at DESC");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("deleted_at") == null) {
					Post post = new Post();

					post.setId(resultSet.getInt("id"));
					post.setTitle(resultSet.getString("title"));
					post.setDate(resultSet.getDate("date").getTime());
					post.setContent(resultSet.getString("content"));
					post.setStatus(true);
					posts.add(post);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return posts;
	}

	public List<Post> getAllUnpublishedPosts() {
		List<Post> posts = new ArrayList<>();

		try {
			preparedStatement = connect.prepareStatement("SELECT id, title, date, content, deleted_at FROM " + TABLE_POST + " WHERE status = 0 ORDER BY updated_at DESC");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("deleted_at") == null) {
					Post post = new Post();

					post.setId(resultSet.getInt("id"));
					post.setTitle(resultSet.getString("title"));
					post.setDate(resultSet.getDate("date").getTime());
					post.setContent(resultSet.getString("content"));
					post.setStatus(false);
					posts.add(post);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return posts;
	}

	public List<Post> getAllDeletedPosts() {
		List<Post> posts = new ArrayList<>();

		try {
			preparedStatement = connect.prepareStatement("SELECT id, title, date, content, status FROM " + TABLE_POST + " WHERE deleted_at IS NOT NULL ORDER BY updated_at DESC");
			resultSet = preparedStatement.executeQuery();
			System.out.println("woi");
			while (resultSet.next()) {
				Post post = new Post();

				post.setId(resultSet.getInt("id"));
				post.setTitle(resultSet.getString("title"));
				post.setDate(resultSet.getDate("date").getTime());
				post.setContent(resultSet.getString("content"));
				post.setStatus(resultSet.getBoolean("status"));
				posts.add(post);
				System.out.println(post.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return posts;
	}

	public Post getPost(int id) {
		Post post = new Post();
		try {
			preparedStatement = connect.prepareStatement("SELECT title, date, content, status, deleted_at FROM " + TABLE_POST + " WHERE id = " + id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("deleted_at") == null) {
					post.setId(id);
					post.setTitle(resultSet.getString("title"));
					post.setDate(resultSet.getDate("date").getTime());
					post.setContent(resultSet.getString("content"));
					post.setStatus(resultSet.getBoolean("status"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return post;
	}

	public boolean updatePost(int id, String title, long date, String content, boolean status) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE " + TABLE_POST + " SET title = ?, date = ?, content = ?, status = ?, updated_at = ? WHERE id = " + id);
			preparedStatement.setString(1, title);
			preparedStatement.setDate(2, new Date(date));
			preparedStatement.setString(3, content);
			preparedStatement.setBoolean(4, status);
			preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean deletePost(int id) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE " + TABLE_POST + " SET deleted_at = ? WHERE id = " + id);
			preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();

			deleteComment(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean restorePost(int id) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE " + TABLE_POST + " SET deleted_at = ?, updated_at = ? WHERE id = " + id);
			preparedStatement.setTimestamp(1, null);
			preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	// Comment
	public boolean createComment(int post_id, String name, String email, String comment) {
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO " + TABLE_COMMENT + "(post_id, name, email, comment, created_at) VALUES(?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, post_id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, comment);
			preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public List<Comment> getAllComments(int post_id) {
		List<Comment> comments = new ArrayList<>();

		try {
			preparedStatement = connect.prepareStatement("SELECT id, name, email, comment, deleted_at, created_at FROM " + TABLE_COMMENT + " WHERE post_id = " + post_id + " ORDER BY created_at DESC");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("deleted_at") == null) {
					Comment comment = new Comment();

					comment.setId(resultSet.getInt("id"));
					comment.setPost_id(post_id);
					comment.setName(resultSet.getString("name"));
					comment.setEmail(resultSet.getString("email"));
					comment.setComment(resultSet.getString("comment"));
					comment.setDate(resultSet.getTimestamp("created_at").getTime());
					comments.add(comment);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comments;
	}

	public boolean deleteComment(int id) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE " + TABLE_COMMENT + " SET deleted_at = ? WHERE id = " + id);
			preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()), Calendar.getInstance());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
