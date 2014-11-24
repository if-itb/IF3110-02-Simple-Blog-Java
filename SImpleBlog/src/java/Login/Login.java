/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Rikysamuel
 */
public class Login {
    public Login(){
        
    }
    
    public void setCookie(String Username, String Password){
//        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        String Username = request.getParameter("login_username");
//        String Password = request.getParameter("login_password");
        
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
        
        Cookies = request.getCookies();
        if (Cookies!=null && Cookies.length>0){
            for (Cookie Cookie : Cookies) {
                if (Cookie.getName().equals("username")){
                    System.out.println("user length: " + Cookies.length);
                    userCookie = Cookie;
                    return userCookie;
                }
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
                    System.out.println("password length: " + Cookies.length);
                    passCookie = Cookie;
                    return passCookie;
                }
            }
        }
        return null;
    }
    
    public void delUserCookie(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] Cookies;
        
        Cookies = request.getCookies();
        if (Cookies!=null && Cookies.length>0){
            for (Cookie Cookie : Cookies) {
                if (Cookie.getName().equals("username")){
                    System.out.println("username found!");
                    Cookie.setValue(null);
                    Cookie.setPath(request.getContextPath());
                    Cookie.setMaxAge(0);
                    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                    response.addCookie(Cookie);
                    System.out.println(Cookie.getMaxAge());
                }
            }
        }
    }
    
    public void delPassCookie(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] Cookies;
        
        Cookies = request.getCookies();
        if (Cookies!=null && Cookies.length>0){
            for (Cookie Cookie : Cookies) {
                if (Cookie.getName().equals("password")){
                    System.out.println("password found!");
                    Cookie.setValue(null);
                    Cookie.setMaxAge(0);
                    Cookie.setPath(request.getContextPath());
                    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                    response.addCookie(Cookie);
                    System.out.println(Cookie.getMaxAge());
                }
            }
        }
    }
    
}
