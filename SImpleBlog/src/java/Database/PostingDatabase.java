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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rikysamuel
 */
@ManagedBean(name="Posting", eager = true)
@SessionScoped
public class FetchDataDB {
    /**
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    public List<Post> getPost() throws ClassNotFoundException{
      ResultSet rs;
        Connection con;
        List<Post> records = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/simpleblog";
        String user = "root";
        String password = "";
      try {   
        con = DriverManager.getConnection("jdbc:mysql://localhost/simpleblog","root","");
        Statement stmt = con.createStatement();
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
      System.out.println("woooooooooyyy: " );
      return records;
   }
}
