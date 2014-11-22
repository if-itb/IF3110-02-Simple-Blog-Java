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
   
    public void addPost() throws ClassNotFoundException, SQLException{
          ResultSet rs;
          Connection con = makeConnection();
          Statement stmt = con.createStatement();
          String query = "Select COUNT(Id) from posting";
          rs = stmt.executeQuery(query);
          PreparedStatement ps;
          int countsumId = 0;
          while(rs.next()){
             countsumId = rs.getInt(1);
           }
          System.out.println(countsumId);
          System.out.println("PINGGG");
            String query2 = "INSERT INTO `posting` (`Id`,`Judul`, `Tanggal`, `Content`, `Author`, `Status`) VALUES (?,?,?,?,?,?)";
            ps= con.prepareStatement(query2);
            ps.setInt(1,countsumId+1);
            ps.setString(2,"Doremi");
            ps.setString(3,"1-2-3");
            ps.setString(4,"ini adalah not");
            ps.setString(5,"Doni");
            ps.setString(6,"unpublished");
            int i = ps.executeUpdate();     
    }
    
}
