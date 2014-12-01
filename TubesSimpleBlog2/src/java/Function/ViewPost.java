/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import java.util.Date;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author ASUS
 */
@ManagedBean(name = "viewPost", eager = true)
@SessionScoped
public class ViewPost{
    private int postId;
    private String judul;
    private String konten;
    private Date tanggal;
	
    public String showPage(int id){
        postId = id;
        System.out.println("viewpost="+id);
        String url = "jdbc:mysql://localhost:3306/datapost";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = "";
         try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url,userName,password);
            Statement st = conn.createStatement();
            ResultSet res= st.executeQuery("Select * from posts where PID = "+id);

            if(res.next()){
                judul=res.getString("Judul");
                tanggal = res.getDate("Tanggal");                
                konten = res.getString("Konten");
                postId = id;
            }
            conn.close();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            }
         return "view_post";
    }
	
    public int getPostId(){
            return postId;
    }
    
    public String getJudul(){
            return judul;
    }
    
    public String getKonten(){
            return konten;
    }
    
    public Date getTanggal(){
            return tanggal;
    }
	
    public void setPostId(int postId){
            this.postId = postId;
    }
    public void setJudul(String judul){
            this.judul = judul;
    }
    public void setKonten(String konten){
            this.konten = konten;
    }
    public void setTanggal(Date tanggal){
            this.tanggal = tanggal;
    }

 }
