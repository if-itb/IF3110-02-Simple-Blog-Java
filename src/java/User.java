/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fahziar
 */

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String username;
    private String password;
    private String role;
    private String email;
    
    public User()
    {
        role="guest";
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getRole()
    {
        return role;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setRole(String role)
    {
        this.role = role;
        
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public boolean loginFromCookie(String username)
    {
        boolean out = false;
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            
            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);
            
            preparedStatement = connect.prepareStatement("SELECT * FROM users where username=?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.last())
            {
                this.username = username;
                password = resultSet.getString("password");
                email = resultSet.getString("email");
                role = resultSet.getString("role");
                out = true;
                System.out.println("Login dari cookie berhasil");
            }
        } catch (SQLException e)
        {
            System.out.println("Failed to fetch users" + e.getSQLState());
        } catch (Exception e)
        {
            System.out.println("Failed to fetch users");
        } finally {
           
        }
        return out;
    }
    
    public boolean login(String username, String password)
    {
        boolean out = false;
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Hello");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);

            preparedStatement = connect.prepareStatement("SELECT * FROM users where `username`=? AND `password`=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.last())
            {
                this.username = username;
                this.password = resultSet.getString("password");
                email = resultSet.getString("email");
                role = resultSet.getString("role");
                out = true;
                System.out.println("Login berhasil");
            }
            
        } catch (SQLException e)
        {
            System.out.println("Failed to fetch users" + e.getMessage());
            
        }catch (Exception e)
        {
            System.out.println("Failed to fetch users");
        } finally {
            Close(resultSet, preparedStatement, connect);            
        }
        return out;
    }
    
    
    public boolean updateUser()
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
            }
                       
        } catch (Exception e)
        {
            System.out.println("Failed to fetch users");
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
    
