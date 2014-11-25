package id.ac.itb.informatika.wbd.jsf;

import id.ac.itb.informatika.wbd.jpa.controller.PostJpaController;
import id.ac.itb.informatika.wbd.jpa.entities.Post;
import java.util.List;
import javax.faces.context.FacesContext;

public class PostController {

    public PostController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (PostJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "postJpa");
    }
    
    private Post postCurrent = null;
    private List<Post> postList = null;
    private PostJpaController jpaController = null;
    
    public Post getPostCurrent() {
        if (postCurrent == null) {
            //post = (post) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCustomer", converter, null);
        }
        if (postCurrent == null) {
            postCurrent = new Post();
        }
        return postCurrent;
    }
    
    public List<Post> getPostList() {
        if (postList == null) {
            postList = jpaController.getAllPosts();
        }
        return postList;
    }
    
     public int getPostCount() {
        return postList.size();
    }
    
    public String newPost() {
        try {
            jpaController.create(postCurrent);
            JsfUtil.addSuccessMessage("Post sudah berhasil dibuat");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return "post_list";
        }
        return "post_list";
    }
}
