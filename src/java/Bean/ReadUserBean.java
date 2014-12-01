package Bean;

import Database.DatabaseAccess;
import Model.User;
import Model.Users;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
public class ReadUserBean {
    
    private ArrayList<User> userList;

    public ReadUserBean() {
        try {
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
            dbManager.openConnection();
            userList = Users.getInstance().getAllUser();
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ReadUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
}
