/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corejsf;

import java.sql.*;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "listpost")
@RequestScoped

/**
 *
 * @author Indam Muhammad
 */
public class ListPost {
    private ArrayList<Post> published_posts;
    private ArrayList<Post> unpublished_posts;
    private ArrayList<Post> deleted_posts;
    
    public ListPost(){
        published_posts = new ArrayList<Post>();
        unpublished_posts = new ArrayList<Post>();
        deleted_posts = new ArrayList<Post>();
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            Statement sm = con.createStatement();
            ResultSet res = sm.executeQuery("SELECT * FROM post ORDER BY Tanggal DESC, id DESC");
            while(res.next()){
                Post pos = new Post();
                pos.setId(res.getInt("id"));
                pos.setJudul(res.getString("Judul"));
                pos.setKonten(res.getString("Konten"));
                pos.setStatus(res.getString("Status"));
                pos.setTanggal(res.getString("Tanggal"));
                pos.setDeleted(res.getInt("deleted"));
                if(pos.getDeleted()==1){
                    deleted_posts.add(pos);
                }else if(pos.getStatus().equalsIgnoreCase("unpublished")){
                    unpublished_posts.add(pos);
                }else{
                    published_posts.add(pos);
                }
            }
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
    }

    public ArrayList<Post> getDeleted_posts() {
        return deleted_posts;
    }

    public void setDeleted_posts(ArrayList<Post> deleted_posts) {
        this.deleted_posts = deleted_posts;
    }
    
    public ArrayList<Post> getPublished_posts(){
        return this.published_posts;
    }
    
    public ArrayList<Post> getUnpublished_posts(){
        return this.unpublished_posts;
    }

    public void setPublished_posts(ArrayList<Post> published_posts) {
        this.published_posts = published_posts;
    }

    public void setUnpublished_posts(ArrayList<Post> unpublished_posts) {
        this.unpublished_posts = unpublished_posts;
    }

}
