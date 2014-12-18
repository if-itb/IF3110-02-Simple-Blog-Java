package Database;

import Model.User;
import Helper.MD5;
import Helper.CookieHelper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Cookie;

/**
 * Representation of data user
 * @author Luthfi Hamid Masykuri
 * @modified Riva Syafri Rachmatullah
 */
@ManagedBean(name="UserData")
@RequestScoped
public class UserData implements Serializable {
    private String table;
    private MySQL db;
    private String CookieData;
    private CookieHelper cook;
    private String username;
    
    /**
     * Create an instance of UserData
     */
    public UserData() {
        table = "user";
        CookieData = "user_blog";
        db = new MySQL();
        cook = new CookieHelper();
    }
    
    /**
     * Get user by its username from database
     * @param user the username
     * @return an instance of user from database
     */
    public User getUser(String user) {
        try {
            this.db.Where("username=", user);
            ResultSet Data = this.db.Select(table);
            if (Data.first()) {
                String username = Data.getString("username");
                String password = Data.getString("password");
                String role = Data.getString("role");
                String name = Data.getString("name");
                String email = Data.getString("email");
                return new User(username, password, role, name, email);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Validate user and pass
     * @param user username input
     * @param pass password input
     * @return String status to pass
     */
    public String validate(String user,String pass) {
        try {
            this.db.Where("username=", user);
            this.db.Where("password=", MD5.getMD5(pass));
            ResultSet Data = this.db.Select(table);
            if (Data.first()) {
                cook.setCookie(CookieData,user, 24*3600);
                return "valid";
            } else {
                return "invalid";
            }
        } catch (Exception e) {
            return "invalid";
        }
    }
    
    /**
     * Add new user to database
     * @param user an instance of user that want to be added to database
     * @return String status to pass
     */
    public String addUser(User user) {
        String col[] = {"username", "password", "role", "name", "email"};
        String val[] = new String[5];
        val[0] = user.getUsername();
        val[1] = user.getPassword();
        val[2] = user.getRole();
        val[3] = user.getName();
        val[4] = user.getEmail();
        int query = this.db.Insert(table, col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
    }
    
    /**
     * Update a tuple of user in database with an instance of user
     * @param username the username in database that want to be found
     * @param user new instance of user that will change the tuple of data selected
     * @return String status to pass
     */
    public String updateUser(User user) {
        this.db.Where("username=", username);
        String col[] = {"username", "password", "role", "name", "email"};
        String val[] = new String[5];
        val[0] = user.getUsername();
        val[1] = user.getPassword();
        val[2] = user.getRole();
        val[3] = user.getName();
        val[4] = user.getEmail();   
        int query = this.db.Update(table, col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
    }
    
    /**
     * Get all user from database
     * @return list of user
     */
    public List<User> getAllUser() {
        try {
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<User> ListUser = new LinkedList();
            while (isExist) {
                String username = Data.getString("username");
                String password = Data.getString("password");
                String role = Data.getString("role");
                String name = Data.getString("name");
                String email = Data.getString("email");
                User user =  new User(username, password, role, name, email);
                ListUser.add(user);
                isExist = Data.next();
            }
            return ListUser;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Delete user from database
     * @param username the deleted username
     * @return String status to pass
     */
    public String delUser(String username) {
        this.db.Where("username=", username);
        this.db.Delete(table);
        return "deleted";
    }
    
    /**
     * Get which user is logged in
     * @return the user which is logged in
     */
    public User userLoggedIn() {
        Cookie cookie = cook.getCookie(CookieData);
        if (cookie != null) {
            String username = cookie.getValue();
            User user = this.getUser(username);
            return user;
        } else {
            return null;
        }
    }
    
    /**
     * Check whether any user is logged in web
     * @return boolean status
     */
    public boolean is_logged_in() {
        Cookie cookie = cook.getCookie(CookieData);
        return (cookie != null);
    }
    
    /**
     * Log out from web
     * @return boolean status
     */
    public boolean logout() {
        cook.setCookie(CookieData, "", 0);
        return true;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
