/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Database.DatabaseAccess;
import Model.User;
import Model.Users;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class CreateUserBean {
    private User user;

    public CreateUserBean() {
        user = new User();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String createUser() {
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
            System.out.println("Berhasil membuka koneksi");
        } catch (SQLException ex) {
            Logger.getLogger(AddPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Users.getInstance().addUser(user);
        try {
            dbManager.closeConnection();
            System.out.println("Berhasil menutup koneksi");
        } catch (SQLException ex) {
            Logger.getLogger(AddPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "new_user?faces-redirect=true";
    }
    
}
