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
    private enum Role {owner, editor, admin, guest};
    private Role role; 
    
    public User () {}
    
    public User(int id, String username, String pass)
    {
        this.id = id;
        this.username = username;
        this.pass = pass; 
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
    
    public void setRole(String newRole) {
        if (newRole.equals("owner")) {
            role = Role.owner;
        } else if (newRole.equals("admin")) {
            role = Role.admin;
        } else if (newRole.equals("editor")) {
            role = Role.editor;
        } else {
            role = Role.guest;
        }
    }

    public String getRole() {
        if (role==Role.owner) {
            return "owner";
        } else if (role==Role.admin) {
            return "admin";
        } else if (role==Role.editor) {
            return "editor";
        } else {
            return "guest";
        }
    }
}
    

