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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class UpdateUserBean {
    
    @ManagedProperty(value="#{param.userId}")
    private String userId;

    private User user;

    public User getUser() {
        if (user==null){
            if (userId!=null) {
                getUserFromUserId();
            }
            else{
                user = new User();
            }
        }
        return user;
    }
    
    private void getUserFromUserId(){
        try {
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
            
            dbManager.openConnection();
            user = Users.getInstance().findUser(Integer.parseInt(userId));
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String updateUser() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        try {
            databaseAccess.openConnection();
            System.out.println("Berhasil membuka koneksi");
	} catch (SQLException e) {
            System.out.println("Gagal membuka koneksi");
            System.out.println(e);
        }
        Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	user.setId(Integer.parseInt(params.get("userId")));
        Users.getInstance().updateUser(this.user);
        
         try {
            databaseAccess.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return "read_user?faces-redirect=true";
    }
}
