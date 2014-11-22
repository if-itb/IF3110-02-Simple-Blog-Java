/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rikysamuel
 */
@ManagedBean(name="Posting", eager = true)
@SessionScoped
public class PostingDatabase {
    public PostingDatabase(){
    }
    
    public Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/simpleblog";
        String user = "root";
        String password = "";
        con = DriverManager.getConnection(url,user,password);
        return con;
    }
    
    public List<Post> getPost() throws ClassNotFoundException{
        ResultSet rs;
        List<Post> records = new ArrayList<>();
        try {
          Statement stmt = makeConnection().createStatement();
          String query = "Select * from posting";
          rs = stmt.executeQuery(query);

          while(rs.next()){
              Post post = new Post();
              post.setId(rs.getInt(1));
              post.setJudul(rs.getString(2));
              post.setTanggal(rs.getString(3));
              post.setContent(rs.getString(4));
              post.setAuthor(rs.getString(5));
              post.setStatus(rs.getString(6));
              records.add(post);
           }
        } catch (SQLException e) {
           System.err.println(e);
        }
        return records;
   }
   
    public void addPost(){
        try {
            Statement stmt = makeConnection().createStatement();
            String query = "INSERT INTO `posting` (`Judul`, `Tanggal`, `Content`, `Author`, `Status`) VALUES (`Doremi`, `1-2-3`, `ini adalah not`, `Doni`, `unpublished`)";
            stmt.execute(query);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }        
    }
    
}
