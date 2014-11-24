/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Sakurai
 */
@ManagedBean(name = "Publishpost", eager = true)
@ViewScoped
public class PublishPost {
    public PublishPost(){
        
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException{
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/blog";
            String user = "root";
            String password = "";
            conn =  DriverManager.getConnection(url, user, password);
            System.out.println("CONNECTED");
        }catch(ClassNotFoundException | InstantiationException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    public List<Post> getUnpublishedPost() throws ClassNotFoundException, IllegalAccessException{
        ResultSet result;
        List<Post> UnpublishedPost = new ArrayList<>();
        try {
          Connection conn = getConnection();
          Statement stmt = conn.createStatement();
          String query = "Select * from post where Status = \"unpublished\"";
          result = stmt.executeQuery(query);

          while(result.next()){
              Post post = new Post();
              post.setId(result.getInt(1));
              post.setJudul(result.getString(2));
              post.setTanggal(result.getString(3));
              post.setContent(result.getString(4));
              post.setAuthor(result.getString(5));
              post.setStatus(result.getString(6));
              UnpublishedPost.add(post);
           }
          conn.close();
        } catch (SQLException e) {
           System.err.println(e);
        }
        return UnpublishedPost;
    }
    
    public String publishPost(int postID) throws ClassNotFoundException, IllegalAccessException{
        ResultSet result;
        try {
          Connection conn = getConnection();
          Statement stmt = conn.createStatement();
          String query = "Update post set Status = \"published\" WHERE ID = " + postID + ";";
          stmt.executeUpdate(query);
          conn.close();
        } catch (SQLException e) {
           System.err.println(e);
        } 
        return "PublishPost.xhtml?faces-redirect=true";
    }
    
}
