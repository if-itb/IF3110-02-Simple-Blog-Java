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

import java.sql.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "postview", eager=true)
@ViewScoped

public class PostView {
    private int id;
    
    private Post pos;
    public PostView(){
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
        pos.setId(id);
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
            }
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void setPos(Post pos){
        this.pos = new Post();
        this.pos.setId(pos.getId());
        this.pos.setJudul(pos.getJudul());
        this.pos.setKonten(pos.getKonten());
        this.pos.setStatus(pos.getStatus());
        this.pos.setTanggal(pos.getTanggal());
    }
    
}
