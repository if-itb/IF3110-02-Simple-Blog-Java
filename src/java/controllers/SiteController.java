/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.servlet.http.*;
import models.User;

@ManagedBean(name="siteController")
@RequestScoped
public class SiteController implements Serializable{
    private User loginForm;
    
    public SiteController(){
        loginForm = new User();
    }
    public String showLogin(){
        return "login";
    }
    /**
     * test the email and password.
     * if valid, set the userIdentity to the given email
     */
    public void submitLogin() {
        //System.out.println(loginForm.getEmail());
        //System.out.println(loginForm.getPassword());
        
    }
    
    public User getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(User loginForm) {
        this.loginForm = loginForm;
    }
    
}
