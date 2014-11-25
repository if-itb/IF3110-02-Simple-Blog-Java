/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author calvin-pc
 */
public class LoginBean {

    private String username;
    private String password;
    private UserBean user;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        username = "";
        password = "";
    }
    
    public String login() {
        // TODO  Sinkronisasi dengan database
        NavigationController nb = new NavigationController();
        if (getUsername().equals("budi") && getPassword().equals("kecil")) {
            user.setRole(2);
            return nb.gotoListPost();
        }
        else return nb.gotoLogin();
        
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }
}
