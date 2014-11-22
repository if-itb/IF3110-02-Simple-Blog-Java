/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Model.User;
import java.sql.ResultSet;
import Security.MD5;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Luthfi Hamid Masykuri
 */
public class UserData {
    private String table;
    private MySQL db;
    
    public UserData()
    {
        table = "user";
        db = new MySQL();
    }
    
    public User getUser(String user)
    {
        try
        {
            this.db.Where("username=", user);
            ResultSet Data = this.db.Select(table);
            if (Data.first())
            {
                String username = Data.getString("username");
                String password = Data.getString("password");
                String role = Data.getString("role");
                String name = Data.getString("name");
                String email = Data.getString("email");
                return new User(username,password,role,name,email);
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public User validate(String user,String pass)
    {
        try
        {
            this.db.Where("username=", user);
            this.db.Where("password=", MD5.getMD5(pass));
            ResultSet Data = this.db.Select(table);
            if (Data.first())
            {
                String username = Data.getString("username");
                String password = Data.getString("password");
                String role = Data.getString("role");
                String name = Data.getString("name");
                String email = Data.getString("email");
                return new User(username,password,role,name,email);
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public void addUser(User user)
    {
        String col[] = {"username","password","role","name","email"};
        String val[] = new String[5];
        val[0] = user.getUsername();
        val[1] = user.getPassword();
        val[2] = user.getRole();
        val[3] = user.getName();
        val[4] = user.getEmail();
        
        int query = this.db.Insert(table, col, val);
    }
    
    public void updateUser(String username,User user)
    {
        this.db.Where("username=", username);
        String col[] = {"username","password","role","name","email"};
        String val[] = new String[5];
        val[0] = user.getUsername();
        val[1] = user.getPassword();
        val[2] = user.getRole();
        val[3] = user.getName();
        val[4] = user.getEmail();
        
        this.db.Update(table, col, val);
    }
    
    public List<User> getAllUser()
    {
        try 
        {
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<User> ListUser = new LinkedList();
            while (isExist) {
                String username = Data.getString("username");
                String password = Data.getString("password");
                String role = Data.getString("role");
                String name = Data.getString("name");
                String email = Data.getString("email");
                User user =  new User(username,password,role,name,email);
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
    
    public void delUser(String username)
    {
        this.db.Where("username=", username);
        this.db.Delete(table);
    }
    
    public static void main(String[] args) {
	UserData user_data = new UserData();
        User user = new User("admin","admin","admin","admin","admin@simple-blog.com");
        user_data.addUser(user);
    }
}
