package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ahmad Zaky | 13512076
 * Kelas yang berisi model User
 */
public class User {
    /* Constants for available roles */
    public static final String ROLE_GUEST = "guest";
    public static final String ROLE_OWNER = "owner";
    public static final String ROLE_EDITOR = "editor";
    public static final String ROLE_ADMIN = "admin";
    
    /* Atribut User */
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    public User() {
        id = 0;
        role = User.ROLE_GUEST;
        username = "Guest";
        email = password = "";
    }
    
    /* Konstruktor parameter dari komponen user */
    public User(String username, String password, String email, String role) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    
    /* Konstruktor parameter dari hasil SQL */
    public User(ResultSet result) {
        try {
            id = result.getInt("id");
            username = result.getString("username");
            password = result.getString("password");
            email = result.getString("email");
            role = result.getString("role");
        }
        catch (SQLException e) {
            id = 0;
            role = User.ROLE_GUEST;
            username = "Guest";
            email = password = "";
        }
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
