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
import java.util.ArrayList;

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
    
    public Connection getConnection(){
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
    
    public void addMember(){
        
    }
    
    public void deleteMember(Member1 member) throws SQLException{
        PreparedStatement ps; 
        Connection con = getConnection();
        String sql = "DELETE FROM member WHERE Email =" + member.getEmail();
        ps = con.prepareStatement(sql);
        ps.executeUpdate();
    }
    
    public void editMember(Member1 member) throws SQLException{
    }
}
