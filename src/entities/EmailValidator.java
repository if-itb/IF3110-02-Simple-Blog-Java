package entities;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.myvalid.EmailValidator")
public class EmailValidator implements Validator {
	private static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
		String email = arg2.toString();
		
		if (!pattern.matcher(email).matches()) {
			FacesMessage msg = new FacesMessage("Submit gagal","Email anda salah");
		    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		    throw new ValidatorException(msg);
		}
	}
}
