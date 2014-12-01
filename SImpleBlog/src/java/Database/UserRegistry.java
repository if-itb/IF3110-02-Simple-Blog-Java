/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sakurai
 */
@ManagedBean(name = "UserRegistry", eager = true)
@ViewScoped
public class UserRegistry {
    
    public UserRegistry(){

    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException{
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/blog";
            String user = "root";
            String password = "";
            conn =  DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | InstantiationException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    public boolean isUserExist(String username) throws ClassNotFoundException, IllegalAccessException{
        boolean userExist = false;
        ResultSet result;
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "Select * From user where Username = \"" + username + "\";";
            result = stmt.executeQuery(query);
            if(!result.next()){
                userExist = false;
            }else{
                userExist = true;
            }
            conn.close();
            
        } catch(SQLException e){
            System.err.println(e);
        }
        return userExist;
    }
    
    public void addUserOwner() throws ClassNotFoundException, SQLException, IllegalAccessException, IOException{
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Username = request.getParameter("Form:Username");
        String Password = request.getParameter("Form:Password");
        String Email = request.getParameter("Form:Email");
        String Name = request.getParameter("Form:Name");
        out.println("password: " + Password);
        if(!isUserExist(Username)){
            try (Connection con = getConnection()) {
                PreparedStatement ps;
                String query = "INSERT INTO `user` (`Username`,`Password`, `Name`, `email`, `Role`) VALUES (?,?,?,?,?)";
                ps= con.prepareStatement(query);
                ps.setString(1,Username);
                ps.setString(2,Password);
                ps.setString(3,Name);
                ps.setString(4,Email);
                ps.setString(5,"Owner");
                int i = ps.executeUpdate();
                con.close();
            }
        }
        ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
        extcon.redirect("/SImpleBlog/Home.xhtml");
    }
}
