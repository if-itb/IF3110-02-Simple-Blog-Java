/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ichwan Haryo Sembodo
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable{
    
    @ManagedProperty(value="#{databaseController}")
    
    private DatabaseController databaseController;
    
    private CookieHelper cookie;
    private User user;
    private String username;
    private String password;
    
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        System.out.println("user ctrl dibuat");
        user = new User();
        cookie = new CookieHelper();
        username = "";
        password = "";
    }

    /**
     * @return the user
     */
    public User getUser() {
        if(databaseController != null){
            user = databaseController.loginValidator(username, password);
        }
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        System.out.println("set user");
        this.user = user;
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
        System.out.println("set username");
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
        System.out.println("set password");
        this.password = password;
    }
    
    public boolean showEditHapus(){
        System.out.println("role diset = " + user.getRole());
        if(user.getRole().equals("admin"))
        {   
            return true;
        }
        else if(user.getRole().equals("owner"))
        {
            return true;
        }
        else if(user.getRole().equals("editor"))
        {
            return true;
        }
        return false;
    }

    /**
     * @return the databaseController
     */
    public DatabaseController getDatabaseController() {
        return databaseController;
    }

    /**
     * @param databaseController the databaseController to set
     */
    public void setDatabaseController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }
    
    public String actionLogin(){
        getUser();
        if(showEditHapus()){
            cookie.setCookie("username", username, 86400);
            cookie.setCookie("password", password, 86400);
            return "";
        }
        return "";
    }

    /**
     * @return the cookie
     */
    public CookieHelper getCookie() {
        return cookie;
    }

    /**
     * @param cookie the cookie to set
     */
    public void setCookie(CookieHelper cookie) {
        this.cookie = cookie;
    }
    
    public boolean isUsernamePasswordExist(){
        if (cookie.getCookie("username") == null 
               && cookie.getCookie("password") == null){
            System.out.println("return false cookie validasi");
           return false;
        }
        System.out.println("set cookie in validasi");
        setUsername(cookie.getCookie("username").getValue());
        setPassword(cookie.getCookie("password").getValue());
        getUser();
        return true;
    }
    
    public String logout(){
        cookie.setCookie("username", null, 0);
        cookie.setCookie("password", null, 0);
        User tmp = new User();
        user = tmp;
        setUsername("");
        setPassword("");
        System.out.println("cookie dihapus");
        return "";
    }
}
