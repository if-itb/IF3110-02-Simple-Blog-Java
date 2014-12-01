/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

/**
 *
 * @author Yusuf
 */
public class User {

    private int uid;
    private String fullname;
    private String username;
    private String password;
    private String role;
    private String email;

    public User() {
        uid = 0;
        username = "guest";
        password = "pass";
        role = "guest";
        email = "";
        fullname = "guest";
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
    
    public String getFullname(){
        return fullname;    
    }
    
    public void setFullname(String fullName){
        this.fullname = fullName;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
}
