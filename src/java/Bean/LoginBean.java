package Bean;


import Database.DatabaseAccess;
import Model.User;
import Model.Users;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    
    @ManagedProperty(value="#{user}")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUsername() {
        return username;
    }
   
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String doNavigation() {
        boolean isExist = true;
        User new_user;
        new_user = Users.getInstance().validateUser(username,password);
        isExist = new_user!=null;  
        
        if (isExist){
            user.setId(new_user.getId());
            user.setPassword(new_user.getPassword());
            user.setRole(new_user.getRole());
            user.setUsername(new_user.getUsername());
            user.setEmail(new_user.getEmail());
            HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            CookieHelper.setCookie("userID", Integer.toString(user.getId()), 100);
            return "user_management?faces-redirect=true";
        }
        else {
            return "login?faces-redirect=true";
        }
    }
    
    public void check() {
        Cookie cookie = CookieHelper.getCookie("userID");
        User new_user=null;
        if (cookie != null){
            
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
            
            try {
                dbManager.openConnection();
            } catch (SQLException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            new_user = Users.getInstance().findUser(Integer.parseInt(cookie.getValue()));
            try {
                dbManager.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (new_user!=null) {
                user.setId(new_user.getId());
                user.setPassword(new_user.getPassword());
                user.setRole(new_user.getRole());
                user.setUsername(new_user.getUsername());
                user.setEmail(new_user.getEmail());
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("user_management.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

