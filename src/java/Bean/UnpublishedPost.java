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
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class UnpublishedPost {
    
    private ArrayList<Post> unpublishedPosts;

    public ArrayList<Post> getUnpublishedPosts() {
        return unpublishedPosts;
    }

    public void setUnpublishedPosts(ArrayList<Post> unpublishedPosts) {
        this.unpublishedPosts = unpublishedPosts;
    }
    
    public UnpublishedPost() {
        try {
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
            dbManager.openConnection();
            
            unpublishedPosts = Posts.getInstance().getUnpublishedPost();
            
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PublishPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
