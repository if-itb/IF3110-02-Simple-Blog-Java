package simpleblog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import simpleblog.model.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import simpleblog.model.Post;

/**
 *
 * @author Luqman
 */
@ManagedBean(name="databaseController")
@SessionScoped
public class DatabaseController implements Serializable {
    
    private User user;
    private DataSource ds;
    private Context initCtx;
    private Context envCtx;
    private Connection conn;
    private PreparedStatement ps;
    
    /**
     * Creates a new instance of dbTest
     */
    public DatabaseController() {
        System.out.println("db ctrl dibuat");
    }
    
    public User loginValidator(String username, String password){
        user = new User();
        try{
            //get database connection
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");

            Connection conn = ds.getConnection();
            ps = conn.prepareStatement("SELECT * FROM user WHERE user.username = '" + username +  "' AND user.password = '" + password + "'"); 
            ResultSet result =  ps.executeQuery();
            result.next();
            user.setId(result.getInt("id"));
            user.setEmail(result.getString("email"));
            user.setUsername(result.getString("username"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setRole(result.getString("role"));

            conn.close();
            
            System.out.println("masuk database");
            return user;
        } catch (Exception e) {
            System.out.println("masuk exception database");
            user.setRole("guest");
            return user;
        }
    }
}
