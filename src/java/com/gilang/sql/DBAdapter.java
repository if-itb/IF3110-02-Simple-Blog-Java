/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilang.sql;

import com.gilang.beans.Komentar;
import com.gilang.beans.Post;
import com.gilang.beans.UserData;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gilang
 */
@ManagedBean(name="sql", eager=false)
@SessionScoped
public class DBAdapter {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement prepStatement;
	private ResultSet resultSet;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/tubes_wbd";
	private String user = "root";
	private String pass = "";
	
	public DBAdapter(){
		try{
			Class.forName(driver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Post getPost(int postID){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM post WHERE post_id = " + postID);
			if(resultSet.next()){
				int post_id = resultSet.getInt("post_id");
				String user_id = resultSet.getString("user_id");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				String date = resultSet.getDate("date").toString();
				boolean published = resultSet.getBoolean("published");
				boolean deleted = resultSet.getBoolean("deleted");
				Post post = new Post(post_id, user_id, title, content, date, published, deleted);
				return post;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Komentar> getComments(int post_id){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			List<Komentar> komentarList = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM comment WHERE post_id="+post_id);
			while(resultSet.next()){
				int comment_id = Integer.parseInt(resultSet.getString("comment_id"));
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String content = resultSet.getString("content");
				String date = resultSet.getDate("date").toString();
				komentarList.add(new Komentar(comment_id, post_id, name, email, content, date));
			}
			return komentarList;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Komentar getComment(int postID, int commentID){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT comment_id, name, email, content, date FROM comment WHERE post_id="
												+ postID + " and comment_id=" + commentID);
			if(resultSet.next()){
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String content = resultSet.getString("content");
				String date = resultSet.getString("date");
				Komentar comment = new Komentar(commentID, postID, name, email, content, date);
				return comment;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
        
	public List<Post> getPosts(boolean unPublished){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			List<Post> postList = new ArrayList<>();
			statement = connection.createStatement();
			if(unPublished)
				resultSet = statement.executeQuery("SELECT * FROM post WHERE published=0 and deleted=0");
			else
				resultSet = statement.executeQuery("SELECT * FROM post WHERE published=1 and deleted=0");
			while(resultSet.next()){
				int post_id = resultSet.getInt("post_id");
				String user_id = resultSet.getString("user_id");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				String date = resultSet.getDate("date").toString();
				boolean published = resultSet.getBoolean("published");
				boolean deleted = resultSet.getBoolean("deleted");
				postList.add(new Post(post_id, user_id, title, content, date, published, deleted));
			}
			return postList;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Post> getDeletedPosts(){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			List<Post> postList = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM post WHERE deleted=1");
			while(resultSet.next()){
				int post_id = resultSet.getInt("post_id");
				String user_id = resultSet.getString("user_id");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				String date = resultSet.getDate("date").toString();
				boolean published = resultSet.getBoolean("published");
				boolean deleted = resultSet.getBoolean("deleted");
				postList.add(new Post(post_id, user_id, title, content, date, published, deleted));
			}
			return postList;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public UserData getUser(String userID){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT user_id, role, email FROM user WHERE user_id='" + userID + "'");
			int role;
			String emailc;
			if(resultSet.next()){
				role = resultSet.getInt("role");
				emailc = resultSet.getString("email");
				UserData userc = new UserData(userID, role, emailc);
				return userc;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
        
	public List<UserData> getUsersData(){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			List<UserData> userList = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT user_id, role, email FROM user");
			while(resultSet.next()){
				String user_id = resultSet.getString("user_id");
				int role = resultSet.getInt("role");
				String email = resultSet.getString("email");
				userList.add(new UserData(user_id, role, email));
			}
			return userList;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

        	
	public void addComment(int post_id, String name, String content, String email){
			try{
			connection = DriverManager.getConnection(url, user, pass);
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			prepStatement = connection.prepareStatement("INSERT INTO comment ( post_id, name, email, content, date) VALUES (?, ?, ?, ?, ?)");
			prepStatement.setInt(1, post_id);
			prepStatement.setString(2, name);
			prepStatement.setString(3, email);
			prepStatement.setString(4, content);
			prepStatement.setString(5, timeStamp);
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}            
	}
        
	public void addPost(String user_id, String title, String date, String content){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("INSERT INTO post VALUES (default, ?, ?, ?, ?, 0, 0)");
			prepStatement.setString(1, user_id);
			prepStatement.setString(2, title);
			prepStatement.setString(3, content);
			String[] temp = date.split("-");
			prepStatement.setDate(4, new Date(Integer.valueOf(temp[0]) - 1900, 
									Integer.valueOf(temp[1]) - 1, Integer.valueOf(temp[2])));
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addUser(String user_id, String password, int role, String email){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?)");
			prepStatement.setString(1, user_id);
			prepStatement.setString(2, password);
			prepStatement.setInt(3, role);
			prepStatement.setString(4, email);
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
        
	public void updatePost(String post_id, String user_id, String title, String date, String content){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("UPDATE post SET user_id=?, title=?, content=?, date=? WHERE post_id=" + Integer.valueOf(post_id));
			prepStatement.setString(1, user_id);
			prepStatement.setString(2, title);
			prepStatement.setString(3, content);
			String[] temp = date.split("-");
			int year = Integer.valueOf(temp[0]) - 1900;
			int month = Integer.valueOf(temp[1]) - 1;
			prepStatement.setDate(4, new Date(year, month, Integer.valueOf(temp[2])));
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateUser(String userID, int role, String email){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("UPDATE user SET role=?, email=? WHERE user_id='" + userID + "'");
			prepStatement.setInt(1, role);
			prepStatement.setString(2, email);
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
        
	public void deletePost(String postId){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("UPDATE post SET deleted=1 WHERE post_id=" + postId);
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteUser(String userID){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("DELETE FROM user WHERE user_id='" + userID + "'");
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void publishPost(String postId){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("UPDATE post SET published=1 WHERE post_id=" + postId);
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void restorePost(String postId){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			prepStatement = connection.prepareStatement("UPDATE post SET deleted=0 WHERE post_id=" + postId);
			prepStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int checkCredential(String username, String password){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM user WHERE user_id='" + username + "' AND password='" + password + "'");
			if(resultSet.next())
				return resultSet.getInt("role");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getRole(String username){
		try{
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM user WHERE user_id='" + username + "'");
			if(resultSet.next())
				return resultSet.getInt("role");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
}
