
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WILLY
 */
@FacesValidator("validator.dateRangeValidator")
public class DateRangeValidator implements Validator {
	@Override
	public void validate (FacesContext context, UIComponent component, Object value) throws ValidatorException {
		System.out.println();
	}
}
