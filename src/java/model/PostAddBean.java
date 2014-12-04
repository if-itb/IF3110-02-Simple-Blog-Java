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
public class PostAddBean {
    
    private UserBean user;
    // TODO semua, sambungin ke List-User.xhtml, dao, dan teman - teman
    private Post post;
    /**
     * Creates a new instance of AddUserBean
     */
    public PostAddBean() {
        post = new Post();
    }

    /**
     * @return the user
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param user the user to set
     */
    public void setPost(Post post) {
        this.post = post;
    }
    
    public String add() {
        NavigationController nb = new NavigationController();
        post.setAuthor(getUser().getUsername());
        DAO.PostDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getPostDAO();
        DB.create(post);
        return nb.gotoListPost()+ "?faces-redirect=true";
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
    }

