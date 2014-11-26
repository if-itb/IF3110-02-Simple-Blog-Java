<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author user
 */
public class Connector {
    private Connection connection;
    public Connector(){
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }
        String url = "jdbc:mysql://localhost:3306/post";
        String username = "root";
        String password = "";
        connection = null;
        try {
            System.out.println("Connecting database...");
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.err.println("Gagal melakukan koneksi.");
        }
    }
    public ArrayList<User> getUser(){
        Statement st;
        ArrayList<User> userList = null;
        try{
            st = connection.createStatement();
            String sql = ("SELECT * FROM user_data");
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setUserName(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getInt("user_type"));
                userList.add(user);
            }
        }
        catch(SQLException ex){
            System.err.println("Gagal mengambil data");
        }
        return userList;
    }
    public ArrayList<Comment> getComment(int post_id){
        Statement st;
        ArrayList<Comment> commentList = null;
        try{
            st = connection.createStatement();
            String sql = ("SELECT * FROM comment WHERE comment_post_id = " + post_id + ";");
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                Comment comment = new Comment();
                comment.setCommentID(rs.getInt("comment_id"));
                comment.setDate(rs.getString("comment_date"));
                comment.setContent(rs.getString("comment_content"));
                comment.setCommentPostID(rs.getInt("comment_post_id"));
                comment.setEmail(rs.getString("comment_email"));
                comment.setUserID(rs.getInt("comment_user_id"));
                commentList.add(comment);
            }
        }
        catch(SQLException ex){
            System.err.println("Gagal mengambil data");
        }
        return commentList;
    }
    public ArrayList<Post> getPublished(){
        Statement st;
        ArrayList<Post> listPost = new ArrayList<Post>();
        try {
            st = connection.createStatement();
            String sql = ("SELECT * FROM `post`.`post_table` WHERE `category` = 'published' ORDER BY `date`;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Post post = new Post();
                post.setPostID(rs.getInt("post_id"));
                post.setTitle(rs.getString("title"));
                post.setDate(rs.getString("date"));
                post.setContent(rs.getString("content"));
                post.setCategory(true);
		post.setAuthorID(rs.getInt("post_author_id"));
		listPost.add(post);
            }
        } catch (SQLException ex) {
            System.err.println("Gagal mengambil data");
        }
        return listPost;
    }
    public ArrayList<Post> getUnPublished(){
        Statement st;
        ArrayList<Post> listPost = new ArrayList<Post>();
        try {
            st = connection.createStatement();
            String sql = ("SELECT * FROM `post`.`post_table` WHERE `category` = 'unpublished' ORDER BY `date`;");
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                Post post = new Post();
                post.setPostID(rs.getInt("post_id"));
                post.setTitle(rs.getString("title"));
                post.setDate(rs.getString("date"));
                post.setContent(rs.getString("content"));
                post.setCategory(true);
                listPost.add(post);
            }
        } catch (SQLException ex) {
            System.err.println("Gagal mengambil data");
        }
        return listPost;
    }
    public void setPost(int user_id, String judul, String tanggal, String konten){
        Statement st;
        try {
            st = connection.createStatement();
            String sql = ("SELECT * FROM post WHERE Category = 0 ORDER BY date;");
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
            }
        } catch (SQLException ex) {
            System.err.println("Gagal mengambil data");
        }
    }
    
    public boolean setComment(String content, int post_id, String email, int user_id){
	Statement st;
	boolean executed = false;
	String finalQuery = null;
	try{
	    st = connection.createStatement();
	    String sqlQuery = "";
	    //contoh query bener 
	    // INSERT INTO `post`.`post_comment` (`comment-id`, `comment-date`, `comment-content`, `comment-post-id`, `comment-email`, `comment-user-id`) VALUES (NULL, '2014-11-24', 'AAAAAAAAAAAAAAAAEH', '1', 'feli@feli.fel', '1');
		sqlQuery = "INSERT INTO `post`.`post_comment` (`comment-user-id`,`comment-content`,`comment-email`,`comment-post-id`,`comment-date`) VALUES (";
	    if (user_id != 0) {
		sqlQuery = sqlQuery + Integer.toString(user_id) + ","; 
	    }
	    else {
		sqlQuery = sqlQuery + "null, "; 
	    }
	    
		sqlQuery = sqlQuery + "'" + content.toString() + "', "; 
		sqlQuery = sqlQuery + "'" + email.toString() + "', "; 
		sqlQuery = sqlQuery + Integer.toString(post_id) + ", ";
		sqlQuery = sqlQuery + "CURDATE() );"; 
		
		//finalQuery = sqlQuery;
		executed = st.execute(sqlQuery);
		
		
		  
	} catch (Exception e) {
	    System.err.println("Gagal insert comment :v ");
	    System.out.println(e);
	}
	return executed;
	
    }
    
    public String getUsernameByID(int user_ID){
	Statement st;
	boolean executed = false;
	String uName = new String();
	try {
	    st = connection.createStatement();
	    String sqlQuery = "SELECT * FROM `post`.`user_data` WHERE `user_id` = " + Integer.toString(user_ID) + ";";
	    ResultSet rs = st.executeQuery(sqlQuery);
	    executed = st.execute(sqlQuery);
	    if (rs == null){
		uName = "RS null coy!";
	    }
	    else 
		uName = rs.getString("user_name");
	} catch (Exception e) {}
	
	return uName;
    }
    
    
    public boolean testConnection(){
	Statement st; 
	try {
	    st = connection.createStatement();
	    String sqlQuery = "INSERT INTO `post`.`post_comment` (`comment-id`, `comment-date`, `comment-content`, `comment-post-id`, `comment-email`, `comment-user-id`) VALUES (NULL, '2014-11-25', 'AAAAaaaAAAAAAAAAAAAEH2', '1', 'feli@feli.fel', '1');";
	    
	    //ResultSet rs = st.executeQuery(sqlQuery);
	    return st.execute(sqlQuery);
	} catch (Exception e) {}
	
	return false;
    }
    public void closeConnection(){
        System.out.println("Closing the connection.");
        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
    }
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Connector {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
    private Connection connection;
    Connector(){
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }
        String url = "jdbc:mysql://localhost:3306/post";
        String username = "root";
        String password = "";
        connection = null;
        try {
            System.out.println("Connecting database...");
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.err.println("Gagal melakukan koneksi.");
        }
    }
    public ArrayList<User> getUser(){
        Statement st;
        ArrayList<User> userList = new ArrayList<User>();
        try{
            st = connection.createStatement();
            String sql = ("SELECT * FROM user_data");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getInt("user_type"));
                userList.add(user);
                System.out.println("Username : " + userList.get(0).getUserName());
            }
        }
        catch(SQLException ex){
            System.err.println("Gagal mengambil data");
        }
        return userList;
    }
    public ArrayList<Comment> getComment(int post_id){
        Statement st;
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        try{
            st = connection.createStatement();
            String sql = ("SELECT * FROM comment WHERE comment_post_id = " + post_id + ";");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Comment comment = new Comment();
                comment.setCommentID(rs.getInt("comment_id"));
                comment.setDate(rs.getString("comment_date"));
                comment.setContent(rs.getString("comment_content"));
                comment.setCommentPostID(rs.getInt("comment_post_id"));
                comment.setEmail(rs.getString("comment_email"));
                comment.setUserID(rs.getInt("comment_user_id"));
                commentList.add(comment);
            }
        }
        catch(SQLException ex){
            System.err.println("Gagal mengambil data");
        }
        return commentList;
    }
    public ArrayList<Post> getPost(int category){
        Statement st;
        ArrayList<Post> listPost = new ArrayList<Post>();
        try {
            st = connection.createStatement();
            String sql = ("SELECT * FROM post.post_table WHERE category = " + category + " ORDER BY date;");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Post post = new Post();
                post.setPostID(rs.getInt("post_id"));
                post.setTitle(rs.getString("title"));
                post.setDate(rs.getString("date"));
                post.setContent(rs.getString("content"));
                post.setCategory(true);
                post.setAuthorID(rs.getInt("post_author_id"));
                listPost.add(post);
            }
        } catch (SQLException ex) {
            System.err.println("Gagal mengambil data");
        }
        return listPost;
    }
    public void setUser(User value){
        Statement st;
        try {
            st = connection.createStatement();
            String sql = ("INSERT INTO `post`.`user_data` (`user_id`, `user_name`, `user_type`, `password`) VALUES (NULL, '" + value.getUserName() +  "', '" + value.getUserType() + "', '" + value.getPassword() + ");");
            ResultSet rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Gagal mengambil data");
        }
        
    }
    public void setComment(Comment value){
        Statement st;
        try {
            st = connection.createStatement();
            String sql = ("INSERT INTO `post`.`post_comment` (`comment_id`, `comment_date`, `comment_content`, `comment_post_id`,`comment_email`, `comment_user_id`) VALUES (NULL, '" + value.getDate() +  "', '" + value.getContent() + "', '" + value.getCommentPostID() + "', '" + value.getEmail() + "', '" + value.getUserID() + ");");
            ResultSet rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Gagal mengambil data");
        }
        
    }
    public void setPost(Post value){
        Statement st;
        try {
            st = connection.createStatement();
            String sql = ("INSERT INTO `post`.`post_table` (`post_id`, `title`, `date`, `content`, `category`, `post_author_id`) VALUES (NULL, '" + value.getTitle() +  "', '" + value.getDate() + "', '" + value.getContent() + "', '" + value.getCategory() + "', '" + value.getAuthorID() + ");");
            ResultSet rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Gagal meng-insert data.");
        }
    }
    public void closeConnection(){
        System.out.println("Closing the connection.");
        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
    }
}
>>>>>>> Update connector, post
