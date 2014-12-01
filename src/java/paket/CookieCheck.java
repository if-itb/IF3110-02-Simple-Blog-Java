/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TOSHIBA
 */
public class CookieCheck {

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    
    /**
     * Creates a new instance of CookieCheck
     */
    public String CookieCheck() {
        String cValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!= null && cookies.length>0){
            for (Cookie cookie : cookies){
                if("role".equals(cookie.getName())){
                    cValue = cookie.getValue();
                }
            }
        }
        return cValue;
    }
    
    public boolean isLoggedIn(){
        return CookieCheck()!=null;
    }
    
    public boolean isAdmin(){
        return "admin".equals(CookieCheck());
    }
    
    public String getUName(){
        String UName="Guest";
        Cookie[] cookies = request.getCookies();
        if (cookies!= null && cookies.length>0){
            for (Cookie cookie : cookies){
                if("user".equals(cookie.getName())){
                    UName = cookie.getValue();
                }
            }
        }
        return UName;
    }
    
    public String getUsrName(){
        String UsrName="Guest";
        Cookie[] cookies = request.getCookies();
        if (cookies!= null && cookies.length>0){
            for (Cookie cookie : cookies){
                if("username".equals(cookie.getName())){
                    UsrName = cookie.getValue();
                }
            }
        }
        return UsrName;
    }
}
