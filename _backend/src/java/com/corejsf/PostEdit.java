/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Indam Muhammad
 */

package com.corejsf;

import java.io.IOException;
import java.sql.*;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "postedit")
@ViewScoped

public class PostEdit implements Serializable {
    private int id;
    
    private Post pos;
    public PostEdit(){
        pos = new Post();
    }
    public int getId(){
        return id;
    }
    public Post getPos(){
        return pos;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setPos(Post pos){
        this.pos = new Post();
        this.pos.setId(pos.getId());
        this.pos.setJudul(pos.getJudul());
        this.pos.setKonten(pos.getKonten());
        this.pos.setStatus(pos.getStatus());
        this.pos.setTanggal(pos.getTanggal());
    }
    
    public void edit(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();            
            con = DriverManager.getConnection(url, user, password);
            String query = "UPDATE post SET Judul=?, Konten=?, Tanggal=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pos.getJudul());
            ps.setString(2, pos.getKonten());            
            ps.setString(3, pos.getTanggal());
            ps.setInt(4, pos.getId());               
            ps.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            con.close();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void execute(int id){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";        
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM post WHERE id="+id);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                pos.setJudul(res.getString("Judul"));
                pos.setKonten(res.getString("Konten"));
                pos.setStatus(res.getString("Status"));
                pos.setTanggal(res.getString("Tanggal"));
                pos.setDeleted(res.getInt("deleted"));
                pos.setId(id);
                this.id = id;
            }            
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
