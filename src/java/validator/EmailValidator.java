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
public class EmailValidator implements Validator{
    private Pattern pattern;
    private Matcher matcher;
    
    private static final String EMAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\"
            + "@([\\w]+\\.)+[\\w]+[\\w]$";
    
    public EmailValidator(){
            pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        String email = (String) value;
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            FacesMessage msg =
                    new FacesMessage(" Email validation failed.",
                    "Please provide valid Email");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }
}
