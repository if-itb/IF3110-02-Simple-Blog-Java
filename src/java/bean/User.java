package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name ="user",eager = true)
@SessionScoped
public class User implements Serializable
{
    private String username;
    private String password;
    private String email;
    private String role;
    
    public User() 
    {
        username="";
        password="";
        role="";
        email="";
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<User> getUsersFromDB()
    {
        String url = "jdbc:mysql://localhost/simple_blog_java";
        String username = "root";
        String password = "";
        Connection con = null;
        List<User> records = new ArrayList<>();
        try 
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement query = con.prepareStatement("Select * from user");
            ResultSet rs = query.executeQuery();
            while(rs.next())
            {
                User temp = new User();
                temp.setUsername(rs.getString("username"));
                temp.setEmail(rs.getString("email"));
                temp.setPassword(rs.getString("password"));
                temp.setRole(rs.getString("role"));
                records.add(temp);
                System.out.println(temp.getUsername());
                System.out.println(temp.getPassword());
                System.out.println(temp.getPassword());
                System.out.println(temp.getRole());
            }
            con.close();
        } 
        catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) 
        {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
        return records;
    }
}
