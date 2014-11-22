package bean;

import java.io.IOException;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name ="user",eager = true)
@SessionScoped
public class User implements Serializable
{
    private String username;
    private String password;
    private String email;
    private String role;
    private List<User> daftar_user;
    
    public User() 
    {
        System.out.println("User Created");
        username="";
        password="";
        role="";
        email="";
        daftar_user = new ArrayList<>();
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

    public List<User> getDaftar_user() {
        fetchUsersFromDB();
        return daftar_user;
    }
    
    public void logout()
    {
        System.out.println("logout");
        username="";
        email="";
        role="";
        password="";
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try 
        {
            context.redirect(context.getRequestContextPath() + "/index.xhtml");
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void register()
    {
        System.out.println("register");
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "INSERT INTO user (username, password, email, role) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, role);
            int executeUpdate = ps.executeUpdate();
            if(executeUpdate > 0)
            {
                System.out.println("update succesful");
            }
            else
            {
                System.out.println("update failure");
            }
            con.close();
            System.out.println("connection closed");
        } 
        catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
        {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
    
    public void fetchUsersFromDB()
    {
        String url = "jdbc:mysql://localhost/simple_blog_java";
        String username = "root";
        String password = "";
        Connection con = null;
        daftar_user = new ArrayList<>();
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
                daftar_user.add(temp);
            }
            con.close();
        } 
        catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) 
        {
            System.out.println(ex.toString());
        }
    }
    
    public void login()
    {
        System.out.println("Login");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try 
        {
            context.redirect(context.getRequestContextPath() + "/" + role + ".xhtml");
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public String getAvail()
    {
        System.out.println("check Available");
        Boolean available = true;
        String result = "";
        fetchUsersFromDB();
        for (User daftar_user1 : daftar_user) {
            if (daftar_user1.getUsername().equals(username)) {
                result = "<div class=\"alert alert-danger login-alert\">username already exist</div>\n";
                available = false;
                break;
            }
        }
        if(available)
        {
            register();
            login();
        }
        return result;
    }
    public String getAuth()
    {
        System.out.println("auth");
        String output ="";
        Boolean match = false;
        fetchUsersFromDB();
        for(User user : daftar_user)
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                match = true;
                role = user.role;
                email = user.email;
                break;
            }
        }
        if(match)
        {
            login();
        }
        else
        {
            output = "<div class=\"alert alert-danger login-alert\">wrong username or password</div>\n";
        }
        return output;
    }
}