/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.User;
import model.model_user;

/**
 *
 * @author A46CB
 */
public class newUserBean {

    private User newUser;
    private model_user modelUser;
    
    public newUserBean() {
        this.newUser = new User();
        this.modelUser = new model_user();
    }
    
    public User getNewUser() {
        return this.newUser;
    }
    
    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
    
    public String saveNewUser() {
       int id = modelUser.addUser(newUser);
       return "list_user.jsp?faces-redirect=true";
    }
}
