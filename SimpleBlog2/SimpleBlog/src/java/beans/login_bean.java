/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class login_bean {
    private String username;
    private String password;
    private String role;
    private String email;
    private String dbusername;
    private String dbpassword;
    private String dbemail;
    private String dbrole;
    private boolean remember;
    String rememberstr;
    Connection con;
    Statement ps;
    ResultSet rs;
    String SQL_Str;
    
    public login_bean(){
        checkCookie();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null){
            session.invalidate();
        }
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
     public String getEmail(){
        return email;
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
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setRemember(boolean remember){
        this.remember = remember;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    //Connect to mysql and get username-password 
    public void dbData(String uname) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "Select * from users where username='" +uname+ "'";
        rs = ps.executeQuery(SQL_Str);
        rs.next();
        dbusername = rs.getString(2);
        dbpassword = rs.getString(3);
        dbemail = rs.getString(4);
        dbrole = rs.getString(5);
        con.close();
    }
    
    public String checkValidUser() throws SQLException, ClassNotFoundException{
        dbData(username);
        if(username.equalsIgnoreCase(dbusername)){
            if(password.equals(dbpassword)){
                role = dbrole;
                email = dbemail;
                FacesContext facesContext = FacesContext.getCurrentInstance();
                
                //Save the username and password in a cookie
                Cookie usercookie = new Cookie("usercookie", username);
                Cookie passcookie = new Cookie("passcookie", password);
                Cookie rolecookie = new Cookie("rolecookie", role);
                Cookie emailcookie = new Cookie("emailcookie", role);
                
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
                rolecookie.setMaxAge(86400);
                emailcookie.setMaxAge(86400);
                
                //Add the cookies to response
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(usercookie);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(passcookie);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(rolecookie);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(emailcookie);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(remembercookie);
                
                return "valid";
            } else {
                return "invalid";
            }
        } else {
            return "invalid";
        }
    }
    
    public String setGuestRole() {
        role = "guest";
        return "guest";
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
                } else if(cookiename.equals("rolecookie")){
                    password = cookies[i].getValue();
                } else if(cookiename.equals("emailcookie")){
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
    
    public String login() throws SQLException, ClassNotFoundException{
       
            String message ="";
            String navto="";
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            try {
                request.login(username, password);
                Principal principal = request.getUserPrincipal();

                if (request.isUserInRole("Administrator")){
                    message = "Username : " + principal.getName() + " You are an administrator ";
                    navto = "admin";
                } else if (request.isUserInRole("Manager")){
                    message = "Username : " + principal.getName() + " You are a manager";
                    navto = "manager";
                } else if (request.isUserInRole("Guest")){
                    message = "Username : " + principal.getName() + " You are a guest";
                    navto = "guest";
                }
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
                return navto;
            } catch (ServletException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login gagal bro", null));
                ex.printStackTrace();
            }
            return "failure";
      
    }
    
    public void logout(){}
}
