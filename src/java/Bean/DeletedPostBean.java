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
public class DeletedPostBean {
    
    private ArrayList<Post> deletedPosts;

    public ArrayList<Post> getDeletedPosts() {
        return deletedPosts;
    }

    public void setDeletedPost(ArrayList<Post> deletedPost) {
        this.deletedPosts = deletedPost;
    }

    public DeletedPostBean() {
        try {
            DatabaseAccess dbManager = DatabaseAccess.getInstance();
            dbManager.openConnection();
            
            deletedPosts = Posts.getInstance().getDeletedPost(true);
            
            dbManager.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PublishPostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
