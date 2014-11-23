package controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("biggerDateValidator")
public class DateValidator implements Validator {

   @Override
   public void validate(FacesContext facesContext,
      UIComponent component, Object value)
      throws ValidatorException {

		String date = value.toString();

		Date now = new Date();
		Date input_date = new SimpleDateFormat("mm/dd/yy").parse(date, new ParsePosition(0));
		
		int compare = now.compareTo(input_date);
		
		if(compare<0){
			FacesMessage msg = new FacesMessage("Date is not valid");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			throw new ValidatorException(msg);
		}
	}
}