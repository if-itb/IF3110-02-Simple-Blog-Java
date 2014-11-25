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
public class publishPostBean {

     private List<Post> listUnpublishedPosts;
    private model_post modelPost;
    

    /**
     * Creates a new instance of homeBean
     */
    public publishPostBean() {
        this.modelPost = new model_post();
    }
    
    public List<Post> getListUnpublishedPosts() {
        listUnpublishedPosts = modelPost.getAllUnpublishedPosts();
        return listUnpublishedPosts;
    }
    
    public void setListUnpublishedPosts(List<Post> listUnpublishedPosts) {
        this.listUnpublishedPosts = listUnpublishedPosts;
    }
}
