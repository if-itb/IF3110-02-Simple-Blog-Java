/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Rikysamuel
 */
//@ManagedBean(name = "login", eager=true)
//@SessionScoped
public class Login {
    public Login(){
        
    }
    
    public void setCookie(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Username = request.getParameter("login_username");
        String Password = request.getParameter("login_password");
        
        Cookie userCookie = new Cookie("username",Username);
        userCookie.setMaxAge(3600*24);
        HttpServletResponse userResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        userResponse.addCookie(userCookie);
        
        Cookie passCookie = new Cookie("password",Password);
        passCookie.setMaxAge(3600*24);
        HttpServletResponse passResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        passResponse.addCookie(passCookie);
    }
    
    public Cookie getUserCookie(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie userCookie;
        Cookie[] Cookies;
        int i = 0;
        
        Cookies = request.getCookies();
        if (Cookies!=null && Cookies.length>0){
            System.out.println(Cookies.length);
            for (Cookie Cookie : Cookies) {
                System.out.println("usernameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee masuk" + i);
                if (Cookie.getName().equals("username")){
                    System.out.println("usernameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                    userCookie = Cookie;
                    System.out.println(userCookie.getValue());
                    return userCookie;
                }
                i++;
            }
        }
        return null;
    }
    
    public Cookie getPassCookie(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie passCookie;
        Cookie[] Cookies;
        
        Cookies = request.getCookies();
        if (Cookies!=null && Cookies.length>0){
            for (Cookie Cookie : Cookies) {
                if (Cookie.getName().equals("password")){
                    passCookie = Cookie;
                    System.out.println(passCookie.getValue());
                    return passCookie;
                }
            }
        }
        return null;
    }
}
