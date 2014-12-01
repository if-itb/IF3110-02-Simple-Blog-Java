/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Database.DatabaseAccess;
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
public class EditPostBean {
    
    @ManagedProperty(value="#{param.postId}")
    private String postId;
    
    private Post post;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Post getPost() {
        if (post==null) {
            if (postId!=null){
                getPostFromPostId();
            }
            else {
                post = new Post();
            }
        }
        return post;
    }
    
    private void getPostFromPostId() {
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        post = Posts.getInstance().findPost(Integer.parseInt(postId));
        
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(EditPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    public String editPost() {
        
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        try {
            dbManager.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        post.setId(Integer.parseInt(postId));
        Posts.getInstance().updatePostDB(post);
        
        try {
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(EditPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "user_management.jsf";
        
    }
    
}
