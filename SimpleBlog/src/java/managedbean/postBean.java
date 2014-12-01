/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entities.Post;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.model_post;

/**
 *
 * @author A46CB
 */
public class postBean {
    
    private Post post;
    private model_post modelPost;
    
    /**
     * Creates a new instance of postBean
     */
    public postBean() {
        this.modelPost = new model_post();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id = Integer.parseInt(request.getParameter("id"));
        this.post = modelPost.getPost(id);
    }
    
 
    public Post getPost() {
        return this.post;
    }
    
    public void setPost(Post post) {
        this.post = post;
    }
}
