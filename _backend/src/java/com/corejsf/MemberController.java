/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corejsf;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gifari
 */

@ManagedBean(name = "memberController")
@ViewScoped

public class MemberController {
    private int id;
    private Member1 mem;
    
    public MemberController(){
        mem = new Member1();
    }
    
    public int getId(){
        return id;
    }
    public Member1 getMem(){
        return mem;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setMem(Member1 mem){
        this.mem = new Member1();
        this.mem.setId(mem.getId());
        this.mem.setEmail(mem.getEmail());
        this.mem.setName(mem.getName());
        this.mem.setPassword(mem.getPassword());
        this.mem.setRole(mem.getRole());
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
            System.out.println("Connection completed.");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
        return con;
    }
    
    public void addMember(String Email, String Name, String Password, String Role){
        try{
            Connection con = getConnection();
            String query = "INSERT INTO member (Email, Name, Password, Role) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Email);            
            ps.setString(2, Name);
            ps.setString(3, Password);
            ps.setString(4, Role);
            ps.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml");
            con.close();
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
            String query = "UPDATE member SET Email=?, Name=?, Password=?, Role=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, mem.getEmail());
            ps.setString(2, mem.getName());            
            ps.setString(3, mem.getPassword());
            ps.setString(4, mem.getRole());
            ps.setInt(5, mem.getId());               
            ps.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM member WHERE id="+id);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                mem.setEmail(res.getString("Email"));
                mem.setName(res.getString("Name"));
                mem.setPassword(res.getString("Password"));
                mem.setRole(res.getString("Role"));
                mem.setId(id);
                this.id = id;
            }            
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String deleteMember(int id){
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM member WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch(SQLException e){            
        }
        return "user?faces-redirect=true";
    }
}
