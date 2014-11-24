/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.Post;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.model_post;

/**
 *
 * @author A46CB
 */
public class editPostBean {

    private model_post modelPost;
    private Post editedPost;
    
    public editPostBean() {
        this.modelPost = new model_post();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id = Integer.parseInt(request.getParameter("id"));
        editedPost = modelPost.getPost(id);
    }
    

    
    public Post getEditedPost() {
        return this.editedPost;
    }
    
    public void setEditedPost(Post editedPost) {
        this.editedPost = editedPost;
    }
    
    public String updatePost() {
       int id = modelPost.updatePost(editedPost);
       return "post.jsp?id="+id+"&faces-redirect=true";
    }
}
