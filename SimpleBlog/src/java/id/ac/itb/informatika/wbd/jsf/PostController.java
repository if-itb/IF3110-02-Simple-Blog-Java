package id.ac.itb.informatika.wbd.jsf;

import id.ac.itb.informatika.wbd.jpa.controller.PostJpaController;
import id.ac.itb.informatika.wbd.jpa.entities.Post;
import java.util.List;
import javax.faces.context.FacesContext;

public class PostController {

    public PostController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
    }
    
    private Post post = null;
    private PostJpaController jpaController = null;
    private List<Post> postList = null;
    
    public Post getPost() {
        if (post == null) {
            //post = (post) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCustomer", converter, null);
        }
        if (post == null) {
            post = new Post();
        }
        return post;
    }
    
    public List<Post> getPostList() {
        if (postList == null) {
            postList = jpaController.getAllPosts();
        }
        return postList;
    }
    
    public String newPost() {
        try {
            jpaController.create(post);
            //JsfUtil.addSuccessMessage("Customer was successfully created.");
        } catch (Exception e) {
            //JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            System.out.println("ERROR");
            return "post_list";
        }
        System.out.println("SUCCESS");
        return "post_list";
    }
}
