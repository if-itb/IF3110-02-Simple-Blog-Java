/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author calvin-pc
 */
public class AllUserBean {
    
    private ArrayList <UserBean> allUser; 
    /**
     * Creates a new instance of AllUserBean
     */
    public AllUserBean() {
        UserBean X = new UserBean(); X.setRole(UserBean.getAdmin());
        UserBean Y = new UserBean(); Y.setRole(UserBean.getEditor());
        UserBean Z = new UserBean(); Z.setRole(UserBean.getOwner());
        allUser = new ArrayList<UserBean> ();
        allUser.add(X);
        allUser.add(Y);
        allUser.add(Z);
    }

    /**
     * @return the allUser
     */
    public ArrayList <UserBean> getAllUser() {
        return allUser;
    }

    /**
     * @param allUser the allUser to set
     */
    public void setAllUser(ArrayList <UserBean> allUser) {
        this.allUser = allUser;
    }
    
}
