/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "postController", eager = true)
@RequestScoped
public class PostController {

    /**
     * Creates a new instance of PostController
     */
    private int id;
    
    public PostController() {}
    
    public Connection getConnection() throws SQLException{
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
        return con;
    }
    
    public void addPost(String judul, String tanggal, String konten){
        try{
            Connection con = getConnection();
            String query = "INSERT INTO post (id_member, Status,Judul,Konten,Tanggal, deleted) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);            
            ps.setString(2, "unpublished");
            ps.setString(3, judul);
            ps.setString(4, konten);
            ps.setString(5, tanggal);  
            ps.setInt(6, 0);
            ps.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            con.close();
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String deletePost(int id, int hlm){
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE post SET deleted=? WHERE id=?");
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();
            con.close();
        } catch(SQLException e){            
        }
        if(hlm == 1)
            return "index?faces-redirect=true";
        else
            return "unpublished?faces-redirect=true";
    }
    
    public String restorePost(int id){
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE post SET deleted=? WHERE id=?");
            ps.setInt(1, 0);
            ps.setInt(2, id);
            ps.executeUpdate();
            con.close();
        } catch(SQLException e){            
        }
        return "soft_deleted?faces-redirect=true";
    }
    
    public String deletePostPermanent(int id){
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM post WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch(SQLException e){            
        }
        return "soft_deleted?faces-redirect=true";
    }
    
    public String publishPost(int id){
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE post SET status=? WHERE id=?");
            ps.setString(1, "published");
            ps.setInt(2, id);
            ps.executeUpdate();
            con.close();
        } catch(SQLException e){            
        }
        return "unpublished?faces-redirect=true";
    }

}
