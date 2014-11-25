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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class IndexBean {
    
    private ArrayList<Post> posts;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
    
    public IndexBean() {
        
        try {
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
            
            dbManager.openConnection();
            posts = Posts.getInstance().getPublishedPost();
            Collections.reverse(posts);
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String goToEditPost(){
        return "admin_edit_post?faces-redirect=true";
    }
}
