package models;

import java.io.Serializable;
import java.sql.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DBConnector;

@ManagedBean(name = "userIdentity")
@SessionScoped

public class User implements Serializable {

    private boolean isLoggedIn;
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
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
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
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM " + tablename + " WHERE email='" + email + "' AND password=SHA1('" + password + "')";
            System.out.println(query);
            ResultSet result = st.executeQuery(query);

            if (result.next()) {
                this.setId(result.getInt("id"));
                this.setEmail(result.getString("email"));
                this.setNama(result.getString("nama"));
                this.setIsLoggedIn(true);
                setCookie(email, password);
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
        ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true)).invalidate();
        return "post/index";
    }

    public void setCookie(String email, String password) {
        HttpServletResponse res;
        
        res = (HttpServletResponse) FacesContext.getCurrentInstance();
        
        Cookie cookie = new Cookie("email",email);
        cookie.setMaxAge(3600); //3600 detik 
        res.addCookie(cookie);
        
        cookie = new Cookie("password",password);
        cookie.setMaxAge(3600);
        res.addCookie(cookie);
    }

}
