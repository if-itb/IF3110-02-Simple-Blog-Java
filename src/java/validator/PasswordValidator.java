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
public class PasswordValidator implements Validator{
    private Pattern pattern;
    private Matcher matcher;
    
    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{6,20}$";
    
    public PasswordValidator(){
            pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        String password = (String) value;
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            FacesMessage msg =
                    new FacesMessage(" Password validation failed.",
                    "Please provide password using a-z,0-9,,_,- character with length 6-20");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }
}
