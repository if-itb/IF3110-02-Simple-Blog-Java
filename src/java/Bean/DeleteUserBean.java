/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Database.DatabaseAccess;
import Model.User;
import Model.Users;
import Test.UsersTest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class DeleteUserBean {
    
    private User user;
    
    @ManagedProperty(value="#{param.username}")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public DeleteUserBean() {
        user = new User();
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
    
    public String deleteUser() {
        
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (username!=null){
            user.setUsername(username);
        }
        Users.getInstance().deleteUserByUsername(user);
        
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "read_user?faces-redirect=true";
    }
    
}
