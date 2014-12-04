/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


/**
 *
 * @author pc asus
 */
public class PostEditBean {
    
    private Post edited;
    /**
     * Creates a new instance of EditUserBean
     */
    public PostEditBean() {
        edited = new Post();
        edited.setPublished(false);
    }
    
    public void reload() {
        DAO.PostDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getPostDAO();
        if (DB.find(edited.getPID()) != null) {
            Post X;
            X = DB.find(edited.getPID());
            edited = X;
            System.out.println("X="+X.getPID());
        }
    }

    /**
     * @return the edited
     */
    public Post getEdited() {
        return edited;
    }

    /**
     * @param edited the edited to set
     */
    public void setEdited(Post edited) {
        this.edited = edited;
    }
    
    public String edit() {
        System.out.println("EDIT:"+edited.getPID());
        System.out.println("Published:"+edited.isPublished());
        
        NavigationController nb = new NavigationController();
        DAO.PostDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getPostDAO();
            DB.update(edited);
             return nb.gotoListPost()+ "?faces-redirect=true";
    }
}
