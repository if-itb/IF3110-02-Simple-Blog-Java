/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.User;
import java.util.List;
import model.model_user;

/**
 *
 * @author A46CB
 */
public class listUserBean {
private List<User> listUsers;
    private model_user modelUser;
    

    /**
     * Creates a new instance of homeBean
     */
    public listUserBean() {
        this.modelUser = new model_user();
    }
    
    public List<User> getListUsers() {
        listUsers = modelUser.getAllUsers();
        return listUsers;
    }
    
    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }
}
