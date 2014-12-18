/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author luthfi
 */
@FacesValidator("FileUploadValidator")
public class FileUploadValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part part = (Part) value;

        
        String fileName = getFileName(part);
        System.out.println("----- validator fileName: " + fileName);
        if(fileName.length() == 0 ) {
                FacesMessage message = new FacesMessage("Error: File name is invalid !!");
                throw new ValidatorException(message);
        } else if (fileName.length() > 50) {
                FacesMessage message = new FacesMessage("Error: File name is too long !!");
                throw new ValidatorException(message);
        }

        
        if ((!"image/gif".equals(part.getContentType()))&&(!"image/png".equals(part.getContentType()))&&(!"image/jpeg".equals(part.getContentType()))) {
                FacesMessage message = new FacesMessage("Error: File type is invalid !!");
                throw new ValidatorException(message);
          }

        
    }
    
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("----- validator partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                        return content.substring(content.indexOf('=') + 1).trim()
                                        .replace("\"", "");
                }
        }
        return "";
    }
}
