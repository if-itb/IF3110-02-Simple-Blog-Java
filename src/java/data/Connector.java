/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Connector {
    private Connection connection;
    Connector(){
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }
        String url = "jdbc:mysql://localhost:3306/post";
        String username = "root";
        String password = "";
        connection = null;
        try {
            System.out.println("Connecting database...");
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
    }
    public ArrayList<Post> getPublished(){
        Statement st;
        ArrayList<Post> listPost = null;
        try {
            st = connection.createStatement();
            String sql = ("SELECT * FROM post WHERE Category = 1 ORDER BY date;");
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                Post post = new Post();
                post.setJudul(rs.getString("Title"));
                post.setKonten(rs.getString("Content"));
                post.setKategori(true);
                listPost.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPost;
    }
    public void closeConnection(){
        System.out.println("Closing the connection.");
        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
    }
}
