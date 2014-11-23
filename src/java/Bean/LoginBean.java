package Bean;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevinyu
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }
    
    public String getUsername() {
        return username;
    }
   
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String doNavigation() {
        boolean isExist = true;
        if (isExist){
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("isLogin","yes");
            return "index?faces-redirect=true";
        }
        else {
            return "login";
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
}
