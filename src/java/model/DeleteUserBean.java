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
public class DeleteUserBean {

    /**
     * Creates a new instance of DeleteUserBean
     */
    public DeleteUserBean() {
    }
    
    public void delete(String username) {
        DAO.UserDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getUserDAO();
        if (DB.find(username) == null) {
            // TODO sambungin dengan error message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Username doesn't exists!"));
        }
        else {
            UserBean deleted = new UserBean();
            deleted.setUsername(username);
            DB.delete(deleted);
        }
    }
    
}
