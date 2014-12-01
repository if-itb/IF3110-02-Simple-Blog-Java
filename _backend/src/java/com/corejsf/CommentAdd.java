/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corejsf;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Indam Muhammad
 */
@ManagedBean(name="commentAdd", eager=true)
@ViewScoped
public class CommentAdd implements Serializable {
    private int id_pos;

    public int getId_pos() {
        return id_pos;
    }

    public void setId_pos(int id_pos) {
        this.id_pos = id_pos;
    }

    public CommentAdd(){
    }
    
    public Connection getConnection() throws SQLException{
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
        return con;
    }
    
    public void addComment(String name, String email, String komentar){
        try{
            Connection con = getConnection();
            String query = "INSERT INTO comment (id_post, Nama, Email, Tanggal, Komentar) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_pos);
            ps.setString(2, name);
            ps.setString(3, email);
            CurrentDate cd = new CurrentDate();
            ps.setString(4, cd.getCDate());
            ps.setString(5, komentar);
            ps.executeUpdate();
            //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addCommentUser(String id, String komentar){
        try {
            Connection con = getConnection();
            Statement sm = con.createStatement();
            ResultSet res = sm.executeQuery("SELECT * FROM member WHERE id="+ Integer.parseInt(id));
            String email="";
            String nama="";
            while(res.next()){
                email = res.getString("Email");
                nama = res.getString("Name");
            }
            con.close();
            addComment(nama, email, komentar);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void execute(int i){
        id_pos = i;
    }
}