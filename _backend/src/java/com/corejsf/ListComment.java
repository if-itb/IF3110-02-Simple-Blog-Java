/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corejsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gifari Kautsar
 */
@ManagedBean(name = "listComment", eager=true)
@ViewScoped
public class ListComment {
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Comment> comments;
    
    public ListComment(){        
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
    public ArrayList<Comment> execute(int id_post){
        System.out.println("NYUUU "+ id_post);
        comments = new ArrayList<Comment>();
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            Statement sm = con.createStatement();
            ResultSet res = sm.executeQuery("SELECT * FROM comment WHERE id_post="+id_post+" ORDER BY Tanggal DESC, id DESC");
            while(res.next()){
                Comment com = new Comment();
                com.setId(res.getInt("id"));
                com.setIdPost(id_post);
                com.setEmail(res.getString("Email"));
                com.setNama(res.getString("Nama"));
                com.setTanggal(res.getString("Tanggal"));
                com.setKomentar(res.getString("Komentar"));
                comments.add(com);
            }
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return comments;
    }
}
