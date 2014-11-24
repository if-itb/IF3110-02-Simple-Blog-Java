/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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

@ManagedBean(name = "userIdentity", eager = true)
@SessionScoped
public class User implements Serializable {

    private boolean isNewRecord;
    private boolean isLoggedIn;
    private int id;
    private String email;
    private String password;
    private String nama;
    private boolean isAdmin;
    private boolean isOwner;
    private boolean isEditor;

    private final String tablename = "user";

    public void clearAttributes(){
        this.setId(0);
        this.setEmail(null);
        this.setPassword(null);
        this.setNama(null);
        this.setIsAdmin(false);
        this.setIsOwner(false);
        this.setIsEditor(false);
    }
    public boolean getIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public boolean getIsEditor() {
        return isEditor;
    }

    public boolean getIsOwner() {
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

    public boolean getIsLoggedIn() {
        return this.isLoggedIn;
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
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM " + tablename + " WHERE email='" + email + "' AND password=SHA1('" + password + "')";
            System.out.println(query);
            ResultSet result = st.executeQuery(query);

            //if exist
            User user = (User) request.getSession().getAttribute("userIdentity");
            if (user == null) {
                user = new User();
            }
            if (result.next()) {

                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setNama(result.getString("nama"));
                user.setIsLoggedIn(true);
                request.getSession().setAttribute("userIdentity", user);
                CookieService.setCookie("email", email, CookieService.DEFAULT_AGE);
                CookieService.setCookie("password", password, CookieService.DEFAULT_AGE);
                return "success";
            } else {
                FacesMessage message = new FacesMessage();
                message.setDetail("Invalid Username/Password combination");
                //message.setSummary("Login Incorrect");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                return "fail";
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String logout() {
        this.setIsLoggedIn(false);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        //request.getSession().invalidate();
        CookieService.clearCookie("email");
        CookieService.clearCookie("password");
        return request.getContextPath() + "/login.xhtml";
    }

    public boolean load(int id) {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT * FROM " + tablename + " WHERE id=" + id + " LIMIT 1";
            ResultSet result = st.executeQuery(query);
            if (result.next()) {
                this.setId(result.getInt("id"));
                this.setEmail(result.getString("email"));
                this.setPassword(result.getString("password"));
                this.setIsAdmin(result.getBoolean("is_admin"));
                this.setNama(result.getString("nama"));
                this.setIsEditor(result.getBoolean("is_editor"));
                this.setIsOwner(result.getBoolean("is_owner"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save() {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            if (this.isNewRecord) {
                String query
                        = "INSERT IGNORE INTO " + tablename
                        + "(email,password,nama,is_admin,is_owner,is_editor)"
                        + "VALUES('" + this.getEmail() + "',SHA1('" + this.getPassword() + "'),'" + this.getNama() + "'," + (this.getIsAdmin() ? 1 : 0) + "," + (this.getIsEditor() ? 1 : 0) + "," + (this.getIsOwner() ? 1 : 0) + ")";
                System.out.println(query);
                st.executeUpdate(query);
            } else {
                String query = "UPDATE " + tablename
                        + " SET email='" + this.getEmail() + "'"
                        + ",password=SHA1('" + this.getPassword() + "')"
                        + ",nama='" + this.getNama() + "'"
                        + ",is_admin=" + (this.getIsAdmin() ? 1 : 0) + ""
                        + ",is_editor=" + (this.getIsEditor() ? 1 : 0) + ""
                        + ",is_owner=" + (this.getIsOwner() ? 1 : 0) + ""
                        + " WHERE id=" + this.getId();
                System.out.println(query);
                st.executeUpdate(query);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete() {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();
            String query = "DELETE FROM " + tablename
                    + "  WHERE id=" + this.id;
            System.out.println(query);
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
