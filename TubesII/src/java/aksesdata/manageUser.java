/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aksesdata;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class manageUser implements Serializable{

    
    @Resource(name="jdbc/test")
    private DataSource ds;
    private List<User> userList;
   
    private String username;
    private String password;
    private String email;
    private int role;
    private boolean editable;
    
    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public manageUser() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/test");
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> getUsers() throws SQLException{
        
         if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("select * from user");
        ResultSet result = ps.executeQuery();
        List<User> user = new ArrayList<User>();
        
        while(result.next()) {
           User login = new User();
            
            login.setUsername(result.getString("username"));
            login.setPassword(result.getString("password"));
            login.setEmail(result.getString("email"));
            login.setRole(result.getInt("role"));
            login.setEditable(result.getBoolean("editable"));
            login.setId(result.getInt("id"));
            user.add(login);
        }
        setUserList(user);
        con.close();
        return user;
    }
    
    public List<User> getUser(String u) throws SQLException{
        
         if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("select * from user where username='"+u+"'");
        ResultSet result = ps.executeQuery();
        List<User> user = new ArrayList<User>();
        
        while(result.next()) {
           User login = new User();
            
            login.setUsername(result.getString("username"));
            login.setPassword(result.getString("password"));
            login.setEmail(result.getString("email"));
            login.setRole(result.getInt("role"));
            login.setEditable(result.getBoolean("editable"));
            login.setId(result.getInt("id"));
            user.add(login);
        }
        setUserList(user);
        con.close();
        return user;
    }
    
    public void deleteUser(String username) throws SQLException{
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("delete from user where username='"+username+"'");
        ps.executeUpdate();
    }
    
    public void addUser() throws SQLException{
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("insert into user(username, password, email, role, editable) values ('"+username+"', '"+password+"', '"+email+"', '"+role+"', 0)");
        ps.executeUpdate();
    }
    
     public void editAction(String username) throws Exception{
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("update user set editable=1 where username='"+username+"'");
        ps.executeUpdate();
    }
     
     public void setAction(int id) throws Exception {
         if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("update user set username='"+username+"', password='"+password+"', email='"+email+"', role="+role+", editable=0 where id="+id);
        ps.executeUpdate();
     }
    
}