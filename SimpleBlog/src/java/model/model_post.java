/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.Post;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A46CB
 */
public class model_post {
    private Connection conn;
    String output2 = "";
    
    public model_post() {
        
        String databaseURL = "jdbc:mysql://localhost:3306/simpleblog";
        String username = "root";
        String password = "";
        try {
             try {
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(Exception e) {
                    System.out.println("Unable to load Driver");
                    output2 = output2 + "drivernya";
                }
            // Step 1: Create a database "Connection" object
            conn = DriverManager.getConnection(databaseURL, username, password);
       
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
            System.out.println("Unable to connect to database");
        } 
        /* finally {
            System.out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } 
        } */
    }
    
    public List<Post> getAllPublishedPosts() {
        Statement stmt = null;
        List<Post> posts = new ArrayList<Post>();
        try {
            stmt = conn.createStatement();
            String sqlStr = "SELECT * FROM post WHERE status = 'published' AND is_deleted='no' ORDER BY tanggal DESC";
            ResultSet rset = stmt.executeQuery(sqlStr); 
            while (rset.next()) {
                Post post = new Post();
                post.setId(rset.getInt("id"));
                post.setJudul(rset.getString("judul"));
                post.setKonten(rset.getString("konten"));
                post.setTanggal(rset.getDate("tanggal"));
                post.setImage(rset.getString("image"));
            
                posts.add(post);  
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        return posts;
    }
    
    public List<Post> getAllUnpublishedPosts() {
        Statement stmt = null;
        List<Post> posts = new ArrayList<Post>();
        try {
            stmt = conn.createStatement();
            String sqlStr = "SELECT * FROM post WHERE status='unpublished' ORDER BY tanggal DESC";
            ResultSet rset = stmt.executeQuery(sqlStr); 
            while (rset.next()) {
                Post post = new Post();
                post.setId(rset.getInt("id"));
                post.setJudul(rset.getString("judul"));
                post.setKonten(rset.getString("konten"));
                post.setTanggal(rset.getDate("tanggal"));
                post.setImage(rset.getString("image"));
                posts.add(post);  
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        return posts;
    }
    
    public List<Post> getAllDeletedPosts() {
        Statement stmt = null;
        List<Post> posts = new ArrayList<Post>();
        try {
            stmt = conn.createStatement();
            String sqlStr = "SELECT * FROM post WHERE is_deleted='yes' ORDER BY tanggal DESC";
            ResultSet rset = stmt.executeQuery(sqlStr); 
            while (rset.next()) {
                Post post = new Post();
                post.setId(rset.getInt("id"));
                post.setJudul(rset.getString("judul"));
                post.setKonten(rset.getString("konten"));
                post.setTanggal(rset.getDate("tanggal"));
                post.setImage(rset.getString("image"));
                posts.add(post);  
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        return posts;
    }
    
    public Post getPost(int id) {
        Statement stmt = null;
        Post post = new Post();
        
        try {
            stmt = conn.createStatement();
            String sqlStr = "SELECT * FROM post WHERE id ="
                   + "'" + id + "'";
            ResultSet rset = stmt.executeQuery(sqlStr); 
            while (rset.next()) {
                post.setId(rset.getInt("id"));
                post.setJudul(rset.getString("judul"));
                post.setKonten(rset.getString("konten"));
                post.setTanggal(rset.getDate("tanggal"));
                post.setImage(rset.getString("image"));
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        return post;
    }
    
    public int addPost(Post post) {
        Statement stmt = null;
        int id = 0;
        String out ="";
        try {
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalPost = df2.format(post.getTanggal()); 
            stmt = conn.createStatement();
            String sqlStr = "INSERT INTO post (judul, tanggal, konten, status) VALUES ('" + post.getJudul() + "','" + tanggalPost + "','" + post.getKonten() + "', 'unpublished')";
            //String sqlStr = "INSERT INTO post (judul, tanggal, konten) VALUES ('tesjudul','2014-11-22','dsasdfasdfas')";
            out = sqlStr;
            stmt.executeUpdate(sqlStr, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
              id = rs.getInt(1);
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        
        return id;
    }
    
    public int updatePost(Post post) {
        Statement stmt = null;
        int id = 0;
        String out ="";
        try {
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalPost = df2.format(post.getTanggal()); 
            stmt = conn.createStatement();
//            String sqlStr = "INSERT INTO post (judul, tanggal, konten) VALUES ('" + post.getJudul() + "','" + tanggalPost + "','" + post.getKonten() + "')";
            String sqlStr = "UPDATE post SET judul='"+post.getJudul()+"', tanggal='"+tanggalPost+"', konten='"+post.getKonten()+"' WHERE id='"+post.getId()+"'";
            out = sqlStr;
            stmt.executeUpdate(sqlStr);
            
            id = post.getId();
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        
        return id;
    }
    
}
