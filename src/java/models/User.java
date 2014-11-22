package models;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CookieService;
import services.DBConnector;

@ManagedBean(name="userIdentity")
@SessionScoped
public class User implements Serializable {

    private boolean loggedIn;
    private int id;
    private String email;
    private String password;
    private String nama;
    private boolean isAdmin;
    private boolean isOwner;
    private boolean isEditor;

    private final String tablename = "user";
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.loggedIn = isLoggedIn;
    }

    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public boolean isEditor() {
        return isEditor;
    }

    public void setIsEditor(boolean isEditor) {
        this.isEditor = isEditor;
    }

    public String login() {
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM " + tablename + " WHERE email='" + email + "' AND password=SHA1('" + password + "')";
            System.out.println(query);
            ResultSet result = st.executeQuery(query);
            
            //if exist
            
            
            //User user = (User) req.getSession().getAttribute("userIdentity");
            
            if (result.next()) {
                this.setId(result.getInt("id"));
                this.setEmail(result.getString("email"));
                this.setNama(result.getString("nama"));
                this.setIsLoggedIn(true);
                CookieService.setCookie("email",email,CookieService.DEFAULT_AGE);
                CookieService.setCookie("password",password,CookieService.DEFAULT_AGE);
            } else {
                //email doesn't exist
            }
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String logout() {
        this.setIsLoggedIn(false);
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        //request.getSession().invalidate();
        
        CookieService.clearCookie("email");
        CookieService.clearCookie("password");
        return request.getContextPath() + "/login.xhtml";
    }
    

}
