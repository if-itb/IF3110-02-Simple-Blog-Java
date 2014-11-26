/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Database.DatabaseAccess;
import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author user
 */

@ManagedBean
@SessionScoped
public class User {
    private int id;
    private String username;
    private String pass;
    private String email;
    private String role;
    
    public User () {
        this.username = "";
        this.pass = "";
        this.email = "";
        this.role = "guest";
    }
    
    public User(int id, String username, String pass, String email)
    {
        this.id = id;
        this.username = username;
        this.pass = pass; 
        this.email = email;
    }
    
    /* Setter */
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String pass) {
        this.pass = pass;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
        
    public void setRole(String newRole) {
        role = newRole;
    }
    
    /* Getter */
    public int getId(){
        return this.id;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return this.pass;
    }
    
     public String getEmail(){
        return this.email;
    }

    public String getRole() {
        return role;
    }
}
    

