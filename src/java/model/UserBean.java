/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author calvin-pc
 */
public class UserBean {
    // TODO Tambah nama user dan email dan kawan - kawan
    private static final int owner = 1;
    private static final int admin = 2;
    private static final int editor = 3;
    private static final int guest = 4;

    private String username;
    private String password;
    /**
     * @return the owner
     */
    public static int getOwner() {
        return owner;
    }

    /**
     * @return the admin
     */
    public static int getAdmin() {
        return admin;
    }

    /**
     * @return the editor
     */
    public static int getEditor() {
        return editor;
    }

    /**
     * @return the guest
     */
    public static int getGuest() {
        return guest;
    }
    
    private int role;
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        role = guest;
        username = new String();
        password = new String();
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }
    
    public String getRoleName() {
        if (role == owner) return "owner";
        else if (role == admin) return "admin";
        else if (role == editor) return "editor";
        else return "guest";
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
}
