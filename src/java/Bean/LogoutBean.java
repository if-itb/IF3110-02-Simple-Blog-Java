/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author wira gotama
 */
@ManagedBean
@RequestScoped
public class LogoutBean {
    
    public String logout(){
        CookieHelper.setCookie("userID", "-1", 100);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.jsf?faces-redirect=true";
    }
}
