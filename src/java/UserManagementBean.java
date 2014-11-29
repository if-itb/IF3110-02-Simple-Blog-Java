/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fahziar
 */
@ManagedBean
@SessionScoped
public class UserManagementBean {

    /**
     * Creates a new instance of UserManagementBean
     */
    public UserManagementBean() {
        userExist = false;
    }
    
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean userExist;
    
    public int getId()
    {
        return id;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getRole()
    {
        return role;
    }
    
    public boolean isUserExist()
    {
        return userExist;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void setRole(String role)
    {
        this.role = role;
    }
    
    public boolean update()
    {
        boolean out = false;
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);

            preparedStatement = connect.prepareStatement("UPDATE `users` SET `password`=?, `role`=? WHERE `username`=?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, role);
            preparedStatement.setString(3, username);
            
            if (preparedStatement.executeUpdate() > 0)
            {
                out = true;
                System.out.println("Update pengguna berhasil");
            }            
        } catch (Exception e)
        {
            System.out.println("Failed to fetch users");
        } finally {
            Close(resultSet, preparedStatement, connect);
        }
        
        return out;
    }

    
    public void delete()
    {
        System.out.println("Menghapus pengguna: " + username);
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);

            preparedStatement = connect.prepareStatement("DELETE from `users` WHERE `username`=?");
            preparedStatement.setString(1, this.username);
            
            if (preparedStatement.executeUpdate() > 0)
            {
                username = "";
                password = "";
                role = "guest";
                email = "";
                System.out.println("Hapus pengguna berhasil");
                FacesContext.getCurrentInstance().getExternalContext().redirect(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI());
            }
                       
        } catch (Exception e)
        {
            System.out.println("Failed to fetch users");
        } finally {
           Close(resultSet, preparedStatement, connect);
        }
                
    }
    
    public void create()
    {
        System.out.println("create()");
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);
            
            preparedStatement = connect.prepareStatement("SELECT * FROM `users` WHERE `username`=?");
            preparedStatement.setString(1, username);
            
            if (!preparedStatement.executeQuery().last())
            {
                preparedStatement = connect.prepareStatement("INSERT INTO `users` (`username`, `password`,`email`, `role`) VALUES (?, ?, ?, ?)");
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.password);
                preparedStatement.setString(3, this.email);
                preparedStatement.setString(4, this.role);

                if (preparedStatement.executeUpdate() > 0)
                {
                    username = "";
                    password = "";
                    role = "guest";
                    email = "";
                    userExist = false;
                    System.out.println("Menambah pengguna berhasil");
                }
            } else {
                userExist = true;
            }
                       
        } catch (Exception e)
        {
            System.out.println("Failed to fetch users");
        } finally {
           Close(resultSet, preparedStatement, connect);
        }
    }
    
    public void processUpdateUser()
    {
        create();
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
