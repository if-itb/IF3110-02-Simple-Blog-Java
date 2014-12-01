package mysql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import comments.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import posts.Post;
import users.User;
import mysql.Connect;


@ManagedBean(name = "dbStatement", eager = true) 
@RequestScoped
public class SQLStatements {
	private Statement statement;
    
    public void SQLStatement() throws SQLException{
        makeStatement();
    }
    
    public Statement makeStatement() throws SQLException{
        Connect c = new Connect();
        Connection conn = c.makeConnection();
        statement = (Statement) conn.createStatement();
        return statement;
    }
    public void AddUser(User U) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("insert into user (Name, Password, Email, Authority) "
        		+ "values (\""+U.getName()+"\", \""+U.getPass()+"\", \""+U.getEmail()+"\", \""+U.getAuth()+"\");");
    }
    public void DeleteUser(int id) throws SQLException{
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("delete from user "
        		+ "where id = \"" +id+ "\";");
    }
    public void EditUser(int id, User U) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("update user "
        		+ "set Name = \"" +U.getName() +"\","
        		+ "Password = \"" +U.getPass() +"\","
        		+ "Email = \"" +U.getEmail() +"\","
        		+ "Authority = \""+U.getAuth() +"\" "
        		+"where id = \""+U.getId()+"\";");
    }
    public void AddPost(Post P) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("insert into Posts (Title, Date, Content, Status) "
        		+ "values (\""+P.getTitle()+"\", \""+P.getDate()+"\", \""+P.getContent()+"\", \""+P.getStatus()+"\");");
    }
    public void EditPost(int id, Post P) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("update Posts "
        		+ "set Title = \"" +P.getTitle() +"\","
        		+ "Date = \"" +P.getDate() +"\","
        		+ "Content = \"" +P.getContent() +"\","
        		+ "Status = \""+P.getStatus() +"\" "
        		+"where id = \""+P.getId()+"\";");
    }
    public void DeletePost(int id) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("delete from Posts "
        		+ "where id = \"" +id+ "\";");    
    }
    public void AddComment(Comment C) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("insert into Comments (Name, Email, Date, Content, PostID) "
        		+ "values (\""+C.getName()+"\", \""+C.getEmail()+"\", \""+C.getDate()+"\", \""+C.getContent()+"\", \""+C.getPostId()+"\" );");
    }
    public void DeleteComment(int id) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
        statement.execute("delete from Comments "
        		+ "where id = \"" +id+ "\";");
    }
    
    public List<Post> GetPosts() throws SQLException {
    	
    	List<Post> Out = new ArrayList<Post>();
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	Post ah;
    	try (ResultSet rs = statement.executeQuery("select * from Posts;")) {
    		while(rs.next()) {
    			ah = new Post(rs.getInt("id"),rs.getString("Title"),rs.getString("Date"),rs.getString("Content"),rs.getString("Status"));
    			Out.add(ah);
    		}
        }
    	return Out;
    }
    public List<Comment> GetComments(String id) throws SQLException{
    	List<Comment> Out = new ArrayList<Comment>();
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	Comment ah = new Comment();
    	try (ResultSet rs = statement.executeQuery("select * from Comments where PostID = \""+id+"\";")) {
    		while(rs.next()) {
    			ah.setComment(rs.getInt("id"),rs.getString("Name"),rs.getString("Email"),rs.getString("Date"),rs.getString("Content"),Integer.toString(rs.getInt("PostID")));
    			Out.add(ah);
    		}
        }
    	return Out;
    }
    public List<User> GetUser() throws SQLException {
    	List<User> Out = new ArrayList<User>();
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	User ah;
    	try (ResultSet rs = statement.executeQuery("select * from User;")) {
    		while(rs.next()) {
    			ah = new User(rs.getInt("id"),rs.getString("Name"),rs.getString("Password"),rs.getString("Email"),rs.getString("Authority"));
    			Out.add(ah);
    		}
        }
    	return Out;
    }
    public List<Post> PickPublishedPost() throws SQLException{
    	List<Post> Out = new ArrayList<Post>(Arrays.asList(new Post()));
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	try (ResultSet rs = statement.executeQuery("select * from posts where Status = 'Published';")) {
    		while(rs != null && rs.next()) {
    			Post ah = new Post();
    			ah.setPost(rs.getInt("id"),rs.getString("Title"),rs.getString("Date"),rs.getString("Content"),rs.getString("Status"));
    			Out.add(ah);
    		}
        }
    	return Out;
    }
    
    public Post GetPost(int id) throws SQLException {
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	Post ah = new Post();
    	try (ResultSet rs = statement.executeQuery("select * from Posts where id = \""+id+"\";")) {
    		while(rs.next()) {
    			ah.setPost(rs.getInt("id"),rs.getString("Title"),rs.getString("Date"),rs.getString("Content"),rs.getString("Status"));
    		}
        }
    	return ah;
    }
    public boolean IsUserExist(String Name) throws SQLException{
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	String xname = null;
    	String query = "SELECT * FROM User WHERE Name=\"" + Name + "\";";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            xname = rs.getString("name");
        }
        return xname!=null;
    }
    
    public boolean isPassword(String Name, String Password) throws SQLException{
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	String pass = null;
        String query = "SELECT Password FROM User WHERE Name=\"" + Name + "\";";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            pass = rs.getString("Password");
        }
        return (pass.equals(Password));
    }
    
    public User SelectUser(String Name, String Password) throws SQLException{
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	User ah = new User();
    	try (ResultSet rs = statement.executeQuery("select * from User where Name = \""+Name+"\" AND Password = \""+Password+"\";")) {
    		while(rs.next()) {
    			ah = new User(rs.getInt("id"),rs.getString("Name"),rs.getString("Password"),rs.getString("Email"),rs.getString("Authority"));
    		}
        }
    	return ah;
    }
    
    public List<Comment> PostComments(int ID) throws SQLException{
    	Connect c = new Connect();
        Connection conn = c.makeConnection();
        if (conn == null){
        	throw new SQLException("Can't get database connection");
        }
        statement = (Statement) conn.createStatement();
    	List<Comment> Out = new ArrayList<Comment>();
    	Comment ah;
    	try (ResultSet rs = statement.executeQuery("select * from Comments where PostID = \""+ID+"\";")) {
    		while(rs.next()) {
    			ah = new Comment(rs.getInt("id"),rs.getString("Title"),rs.getString("Date"),rs.getString("Content"),rs.getString("Status"),Integer.toString(rs.getInt("PostID")));
    			Out.add(ah);
    		}
        }
    	return Out;
    }
    
}
