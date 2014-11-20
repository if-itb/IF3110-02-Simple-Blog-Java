/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {

    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        
        String email = (String) value;
        if (!email.contains("@")){
            FacesMessage message = new FacesMessage();
            message.setDetail("Invalid email");
            throw new ValidatorException(message);
        }
    }
}
