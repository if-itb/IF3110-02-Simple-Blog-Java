/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author Afik
 */
@ManagedBean
@RequestScoped
public class ListPost {
    
    ArrayList<Post> listPost;
    ArrayList<Post> unpublishedPost;
    /**
     * Creates a new instance of ListPost
     * @return Post
     * @throws java.sql.SQLException
     */
    public Post fetchPost() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_blog_2", "root", "");
            Statement sta = conn.createStatement();
            String Sql = "select * from entries";
            ResultSet rs = sta.executeQuery(Sql);
            rs.next();
            Post p = new Post();
            p.setJudul(rs.getString(2));
            p.setDate(rs.getString(3));
            p.setKonten(rs.getString(4));
            p.setPID(rs.getInt(1));
            p.setAuthor(rs.getString(6));
            p.setPublished(rs.getBoolean(5));
            return p;
        } catch (ClassNotFoundException ex) {
            if (1==1)throw new SQLException("ggal");
            Logger.getLogger(ListPost.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Post> fetchPublishedPost() {
        return listPost;
    }
    
    public ArrayList<Post> fetchUnpublishPost(){
        return unpublishedPost;
    }
    
    
}
