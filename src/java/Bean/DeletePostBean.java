/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Database.DatabaseAccess;
import Model.Comments;
import Model.Post;
import Model.Posts;
import Test.UsersTest;
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
public class DeletePostBean {
    
    @ManagedProperty(value="#{param.postId}")
    private String postId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
    
    public String softDeletePost() {
        
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Post post = Posts.getInstance().findPost(Integer.parseInt(postId));
        post.setDeleted(true);
        Posts.getInstance().updatePostDB(post);
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "user_management.jsf?faces-redirect=true";
    }
    
    public String deletePost() {
        
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Posts.getInstance().removePost(Integer.parseInt(postId));
        Comments.getInstance().deleteCommentByPostId(Integer.parseInt(postId));
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "deleted_post.jsf?faces-redirect=true";
    }
    
    public String undeletePost() {
        
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Post post = Posts.getInstance().findPost(Integer.parseInt(postId));
        post.setDeleted(false);
        Posts.getInstance().updatePostDB(post);
        
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "deleted_post.jsf?faces-redirect=true";
    }
    
}
