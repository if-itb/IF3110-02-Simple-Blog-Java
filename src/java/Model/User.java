package Model;

import Helper.MD5;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Representation of User
 * @author Luthfi Hamid Masykuri
 * @modified Riva Syafri Rachmatullah
 */
@ManagedBean(name="User")
@RequestScoped
public class User {
    private String username;
    private String password;
    private String role;
    private String name;
    private String email;
    
    /**
     * Create guest user
     */
    public User() {
        username = "";
        password = "";
        role = "";
        name = "";
        email = "";
    }
    
    /**
     * Create instance of user with its information
     * @param username username of user
     * @param password password of user
     * @param role role of user
     * @param name name of user
     * @param email email of user
     */
    public User(String username,String password,String role,String name,String email) {
        this.username = username;
        this.password = MD5.getMD5(password);
        this.role = role;
        this.name = name;
        this.email = email;
    }
    
    /**
     * Get the username of an user
     * @return username of user
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Get the password of an user
     * @return password of user
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Get the role of an user
     * @return role of user
     */
    public String getRole() {
        return role;
    }
    
    /**
     * Get the name of an user
     * @return name of user
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the email of an user
     * @return email of user
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Set the new username of an user
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Set the new password of an user
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = MD5.getMD5(password);
    }
    
    /**
     * Set the new role of an user
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }
    
    /**
     * Set the new name of an user
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Set the new email of an user
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
