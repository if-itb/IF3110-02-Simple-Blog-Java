package controller;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter extends DateTimeConverter {

    public DateConverter() {
        setPattern("yyyy-MM-dd");
    }

    @SuppressWarnings("deprecation")
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try{
        	Date date = (Date) super.getAsObject(context, component, value);
            Date now = new Date();            
    		now.setHours(0);
    		now.setMinutes(0);
    		now.setSeconds(0);
            
    		if(date.before(now)){
    			throw new ConverterException("Date is smaller than today");
    		}
    		return (Object) date;
        }catch(ConverterException ex){
        	throw new ConverterException("Invalid format, use this pattern: "+ super.getPattern());
        }
      
    }
}