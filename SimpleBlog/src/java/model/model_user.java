/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Post;
import entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A46CB
 */
public class model_user {
    private Connection conn;
    String output2 = "";
    
    public model_user() {
        String databaseURL = "jdbc:mysql://localhost:3306/simpleblog";
        String username = "root";
        String password = "";
        try {
             try {
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(Exception e) {
                    System.out.println("Unable to load Driver");
                    output2 = output2 + "drivernya";
                }
            // Step 1: Create a database "Connection" object
            conn = DriverManager.getConnection(databaseURL, username, password);
       
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
            System.out.println("Unable to connect to database");
        } 
        /* finally {
            System.out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } 
        } */
    }
    
    public List<User> getAllUsers() {
        Statement stmt = null;
        List<User> users = new ArrayList<User>();
        try {
            stmt = conn.createStatement();
            String sqlStr = "SELECT * FROM user";
            ResultSet rset = stmt.executeQuery(sqlStr); 
            while (rset.next()) {
                User user = new User();
                user.setId(rset.getInt("id"));
                user.setEmail(rset.getString("email"));
                user.setPassword(rset.getString("password"));
                user.setNama(rset.getString("nama"));
                user.setRole(rset.getString("role"));
                users.add(user);  
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        return users;
    }
    
    
    public User getUser(int id) {
        Statement stmt = null;
        User user = new User();
        
        try {
            stmt = conn.createStatement();
            String sqlStr = "SELECT * FROM user WHERE id ="
                   + "'" + id + "'";
            ResultSet rset = stmt.executeQuery(sqlStr); 
            while (rset.next()) {
                user.setId(rset.getInt("id"));
                user.setEmail(rset.getString("email"));
                user.setPassword(rset.getString("password"));
                user.setNama(rset.getString("nama"));
                user.setRole(rset.getString("role"));               
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        return user;
    }
    
    public int addUser(User user) {
        Statement stmt = null;
        int id = 0;
        String out ="";
        try {
            
            stmt = conn.createStatement();
            String sqlStr = "INSERT INTO user (email, password, nama, role) VALUES ('" + user.getEmail()+ "','" + user.getPassword() + "','" + user.getNama() + "', '"+ user.getRole() +"')";
            //String sqlStr = "INSERT INTO post (judul, tanggal, konten) VALUES ('tesjudul','2014-11-22','dsasdfasdfas')";
            out = sqlStr;
            stmt.executeUpdate(sqlStr, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
              id = rs.getInt(1);
            }
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        
        return id;
    }
    
    public int updateUser(User user) {
        Statement stmt = null;
        int id = 0;
        String out ="";
        try {
           
            stmt = conn.createStatement();
//            String sqlStr = "INSERT INTO post (judul, tanggal, konten) VALUES ('" + post.getJudul() + "','" + tanggalPost + "','" + post.getKonten() + "')";
            String sqlStr = "UPDATE post SET email='"+user.getEmail()+"', password='"+user.getPassword()+"', nama='"+user.getNama()+"', role='"+user.getRole()+"' WHERE id='"+user.getId()+"'";
            out = sqlStr;
            stmt.executeUpdate(sqlStr);
            
            id = user.getId();
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            output2 = output2 + ex.toString();
        } 
        
        return id;
    }
}
