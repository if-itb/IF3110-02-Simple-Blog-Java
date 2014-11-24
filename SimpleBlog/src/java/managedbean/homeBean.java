/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.Post;
import java.util.List;
import model.model_post;

/**
 *
 * @author A46CB
 */

public class homeBean {
   
    private List<Post> listPosts;
    private model_post modelPost;
    

    /**
     * Creates a new instance of homeBean
     */
    public homeBean() {
        this.modelPost = new model_post();
    }
    
    public List<Post> getListPosts() {
        listPosts = modelPost.getAllPublishedPosts();
        return listPosts;
    }
    
    public void setListPosts(List<Post> listPosts) {
        this.listPosts = listPosts;
    }
}
