/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.Post;
import model.model_post;

/**
 *
 * @author A46CB
 */
public class newPostBean {
    
    private Post newPost;
    private model_post modelPost;
    
    /**
     * Creates a new instance of newPostBean
     */
    public newPostBean() {
        this.modelPost = new model_post();
        this.newPost = new Post();
    }
    
    public Post getNewPost() {
//        this.newPost = new Post();
        return this.newPost;
    }
    
    public void setNewPost(Post newPost) {
        this.newPost = newPost;
    }
    
    public String saveNewPost() {
       int id = modelPost.addPost(newPost);
       return "post.jsp?id="+id+"&faces-redirect=true";
    }
}
