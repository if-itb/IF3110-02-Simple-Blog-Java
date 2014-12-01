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
public class restorePostBean {

     private List<Post> listDeletedPosts;
    private model_post modelPost;
    

    /**
     * Creates a new instance of homeBean
     */
    public restorePostBean() {
        this.modelPost = new model_post();
    }
    
    public List<Post> getListDeletedPosts() {
        listDeletedPosts = modelPost.getAllDeletedPosts();
        return listDeletedPosts;
    }
    
    public void setListDeletedPosts(List<Post> listDeletedPosts) {
        this.listDeletedPosts = listDeletedPosts;
    }
}
