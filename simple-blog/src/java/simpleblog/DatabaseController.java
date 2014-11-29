package simpleblog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import static org.apache.tomcat.jni.User.username;
import simpleblog.model.User;
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
    private User selectedUser;
    private List<User> lusers;
    /**
     * Creates a new instance of dbTest
     */
    public DatabaseController() {
        System.out.println("db ctrl dibuat");
        selectedUser = new User();
    }
    public User getSelectedUser() {
        if(selectedUser == null)
            selectedUser = new User();
         return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User loginValidator(String username, String password){
        setUser(new User());
        try{
            //get database connection
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");

            Connection conn = ds.getConnection();
            ps = conn.prepareStatement("SELECT * FROM user WHERE user.username = '" + username +  "' AND user.password = '" + password + "'"); 
            ResultSet result =  ps.executeQuery();
            result.next();
            getUser().setId(result.getInt("id"));
            getUser().setEmail(result.getString("email"));
            getUser().setUsername(result.getString("username"));
            getUser().setName(result.getString("name"));
            getUser().setPassword(result.getString("password"));
            getUser().setRole(result.getString("role"));

            conn.close();
            
            System.out.println("masuk database");
            return getUser();
        } catch (Exception e) {
            System.out.println("masuk exception database");
            getUser().setRole("guest");
            return getUser();
        }
    }
    public User getUser() {
        if(user == null)
            user = new User();
        return user;
    }
    public User intialUser(){
        user = new User();
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    public List<User> getUserList() throws SQLException{
                try {
			initCtx = new InitialContext();
                        envCtx = (Context) initCtx.lookup("java:comp/env");
                        ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
		} catch (NamingException e) {
		}
		if(ds==null)
			throw new SQLException("Can't get data source");
 
		//get database connection
		conn = ds.getConnection();
 
		if(conn==null)
			throw new SQLException("Can't get database connection");
 
		ps
			= conn.prepareStatement(
			   "SELECT id,username,name,email,password,role FROM user"); 
 
		//get customer data from database
		ResultSet result =  ps.executeQuery();
		List<User> list = new ArrayList<User>();
                
		while(result.next()){
			setUser(new User());
 
			getUser().setId(result.getInt("id"));
                        getUser().setEmail(result.getString("email"));
                        getUser().setName(result.getString("name"));
                        getUser().setUsername(result.getString("username"));
                        getUser().setPassword(result.getString("password"));
                        getUser().setRole(result.getString("role"));
			//store all data into a List
			list.add(getUser());
		}
 
		return list;
	}
    public void add() throws SQLException{
        try {
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
        } catch (NamingException e) {
        }
	if(ds==null)
            throw new SQLException("Can't get data source");
 
	//get database connection
	conn = ds.getConnection();
        if(conn==null)
            throw new SQLException("Can't get database connection");
        else{
        ps  = conn.prepareStatement("INSERT INTO user (username,name,email,password,role) VALUES('"+user.getUsername()+"','"+user.getName()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getRole()+"')"); 
        ps.executeUpdate();
        conn.close();
        ps.close();}
        System.out.println("Berhasil");
        user = null;
    }

    
    public void delete() throws SQLException{
        try {
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
        } catch (NamingException e) {
        }
	if(ds==null)
            throw new SQLException("Can't get data source");
 
	//get database connection
	conn = ds.getConnection();
        if(conn==null)
            throw new SQLException("Can't get database connection");
        else{
        ps  = conn.prepareStatement("DELETE FROM user WHERE id = "+selectedUser.getId()+""); 
        ps.executeUpdate();
        conn.close();
        ps.close();}
        System.out.println("Berhasil");
    }
    public void update() throws SQLException{
        try {
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
        } catch (NamingException e) {
        }
	if(ds==null)
            throw new SQLException("Can't get data source");
 
	//get database connection
	conn = ds.getConnection();
        if(conn==null)
            throw new SQLException("Can't get database connection");
        else{
        ps  = conn.prepareStatement("UPDATE user SET email='"+selectedUser.getEmail()+"',username='"+selectedUser.getUsername()+"',name='"+selectedUser.getName()+"',password='"+selectedUser.getPassword()+"',role='"+selectedUser.getRole()+"' WHERE id = '"+selectedUser.getId()+"'"); 
        ps.executeUpdate();
        System.out.println(selectedUser.getName());
        conn.close();
        ps.close();}
        System.out.println("Berhasil");
    }
    public void settingSelectedUser(){
        try {
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");
 
            //get database connection
            conn = ds.getConnection();
            
            System.out.println("SELECT * FROM user WHERE user.id ='"+selectedUser.getId()+"'");

            ps  = conn.prepareStatement("SELECT * FROM user WHERE user.id ='"+selectedUser.getId()+"'"); 
            ResultSet result =  ps.executeQuery();
            result.next();
            selectedUser.setEmail(result.getString("email"));
            selectedUser.setName(result.getString("name"));
            selectedUser.setUsername(result.getString("username"));
            selectedUser.setPassword(result.getString("password"));
            selectedUser.setRole(result.getString("role"));
            conn.close();
            ps.close();
            System.out.println("Berhasil");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void nullUser(){
        user = new User();
        user.setRole(null);
    }
    
    public void redirectTable() throws Exception{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("faces/ShowTable.xhtml");
    }
}
