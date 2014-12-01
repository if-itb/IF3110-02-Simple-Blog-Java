/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.*;
import javax.faces.context.FacesContext;

/**
 *
 * @author A 46 CB i3
 */
@ManagedBean(name="arrPostUnpublished", eager=true)
@RequestScoped
public class ArrPostUnpublished {
    
    // attribute
    private List<Post> post;
    private Connection con;
    private Statement ps;
    
    // default constructor
    public ArrPostUnpublished() {
        post = new ArrayList<Post>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2?zeroDateTimeBehavior=convertToNull", "root", "");
            ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from postdata where status='unpublished'");
            while(rs.next() == true) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setJudul(rs.getString(2));
                p.setTanggal(rs.getString(3));
                p.setKonten(rs.getString(4));
                p.setStatus(rs.getString(5));
                post.add(p);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void makepublish(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE postdata SET status='published' where id_post=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    // getter
    public List getPost() {
        return post;
    }
}