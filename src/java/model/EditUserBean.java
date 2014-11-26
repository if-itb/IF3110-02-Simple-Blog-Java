/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author calvin-pc
 */
public class EditUserBean {

    private UserBean edited;
    /**
     * Creates a new instance of EditUserBean
     */
    public EditUserBean() {
        edited = new UserBean();
    }

    /**
     * @return the edited
     */
    public UserBean getEdited() {
        return edited;
    }

    /**
     * @param edited the edited to set
     */
    public void setEdited(UserBean edited) {
        this.edited = edited;
    }
    
    public void edit() {
        DAO.UserDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getUserDAO();
        if (DB.find(edited.getUsername()) == null) {
            // TODO sambungin dengan error message
        }
        else {
            DB.update(edited);
        }
    }
}
