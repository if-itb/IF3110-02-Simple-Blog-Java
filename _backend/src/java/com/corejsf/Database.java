/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "database")
@RequestScoped
public class Database {
    public Database() {
    }
    
    public Connection getConnection() throws SQLException{
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
        return con;
    }
    
    public void addMember(Member1 member) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "INSERT INTO member (Email,Name,Password,Role) VALUES ('" + member.getEmail() + "','" + member.getName() + "','" + member.getPassword() + "','" + member.getRole() + "')";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){} 
    }
    
    public void deleteMember(Member1 member) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "DELETE FROM member WHERE Email=" + member.getEmail();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){}        
    }
    
    public void editMember(Member1 member) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "UPDATE member SET Email='" + member.getEmail() + "',Name='" + member.getName() + "',Password='" + member.getPassword() + "',Role='" + member.getRole() + "' WHERE id=" + member.getId();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){} 
    }
}
