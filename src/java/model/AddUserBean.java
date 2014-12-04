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
public class AddUserBean {
    // TODO semua, sambungin ke List-User.xhtml, dao, dan teman - teman
    private UserBean user;
    /**
     * Creates a new instance of AddUserBean
     */
    public AddUserBean() {
        user = new UserBean();
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
    
    public void add() {
        DAO.UserDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getUserDAO();
        if (DB.find(getUser().getUsername()) != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Username already exists!"));
        }
        else {
            DB.create(getUser());
        }
    }
}
