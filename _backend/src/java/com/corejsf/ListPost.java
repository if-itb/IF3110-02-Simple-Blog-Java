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
    private ArrayList<Post> posts;
    public ListPost(){
        posts = new ArrayList<Post>();
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            Statement sm = con.createStatement();
            ResultSet res = sm.executeQuery("SELECT * FROM post ORDER BY Tanggal DESC");
            while(res.next()){
                Post pos = new Post();
                pos.setId(res.getInt("id"));
                pos.setJudul(res.getString("Judul"));
                pos.setKonten(res.getString("Konten"));
                pos.setStatus(res.getString("Status"));
                pos.setTanggal("0000-00-00");
                posts.add(pos);
            }
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
    }
    
    public ArrayList<Post> getPosts(){
        return posts;
    }
    
}
