/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.User;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.model_post;
import model.model_user;

/**
 *
 * @author A46CB
 */
public class editUserBean {    
    private model_user modelUser;
    private User editedUser;
    
    public editUserBean() {
        this.modelUser = new model_user();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id = Integer.parseInt(request.getParameter("id"));
        editedUser = modelUser.getUser(id);
    }
    

    
    public User getEditedUser() {
        return this.editedUser;
    }
    
    public void setEditedUser(User editedUser) {
        this.editedUser = editedUser;
    }
    
    public String updateUser() {
       int id = modelUser.updateUser(editedUser);
       return "user.jsp?id="+id+"&faces-redirect=true";
    }
}
