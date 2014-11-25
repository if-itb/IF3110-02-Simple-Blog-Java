/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author User
 */
@ManagedBean (name="post", eager=true)
@RequestScoped
public class Post {
    private int id_post;
    private String judul;
    private String konten;
    private Date tanggal_post;
    private String status;
    private int del_stat;
    
    String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
    String uName = "root";
    String pass = "";

    private Post(int id_post, String judul, String konten, Date tanggal_post, String status, int del_stat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * @return the id_post
     */
    public int getId_post() {
        return id_post;
    }

    /**
     * @param id_post the id_post to set
     */
    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    /**
     * @return the judul
     */
    public String getJudul() {
        return judul;
    }

    /**
     * @param judul the judul to set
     */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /**
     * @return the konten
     */
    public String getKonten() {
        return konten;
    }

    /**
     * @param konten the konten to set
     */
    public void setKonten(String konten) {
        this.konten = konten;
    }

    /**
     * @return the tanggal_post
     */
    public Date getTanggal_post() {
        return tanggal_post;
    }

    /**
     * @param tanggal_post the tanggal_post to set
     */
    public void setTanggal_post(Date tanggal_post) {
        this.tanggal_post = tanggal_post;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the del_stat
     */
    public int getDel_stat() {
        return del_stat;
    }

    /**
     * @param del_stat the del_stat to set
     */
    public void setDel_stat(int del_stat) {
        this.del_stat = del_stat;
    }
    @ManagedProperty (value="#{posts}")
    private ArrayList<Post> posts;
    
    public ArrayList<Post> getPost() throws SQLException {
            
        Connection connect = null;
        Statement stmnt = null;
        
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println("Unable to load Driver");
                
            }
            connect = DriverManager.getConnection(dbURL,uName,pass );
            stmnt = connect.createStatement();
            
            String sqlStr = "SELECT * FROM `post`";
            ResultSet rSet = stmnt.executeQuery(sqlStr);                    
                    while (rSet.next()){
                        int id_post = rSet.getInt("id_post");
                        String judul = rSet.getString("judul");
                        String konten = rSet.getString("konten");
                        Date tanggal_post = rSet.getDate("tanggal_user");
                        String status = rSet.getString("status");
                        int del_stat = rSet.getInt("del_stat");
                        Post post = new Post(id_post,judul,konten,tanggal_post,status,del_stat);
                        
                            posts.add(post);
                            System.out.println(posts);
                    }
        } catch (SQLException ex) {
            // Step 1: Create a database "Connection" object
            ex.printStackTrace();
            System.out.println("Unable to connect to database");
            
        }
        return posts;
    }
    public boolean IsPublished(){
        return getStatus()=="published";
    }
    
}
