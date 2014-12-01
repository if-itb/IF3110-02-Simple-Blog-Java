/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Database.DatabaseAccess;
import Model.Post;
import Model.Posts;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class PublishPostBean {
    
    @ManagedProperty(value="#{param.postId}")
    private String postId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
  
    public String publishPost() {
        
        
        try {  
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
            dbManager.openConnection();
            
            Post post = Posts.getInstance().findPost(Integer.parseInt(postId));
            post.setPublished(true);
        
            Posts.getInstance().updatePostDB(post);
            
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PublishPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "list_post?faces-redirect=true";
    }

}
