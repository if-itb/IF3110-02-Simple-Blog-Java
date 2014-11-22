/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

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

/**
 *
 * @author Sakurai
 */
@ManagedBean(name = "Publishpost", eager = true)
@SessionScoped
public class PublishPost {
    public PublishPost(){
        
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/blog";
            String user = "root";
            String password = "";
            conn =  DriverManager.getConnection(url, user, password);
            System.out.println("CONNECTED");
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        System.out.println("asdasdasdasdasdasd");
        return conn;
    }
    
    public List<Post> getUnpublishedPost() throws ClassNotFoundException{
        ResultSet result;
        List<Post> UnpublishedPost = new ArrayList<>();
        try {
          Statement stmt = getConnection().createStatement();
          String query = "Select * from post where status = \"unpublished\"";
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
        } catch (SQLException e) {
           System.err.println(e);
        }
        return UnpublishedPost;
    }
}
