/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
        CookieHelper ck = new CookieHelper();
        username = new String();
        password = new String();
        if (ck.getCookie("username") != null) {
            username = ck.getCookie("username").getValue();
        }
        if (ck.getCookie("password") != null) {
            password = ck.getCookie("password").getValue();
        }
    }
    
    public String login() {
        NavigationController nb = new NavigationController();
        UserBean finded = DAO.DAOFactory.getInstance("javabase.jdbc").getUserDAO().find(username);
        if (finded == null || !finded.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Username atau Password salah"));
            return nb.gotoLogin() ;
        }
        else {
            CookieHelper ck = new CookieHelper();
            int expiry = 60*60*24*30; //30 hari
            user.setUsername(finded.getUsername());
            user.setPassword(finded.getPassword());
            user.setRole(finded.getRole());
            user.setEmail(finded.getEmail());
            ck.setCookie("username", username, expiry);
            ck.setCookie("password", password, expiry);
            return nb.gotoListPost() + "?faces-redirect=true";
        }
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
