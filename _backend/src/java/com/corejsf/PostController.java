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
    
    public void addPost(String judul, String tanggal, String konten, String status){
        try{
            Connection con = getConnection();
            String query = "INSERT INTO post (id_member, Status,Judul,Konten,Tanggal) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);            
            ps.setString(2, status);
            ps.setString(3, judul);
            ps.setString(4, konten);
            ps.setString(5, tanggal);            
            ps.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            con.close();
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
<<<<<<< HEAD
    public String deletePost(int id){
        System.out.println("MASUK "+id);
=======
    public void addMember (String email, String name, String password, String role){
        try{
            Connection con = getConnection();
            String query = "INSERT INTO member (Email, Name, Password, Role) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);            
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setString(4, role);        
            ps.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml");
            con.close();
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deletePost(int id){
>>>>>>> 5cec5b02e25dc9b925df4d6adc1b51369c2a5b14
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM post WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            con.close();
        } catch(SQLException e){            
        }
        return "index?faces-redirect=true";
    }
}
