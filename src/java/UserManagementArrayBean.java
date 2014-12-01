/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Fahziar
 */
@ManagedBean
@RequestScoped
public class UserManagementArrayBean {

    /**
     * Creates a new instance of UserManagementArrayBean
     */
    
    private ArrayList<UserManagementBean> users;
    
    public ArrayList<UserManagementBean> getUsers()
    {
        return users;
    }
    
    public UserManagementArrayBean() {
        users = new ArrayList<>();
        fetchUsers();
    }
    
    public void fetchUsers()
    {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        users.clear();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);

            preparedStatement = connect.prepareStatement("SELECT * from `users`");
            resultSet = preparedStatement.executeQuery();
            
            System.out.println(resultSet.isAfterLast());
            while (resultSet.next())
            {
                UserManagementBean user = new UserManagementBean();
                
                int id = resultSet.getInt(1);
                System.out.println("hello");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                
                
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(role);
                users.add(user);
                System.out.println("Hello");
            }
            
                
        } catch (Exception e)
        {
            System.out.println("Class UserManagementArrayBean: Failed to fetch users");
        } finally {
           Close(resultSet, preparedStatement, connect);
        }
    }
    
    private void Close(ResultSet r, PreparedStatement p, Connection c)
    {
        if (r != null)
        {
            try{
                r.close();
            } catch (Exception e)
            {
            }
        }
        
        if (p != null)
        {
            try{
                p.close();
            } catch (Exception e)
            {
            }
        }
        if (c != null)
        {
            try{
                c.close();
            } catch (Exception e)
            {
                
            }
        }
    }
}
