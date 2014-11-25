/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class login_bean {
    private String username;
    private String password;
    private String role;
    private String dbusername;
    private String dbpassword;
    private String dbrole;
    private boolean remember;
    String rememberstr;
    
    public login_bean(){
        checkCookie();
    }
    
    //Getter
    public String getDBpassword(){
        return dbpassword;
    }
    
    public String getDBusername(){
        return dbpassword;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public boolean getRemember(){
        return remember;
    }
    public String getRole(){
        return role;
    }
    
    //Setter
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setRemember(boolean remember){
        this.remember = remember;
    }
    
    //Connect to mysql and get username-password 
    public void dbData(String uname) throws SQLException, ClassNotFoundException{
        Connection con;
        Statement ps;
        ResultSet rs;
        String SQL_Str;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "Select * from users where username='" +uname+ "'";
        rs = ps.executeQuery(SQL_Str);
        rs.next();
        dbusername = rs.getString(2);
        dbpassword = rs.getString(3);
        dbrole = rs.getString(4);
        con.close();
    }
    
    public String checkValidUser() throws SQLException, ClassNotFoundException{
        dbData(username);
        if(username.equalsIgnoreCase(dbusername)){
            if(password.equals(dbpassword)){
                role = dbrole;
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
                
                //Save the username and password in a cookie
                Cookie usercookie = new Cookie("usercookie", username);
                Cookie passcookie = new Cookie("passcookie", password);
                
                //Check if checkbox remember me is checked
                if(remember == false){
                    rememberstr = "false";
                } else {
                    rememberstr = "true";
                }
                
                //Save the remember i a cookie
                Cookie remembercookie = new Cookie("rememberccookie", rememberstr);
                
                //Set cookie's age to 1 day = 86400 s
                usercookie.setMaxAge(86400);
                passcookie.setMaxAge(86400);
                
                //Add the cookies to response
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(usercookie);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(passcookie);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(remembercookie);
                
                return "valid";
            } else {
                return "invalid";
            }
        } else {
            return "invalid";
        }
    }
    
    public void checkCookie(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookiename;
        Cookie cookies[] = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getCookies();
        if ((null != cookies) && (cookies.length > 0)){
            for (int i=0; i<cookies.length; i++){
                cookiename = cookies[i].getName();
                if (cookiename.equals("usercookie")){
                    username = cookies[i].getValue();
                } else if(cookiename.equals("passcookie")){
                    password = cookies[i].getValue();
                } else if(cookiename.equals("remembercookie")){
                    rememberstr = cookies[i].getValue();
                    if (rememberstr.equals("true")){
                        remember = true;
                    } else if (rememberstr.equals("false")){
                        remember = false;
                    }
                }
            }
        } else {
            System.out.println("Cannot find any cookie");
        }
    }
}
