/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author calvin-pc
 */
public class UsernameValidator implements Validator{
    
    
    private Pattern pattern;
    private Matcher matcher;
    
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    
    public UsernameValidator(){
            pattern = Pattern.compile(USERNAME_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        String username = (String) value;
        matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            FacesMessage msg =
                    new FacesMessage(" Username validation failed.",
                    "Please provide username using a-z,0-9,,_,- character with length 3-15");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }
}
