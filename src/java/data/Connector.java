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
        ArrayList<User> userList = new ArrayList<User>();
        try{
            st = connection.createStatement();
            String sql = ("SELECT * FROM `user_data`");
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
            String sql = ("SELECT * FROM `post`.`post_comment` WHERE `comment-post-id` = " + post_id + ";");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Comment comment = new Comment();
                comment.setCommentID(rs.getInt("comment-id"));
                comment.setDate(rs.getString("comment-date"));
                comment.setContent(rs.getString("comment-content"));
                comment.setCommentPostID(rs.getInt("comment-post-id"));
                comment.setEmail(rs.getString("comment-email"));
                comment.setUserID(rs.getInt("comment-user-id"));
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
	    String cat = new String();
	    if (category == 0)
		cat = "unpublished";
	    else 
		cat = "published";
	    
            String sql = ("SELECT * FROM `post`.`post_table` WHERE `category` = '" + cat + "' ORDER BY `date`;");
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
    
    public Post getPost(int postID, boolean published){
	Post post = new Post();
	Statement st;
	try {
	    st = connection.createStatement();
	    String sqlQuery = new String();
	    if (published){
		sqlQuery = "SELECT * FROM `post`.`post_table` WHERE `category` = 'published' AND `post_id` = " + Integer.toString(postID);
		ResultSet rs = st.executeQuery(sqlQuery);
		while (rs.next()){
		    post.setPostID(rs.getInt("post_id"));
		    post.setTitle(rs.getString("title"));
		    post.setDate(rs.getString("date"));
		    post.setContent(rs.getString("content"));
		    post.setCategory(true);
		    post.setAuthorID(rs.getInt("post_author_id"));
		}
	    }
	    
	}
	catch (Exception ex){
	    
	}
	return post;
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
	    String sql = new String();
	    
	    if (value.getUserID() == 0) // GUEST
		sql = ("INSERT INTO `post`.`post_comment` (`comment-id`, `comment-date`, `comment-content`, `comment-post-id`,`comment-email`, `comment-user-id`) "
			+ "VALUES"
			+ "(NULL, CURDATE(), '" + value.getContent() + "', '" + value.getCommentPostID() + "', '" + value.getEmail() + "', '" + "NULL" + ");");
	    else {
		
		sql = ("INSERT INTO `post`.`post_comment` (`comment-id`, `comment-date`, `comment-content`, `comment-post-id`,`comment-email`, `comment-user-id`) VALUES (NULL, CURDATE(), '" + value.getContent() + "', '" + value.getCommentPostID() + "', '" + this.getEmailByID(value.getUserID()) + "'," + value.getUserID() + ");");
	    }
	    
            ResultSet rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Gagal mengambil data");
        }
        
    }
    
        public void setComment(String content, int post_id, String email, int user_id){
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
	
    }
	
	public String setcommentquery(String content, int post_id, String email, int user_id){
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
		
		finalQuery = sqlQuery;
		st.executeQuery(sqlQuery);
		//executed = st.execute(sqlQuery);
	} catch (Exception e) {
	    System.err.println("Gagal insert comment :v ");
	    System.out.println(e);
	}
	return finalQuery;
	
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
    
        public boolean resetPost(Post value){
        Statement st;
	Boolean queryres = false;
        try {
            st = connection.createStatement();
	    //correct query : UPDATE `post_table` SET `post_id`=[value-1],`title`=[value-2],`date`=[value-3],`content`=[value-4],`category`=[value-5],`post_author_id`=[value-6] WHERE 1
            String sql = "UPDATE `post`.`post_table` "
		    + "SET `title` ='" + value.getTitle() +  "', "
		    + "`date` ='" + value.getDate() + "', "
		    + "`content` = '" + value.getContent() + "' "
		    + "WHERE `post_id`=" + value.getPostID() + ";" ;
	    //return sql;
	    //ResultSet rs = st.executeQuery(sql);
            queryres = st.execute(sql);
        } catch (SQLException ex) {
            System.err.println("Gagal meng-insert data.");
        }
	    return queryres;
    }
    
     public String getUsernameByID(int user_ID){
	boolean executed = false;
	
	String uName = new String();
	try {
	    Statement st;
	    st = connection.createStatement();
	    String sqlQuery = "SELECT * FROM `post`.`user_data` WHERE `user_id` = " + Integer.toString(user_ID) + ";";
	    ResultSet rs = st.executeQuery(sqlQuery);
	    if (rs.next()){
	    uName = Boolean.toString(executed);
	    uName = rs.getString("user_name");
	    }
	} catch (Exception e) {
	    uName = uName + e.toString();
	    //uName = sqlQuery;
	}
	
	return uName;
    }
     public String getEmailByID(int user_ID){
	boolean executed = false;
	
	String uName = new String();
	try {
	    Statement st;
	    st = connection.createStatement();
	    String sqlQuery = "SELECT * FROM `post`.`user_data` WHERE `user_id` = " + Integer.toString(user_ID) + ";";
	    ResultSet rs = st.executeQuery(sqlQuery);
	    if (rs.next()){
	    uName = Boolean.toString(executed);
	    uName = rs.getString("user_email");
	    }
	} catch (Exception e) {
	    uName = uName + e.toString();
	    //uName = sqlQuery;
	}
	
	return uName;
    }
    public void closeConnection(){
        System.out.println("Closing the connection.");
        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
    }
}
