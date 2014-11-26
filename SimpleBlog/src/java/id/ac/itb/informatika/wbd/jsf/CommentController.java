package id.ac.itb.informatika.wbd.jsf;

import id.ac.itb.informatika.wbd.jpa.controller.CommentJpaController;
import id.ac.itb.informatika.wbd.jpa.entities.Comment;
import java.util.List;
import javax.faces.context.FacesContext;

public class CommentController {

    public CommentController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CommentJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "commentJpa");
    }
    
    private Comment commentCurrent = null;
    private List<Comment> commentList = null;
    private CommentJpaController jpaController = null;
    
    
    public Comment getCommentCurrent() {
        return commentCurrent;
    }
    
    public List<Comment> getCommentList() {
        String currentPostString = JsfUtil.getRequestParameter("jsfcrud.currentPost");
        Long id = Long.getLong(currentPostString);
        if (commentList == null) {
            commentList = jpaController.getPostComments(id);
        }
        return commentList;
    }
    
    public int getCommentCount() {
        return commentList.size();
    }
    
    public String loadComment() {
        return null;
    }
    
    public String newComment() {
        //String postString = converter.getAsString(FacesContext.getCurrentInstance(), null, postCurrent);
        String currentPostString = JsfUtil.getRequestParameter("jsfcrud.currentPost");
	Long postId = Long.getLong(currentPostString);
        try {
            jpaController.create(commentCurrent, postId);
            JsfUtil.addSuccessMessage("Comment sudah berhasil dibuat");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        commentList = jpaController.getPostComments(postId);
        return null;
    }
}
