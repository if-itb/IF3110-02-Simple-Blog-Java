/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.User;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.model_user;

/**
 *
 * @author A46CB
 */
public class userBean {

  private User user;
    private model_user modelUser;
    
    /**
     * Creates a new instance of userBean
     */
    public userBean() {
        this.modelUser = new model_user();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id = Integer.parseInt(request.getParameter("id"));
        this.user = modelUser.getUser(id);
    }
    
 
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
