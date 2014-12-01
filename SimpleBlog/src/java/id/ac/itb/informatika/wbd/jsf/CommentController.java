package id.ac.itb.informatika.wbd.jsf;

import id.ac.itb.informatika.wbd.jpa.controller.CommentJpaController;
import id.ac.itb.informatika.wbd.jpa.entities.Comment;
import id.ac.itb.informatika.wbd.jpa.entities.Post;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class CommentController {

    public CommentController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CommentJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "commentJpaControl");
    }
    
    private Comment commentCurrent = null;
    private List<Comment> commentList = null;
    private CommentJpaController jpaController = null;
    
    public Comment getCommentCurrent() {
        if (commentCurrent == null) {
            commentCurrent = new Comment();
        }
        return commentCurrent;
    }

    public void setCommentCurrent(Comment commentCurrent) {
        this.commentCurrent = commentCurrent;
    }
 
    public List<Comment> getCommentList() {
        if (commentList == null) {
            //JsfUtil.addSuccessMessage(Long.toString(postController.getPostCurrent().getId()));
            //commentList = jpaController.getPostComments(id);
            //loadComment();
            commentList = new  ArrayList<Comment>();
        }
        return commentList;
    }
    
    public int getCommentCount() {
        return commentList.size();
    }
    
    public String loadComment(Long id) {
        //commentList = jpaController.getPostComments(postController.getPostCurrent().getId());
        commentList = jpaController.getPostComments(id);
        return null;
    }
    
    public String newComment(Long id) {
        try {
            commentCurrent.setPost(id);
            jpaController.create(commentCurrent);
            JsfUtil.addSuccessMessage("Comment sudah berhasil dibuat");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return null;
    }
}
