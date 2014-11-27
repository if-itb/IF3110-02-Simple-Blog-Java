package Database;

import Model.User;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Representation of data user
 * @author Luthfi Hamid Masykuri
 * @modified Riva Syafri Rachmatullah
 */
public class UserData {
    private String table;
    private MySQL db;
    
    /**
     * Create an instance of UserData
     */
    public UserData() {
        table = "user";
        db = new MySQL();
    }
    
    /**
     * Get user by its username from database
     * @param user the username
     * @return an instance of user from database
     */
    public User getUser(String user) {
        try {
            this.db.openConnection();
            this.db.Where("username=", user);
            ResultSet Data = this.db.Select(table);
            this.db.closeConnection();
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
     * Add new user to database
     * @param user an instance of user that want to be added to database
     */
    public void addUser(User user) {
        String col[] = {"username", "password", "role", "name", "email"};
        String val[] = new String[5];
        val[0] = user.getUsername();
        val[1] = user.getPassword();
        val[2] = user.getRole();
        val[3] = user.getName();
        val[4] = user.getEmail();
        this.db.openConnection();
        this.db.Insert(table, col, val);
        this.db.closeConnection();
    }
    
    /**
     * Update a tuple of user in database with an instance of user
     * @param username the username in database that want to be found
     * @param user new instance of user that will change the tuple of data selected
     */
    public void updateUser(String username, User user) {
        this.db.openConnection();
        this.db.Where("username=", username);
        String col[] = {"username", "password", "role", "name", "email"};
        String val[] = new String[5];
        val[0] = user.getUsername();
        val[1] = user.getPassword();
        val[2] = user.getRole();
        val[3] = user.getName();
        val[4] = user.getEmail();   
        this.db.Update(table, col, val);
        this.db.closeConnection();
    }
    
    /**
     * Get all user from database
     * @return list of user
     */
    public List<User> getAllUser() {
        try 
        {
            this.db.openConnection();
            ResultSet Data = this.db.Select(table);
            this.db.closeConnection();
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
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Delete user from database
     * @param username the deleted username
     */
    public void delUser(String username)
    {
        this.db.openConnection();
        this.db.Where("username=", username);
        this.db.Delete(table);
        this.db.closeConnection();
    }
}
