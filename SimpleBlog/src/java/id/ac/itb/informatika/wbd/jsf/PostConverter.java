package id.ac.itb.informatika.wbd.jsf;

import id.ac.itb.informatika.wbd.jpa.controller.PostJpaController;
import id.ac.itb.informatika.wbd.jpa.entities.Post;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class PostConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Long id = new Long(string);
        PostJpaController controller = (PostJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "postJpaController");
        return controller.findPost(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Post) {
            Post o = (Post) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.Customer");
        }
    }

}

