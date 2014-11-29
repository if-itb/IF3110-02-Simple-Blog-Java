/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Arina Listyarini DA
 */

@ManagedBean(name = "listmember")
@RequestScoped
public class ListMember {
    private ArrayList<Member1> members;
    /**
     * Creates a new instance of ListMember
     */
    public ListMember() {
        members = new ArrayList<Member1>();
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String driver = "com.mysql.jdbc.Driver";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            Statement sm = con.createStatement();
            ResultSet res = sm.executeQuery("SELECT * FROM member");
            while(res.next()){
                Member1 member = new Member1();
                member.setId(res.getInt("id"));
                member.setEmail(res.getString("Email"));
                member.setName(res.getString("Name"));
                member.setPassword(res.getString("Password"));
                member.setRole(res.getString("Role"));
                members.add(member);
            }
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
    }
    
    public ArrayList<Member1> getMembers(){
        return members;
    }
    
}
