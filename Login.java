
import static com.sun.faces.facelets.util.Path.context;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidiaanindhita
 */
@ManagedBean(name = "Login", eager = true)
public class Login {
    private String username;
    private String password;
    private String role;
    private Connection con;
    
    public Login() {
        username="lalala";
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setUsername(String user) {
        username=user;
    }
    
    public void setPassword(String pass) {
        password=pass;
    }
    
    public void accountHandler() throws SQLException, IOException, ClassNotFoundException {
        String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "asdasd123";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = DriverManager.getConnection(host, user, pwd);
        
        Statement stmt = null;
        String select;
        select = "SELECT * FROM akun WHERE (username ='windy' AND password='" + password + "')";
        //String insert = "INSERT INTO akun (username, password, role) VALUES ('" + username + "' , '" + password + "' , '')";
        stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery(select);
        
        while (rs.next()) {
            String userWeb = rs.getString("username");
            username = userWeb;
            String passUser = rs.getString("password");
            password = passUser;
            String roleUser = rs.getString("role");
            role = roleUser;
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        if (rs != null) {
            response.sendRedirect("page2.xhtml");
        } else {
            //response.sendRedirect("/SimpleBlog/faces/Login.xhtml");
        }
        //stmt.executeUpdate(select);
    }
    
    public void handler () throws SQLException {
        /*String user = username;
        String pass = "apalah";
        String role = "owner";
        
        Statement stmt = null;       
        String insert = "INSERT INTO akun (username, password, role) VALUES ('"+ user + "' , '" + pass + "' , '" + role + "')";
           stmt = con.createStatement();
    
        
            stmt.executeUpdate(insert);*/
        
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.addResponseCookie("MyTestCookie", "Hello Cookie", null);
            //HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        
    }
}
