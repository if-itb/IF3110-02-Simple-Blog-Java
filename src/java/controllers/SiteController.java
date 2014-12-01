/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.*;
import models.User;
import services.CookieService;
import services.DBConnector;

@ManagedBean(name = "siteCtrl")
@SessionScoped
public class SiteController implements Serializable {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private User loginForm;
    
    public SiteController() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = req.getSession();
        User userIdentity = (User) session.getAttribute("userIdentity");
        if (userIdentity == null || userIdentity.getIsGuest()) {
            CookieService.loginWithCookies();
        }
    }

    public String showLogin() {
        loginForm = new User();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return req.getContextPath() + "/faces/login.xhtml";
    }

    public String doLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        //Map<String, Object> sessionMap =  context.getExternalContext().getSessionMap();
        User userIdentity = (User) req.getSession().getAttribute("userIdentity");
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM user WHERE email='" + loginForm.getEmail() + "' AND password=SHA1('" + loginForm.getPassword() + "')";
            System.out.println(query);
            ResultSet result = st.executeQuery(query);

            //if exist
            if (result.next()) {
                userIdentity.setId(result.getInt("id"));
                userIdentity.setEmail(result.getString("email"));
                userIdentity.setNama(result.getString("nama"));
                userIdentity.setIsLoggedIn(true);
                CookieService.setCookie("email", userIdentity.getEmail(), CookieService.DEFAULT_AGE);
                CookieService.setCookie("password", userIdentity.getPassword(), CookieService.DEFAULT_AGE);
                req.getSession().setAttribute("userIdentity", userIdentity);
                return SUCCESS;
            } else {
                return FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return FAIL;
        }
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

        User userIdentity = (User) req.getSession().getAttribute("userIdentity");
        userIdentity.clearAttributes();
        req.getSession().setAttribute("userIdentity",userIdentity);
        CookieService.clearCookie("email");
        CookieService.clearCookie("password");
        return req.getContextPath()+"/faces/post/index.xhtml";
    }

    public User getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(User loginForm) {
        this.loginForm = loginForm;
    }

}
