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
 * @author pc asus
 */
public class PostDeleteBean {
     /**
     * Creates a new instance of DeleteUserBean
     */
    private Post deleted;
    public PostDeleteBean() {
        deleted = new Post();
    }

    /**
     * @return the edited
     */
    public Post getEdited() {
        return deleted;
    }

    /**
     * @param edited the edited to set
     */
    public void setDeleted(Post delted) {
        this.deleted = deleted;
    }
    public String delete(int PID) {
         NavigationController nb = new NavigationController();
        DAO.PostDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getPostDAO();
        System.out.println("Joshua"+PID);
        if (DB.find(PID) == null) {
            // TODO sambungin dengan error message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Username doesn't exists!"));
        }
        else {
            Post deleted = new Post();
            deleted.setPID(PID);
            DB.delete(deleted);
        }
         return nb.gotoListPost()+ "?faces-redirect=true";
    }
}
