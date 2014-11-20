/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author user
 */
public class User {
    private int id;
    private String username;
    private String pass;
    private enum role {owner, editor, admin, guest};
    
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
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    /* Getter */
    public int getId(){
        return this.id;
    }
    
    public int getUsername(){
        return this.id;
    }
    
    public int getPass(){
        return this.id;
    }
    
    
    
    
    
}
    

