/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "postController")
@RequestScoped
public class PostController {

    /**
     * Creates a new instance of PostController
     */
    private ArrayList<Post> posts;
    
    public PostController() {}
    
    public void setPostController(ArrayList<Post> posts){
        this.posts = posts;
    }
    
    public ArrayList<Post> getPostController(){
        return posts;
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
}
