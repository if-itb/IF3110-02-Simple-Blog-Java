/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "post")
@RequestScoped
public class Post {
    private int id;
    private String status;
    private String judul;
    private Date tanggal;
    private String konten;
    
    public Post() {
    }
    
    public int getId(){
        return id;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String getJudul(){
        return judul;
    }
    
    public Date getTanggal(){
        return tanggal;
    }
    
    public String getKonten(){
        return konten;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setJudul(String judul){
        this.judul = judul;
    }
    
    public void setTanggal(Date tanggal){
        this.tanggal = tanggal;
    }
    
    public void setKonten(String konten){
        this.konten = konten;
    }
    public void addPost(String judul, String tanggal, String konten, String status){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO post (id_member, Status,Judul,Konten,Tanggal) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);            
            ps.setString(2, status);
            ps.setString(3, judul);
            ps.setString(4, konten);
            ps.setDate(5, new Date(115, 1, 1));            
            ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
    }
    public Date aturTanggal(String s){
        int a = Integer.parseInt(s.substring(0, 3));
        int b = Integer.parseInt(s.substring(5, 6));
        int c = Integer.parseInt(s.substring(8, 9));
        Date d = new Date(a,b,c);
        return d;
    }
}
