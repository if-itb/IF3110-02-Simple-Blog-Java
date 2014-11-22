/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Security.MD5;

/**
 *
 * @author Luthfi Hamid Masykuri
 */
public class User {
    private String username;
    private String password;
    private String role;
    
    public User()
    {
        username = "";
        password = "";
        role = "";
    }
    
    public User(String username,String password,String role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getRole()
    {
        return role;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setPassword(String password)
    {
        this.password = MD5.getMD5(password);
    }
    
    public void setRole(String role)
    {
        this.role = role;
    }
}
