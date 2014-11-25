/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Database.DatabaseAccess;
import Model.Post;
import Model.Posts;
import Model.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class AddPostBean {
    
    @ManagedProperty(value="#{user}")
    User user;
    
    Post post;

    public AddPostBean() {
        post = new Post();
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    public void setUser(User newUser){
        user = newUser;
    }
    
    public User getUser(){
        return user;
    }
    
    public String addPost(){
        post.setId(user.getId());
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
            System.out.println("Berhasil membuka koneksi");
        } catch (SQLException ex) {
            Logger.getLogger(AddPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Posts.getInstance().insertPostDB(post);
        try {
            dbManager.closeConnection();
            System.out.println("Berhasil menutup koneksi");
        } catch (SQLException ex) {
            Logger.getLogger(AddPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "new_post?faces-redirect=true";
    }
}
