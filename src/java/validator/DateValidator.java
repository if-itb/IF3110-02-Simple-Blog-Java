/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Afik
 */
public class DateValidator implements Validator{

    private Date Tanggal;
    
    
    public DateValidator(){
            Tanggal = new Date();
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException
    {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        Date tanggal;
        try {
            tanggal = formatter.parse((String) value);
            if (Tanggal.compareTo(tanggal)>-1) {
                FacesMessage msg =
                        new FacesMessage(" Date validation failed.",
                        "Please put newer date");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);

                throw new ValidatorException(msg);
            }
        } catch (ParseException ex) {
            Logger.getLogger(DateValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
