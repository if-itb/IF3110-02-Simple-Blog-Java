package id.ac.itb.informatika.wbd.jsf;

import id.ac.itb.informatika.wbd.jpa.controller.PostJpaController;
import id.ac.itb.informatika.wbd.jpa.entities.Post;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class PostController {

    public PostController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (PostJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "postJpa");
        converter = new PostConverter();
    }
    
    private Post postCurrent = null;
    private List<Post> postList = null;
    private PostJpaController jpaController = null;
    private PostConverter converter = null;
    
    public Post getPostCurrent() {
        if (postCurrent == null) {
            postCurrent = (Post) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPost", converter, null);
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
    
    public Converter getConverter() {
        return converter;
    }
    
    public String newPost() {
        String postString = converter.getAsString(FacesContext.getCurrentInstance(), null, postCurrent);
        String currentPostString = JsfUtil.getRequestParameter("jsfcrud.currentPost");
	
        try {
            if (postCurrent.getId() == null) {
                jpaController.create(postCurrent);
                JsfUtil.addSuccessMessage("Post sudah berhasil dibuat");
            } else {
                jpaController.edit(postCurrent);
                JsfUtil.addSuccessMessage("Post sudah berhasil diedit ");
            }
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        postList = jpaController.getAllPosts();
        return "post_list";
        
    }
    
    public String deletePost(Post post) {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPost");
        Long id = new Long(idAsString);
        
        try {
            jpaController.destroy(post.getId());
            JsfUtil.addSuccessMessage("Post berhasil dihapus");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        postList = jpaController.getAllPosts();
        return "post_list";
    }


}
