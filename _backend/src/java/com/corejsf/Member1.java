/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "member")
@RequestScoped
public class Member1 {
    private int id;
    private String email;
    private String name;
    private String password;
    private String role;
    
    public int getId(){
        return id;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRole(String role){
        this.role = role;
    }
}
