package id.ac.itb.informatika.wbd.jsf;

import id.ac.itb.informatika.wbd.jpa.controller.PostJpaController;
import id.ac.itb.informatika.wbd.jpa.entities.Post;
import javax.faces.context.FacesContext;

public class PostController {

    public PostController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
    }
    
    private Post post = null;
    private PostJpaController jpaController = null;
    
}
