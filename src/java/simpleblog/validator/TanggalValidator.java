/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleblog.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
//import java.sql.Date;
/**
 *
 * @author Asus
 */
@FacesValidator("TanggalValidator")
public class TanggalValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        Date d;
        try {
            d = df.parse((String) value);
        } catch (ParseException ex) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Format tanggal dd-mm-yyy", null));
        }
        
        Date e = new Date();
        if (e.after(d)) {
            
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Tanggal tidak boleh kurang dari tanggal sekarang.", null));
        }
    }

    
}
