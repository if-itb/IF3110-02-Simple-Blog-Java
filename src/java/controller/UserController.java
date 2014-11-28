package controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import model.User;

@ManagedBean(name = "userController", eager = true)
@SessionScoped
public class UserController implements Serializable {
    // current active user
    private User user = null;
    // dummy user to store the login data
    private User dummy;
    private boolean rememberMe;
    private boolean invalidLogin;
    private boolean invalidEdit;
    
    private final DBController dbController;
    
    public UserController() throws SQLException, ClassNotFoundException {
        dbController = DBController.getInstance();
        dummy = new User("","","","");
        // retrieve cookie
        Cookie cookie = CookieController.getCookie();
        if (cookie == null) {
            if (user == null) user = new User();
        }
        else {
            user = getUserByUsername(CookieController.decrypt(cookie));
        }
        invalidLogin = invalidEdit = false;
    }
    
    public User getUser() {
        return user;
    }
    
    // Change the current active user
    // Show message if the process failed
    public void login() throws IOException {
        login(dummy.getUsername(), dummy.getPassword(), rememberMe);
    }
    public void login(String username, String password, boolean rememberMe) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (username.length() == 0 || password.length() == 0) {
            // field is empty
            context.addMessage("_login-form:_password", new FacesMessage("Username dan Password tidak boleh kosong!"));
            setInvalidLogin(true);
        }
        else {
            User dummy = getUserByUsername(username);
            if (dummy == null) {
                // user is not exist
                context.addMessage("_login-form:_password", new FacesMessage("Username atau Password salah!"));
                setInvalidLogin(true);
            }
            else if (!password.equals(dummy.getPassword())) {
                // password mismatch
                context.addMessage("_login-form:_password", new FacesMessage("Username atau Password salah!"));
                setInvalidLogin(true);
            }
            else {
                this.user = dummy;
                // set cookie if the user wants to be remembered
                if (rememberMe) {
                    CookieController.setCookie(user);
                }
                setInvalidLogin(false);
                context.getExternalContext().redirect("index.xhtml");
            }
        }
    }
    
    public void logout() throws IOException {
        user = new User();
        CookieController.removeCookie();
        // refresh page?
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    // Add a new user to the database
    public void addUser(String username, String password, String email, String role) {
        addUser(new User(username, password, email, role));
    }
    public void addUser(User user) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            // check if the username is already exist
            if (getUserByUsername(user.getUsername()) != null) {
                context.addMessage("_add-user-form:_username", new FacesMessage("Username telah dipakai!"));
            }
            // else, execute update
            else {
                dbController.executeUpdate("INSERT INTO `user`(`username`, `password`, `email`, `role`)  VALUES (?, ?, ?, ?)",
                        new String[]{user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()});
            }
        }
        catch (SQLException e) {
            context.addMessage("_add-user-form:_username", new FacesMessage("Terjadi kesalahan pada basis data: " + e.getMessage()));
        }
    }
    
    // Remove an existing user
    public void removeUser(int id) throws SQLException {
        dbController.executeUpdate("DELETE FROM `user` WHERE `id` = ?",
                new String[]{Integer.toString(id)});
    }
    
    // modify an existing user
    public void modifyUser(int id, String username, String password, String email, String role) throws IOException {
        User dummy = new User(username, password, email, role);
        User old = getUserById(id);
        if (password.isEmpty()) {
            dummy.setPassword(old.getPassword());
        }
        dummy.setId(id);
        modifyUser(dummy);
    }
    public void modifyUser(User user) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            // check if the username is already exist
            ResultSet result = dbController.executeQuery("SELECT * FROM `user` WHERE `username` = ? AND NOT `id` = ?", new String[]{user.getUsername(), Integer.toString(user.getId())});
            User other = null;
            while (result.next()) {
                other = new User(result);
            }
            if (other != null) {
                context.addMessage("_edit-user-form:_username", new FacesMessage("Username telah dipakai!"));
                setInvalidEdit(true);
            }
            // else, execute update
            else {
                dbController.executeUpdate("UPDATE `user` SET `username` = ?, `password` = ?, `email` = ?, `role` = ? WHERE `id` = ?",
                        new String[]{user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), Integer.toString(user.getId())});
                setInvalidEdit(false);
            }
        }
        catch (SQLException e) {
            context.addMessage("_edit-user-form:_username", new FacesMessage("Terjadi kesalahan pada basis data: " + e.getMessage()));
            setInvalidEdit(true);
        }
    }
    
    public User getUserById(int id) {
        try {
            ResultSet result = dbController.executeQuery("SELECT * FROM `user` WHERE `id` = ?",
                    new String[]{Integer.toString(id)});
            while (result.next()) {
                return new User(result);
            }
            return null;
        }
        catch (SQLException e) {
            return null;
        }
    }
    
    private User getUserByUsername(String username) {
        try {
            ResultSet result = dbController.executeQuery("SELECT * FROM `user` WHERE `username` = ?", new String[]{username});
            while (result.next()) {
                return new User(result);
            }
            return null;
        }
        catch (SQLException e) {
            return null;
        }
    }
    
    public List<User> getUserList() throws SQLException {
        List<User> userList = new ArrayList<>();
        try {
            ResultSet result = dbController.executeQuery("SELECT * FROM `user`");
            while (result.next()) {
                userList.add(new User(result));
            }
        }
        catch (SQLException e) {
            // do nothing
        }
        return userList;
    }

    /**
     * @return the dummy
     */
    public User getDummy() {
        return dummy;
    }

    /**
     * @param dummy the dummy to set
     */
    public void setDummy(User dummy) {
        this.dummy = dummy;
    }

    /**
     * @return the rememberMe
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * @param rememberMe the rememberMe to set
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * @return the invalidLogin
     */
    public boolean isInvalidLogin() {
        return invalidLogin;
    }

    /**
     * @param invalidLogin the invalidLogin to set
     */
    public void setInvalidLogin(boolean invalidLogin) {
        this.invalidLogin = invalidLogin;
    }

    /**
     * @return the invalidEdit
     */
    public boolean isInvalidEdit() {
        return invalidEdit;
    }

    /**
     * @param invalidEdit the invalidEdit to set
     */
    public void setInvalidEdit(boolean invalidEdit) {
        this.invalidEdit = invalidEdit;
    }
}
