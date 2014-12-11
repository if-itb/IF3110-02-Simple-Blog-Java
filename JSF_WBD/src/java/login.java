
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import static com.sun.faces.facelets.util.Path.context;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mochamad Lutfi F
 */
@ManagedBean(name = "login",eager=true)
@ApplicationScoped
public class login 
{ 
    private String username; 
    private String password; 
    private String role;
    private boolean isUsernameValid; 
    private boolean isPasswordValid; 
    private boolean validationComplete = false; 
    private String dbuser;
    private String dbpassword;
    private String dbrole;
    private String SQLuser;
    private static final String COOKIE_NAME = "Cookie";
    private static final int COOKIE_MAX_AGE = 24*60*7;
    
    private User currentUser = new User();

    Connection con = null;
    Statement stmt = null;
    ResultSet rs;
    
    /** * @return the username */
    public String getUsername() 
        { return username; } 
    /** * @param username * the username to set */ 
    public void setUsername(String username) 
        { this.username = username; } 
    /** * @return the password */ 
    public String getPassword() 
        { return password; } 
    /** * @param password * the password to set */ 
    public void setPassword(String password) 
        { this.password = password; } 
    /** * @return the isUsernameValid */ 
    public String getRole(){
        return role;
    }
    public String getDBRole(){
        return dbrole;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    
    public boolean isAdmin(){
        return dbrole.equals("admin");
    }
    
    public boolean isEditor(){
        return dbrole.equals("editor");
    }
    
    public boolean isOwner(){
        return dbrole.equals("owner");
    }
    
    public boolean isCookieSet(Cookie cookie){
        if(cookie.getName() == COOKIE_NAME)
            return true;
        else
            return false;
    }
    
      public void setCookie() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Cookie cookie = null;
        
        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (Cookie c : userCookies) {
                if (c.getName().equals(COOKIE_NAME)) {
                    cookie = c;
                    break;
                }
            }
        }
        
        // update cookie if it already exist
        if (cookie != null) {
            cookie.setValue(currentUser.getUsername());
        }
        else {
            cookie = new Cookie(COOKIE_NAME, currentUser.getUsername());
            cookie.setPath(request.getContextPath());
        }
        cookie.setMaxAge(COOKIE_MAX_AGE);
        
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.addCookie(cookie);
    }
    
    public Cookie getCookie() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Cookie cookie = null;
        
        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (Cookie c : userCookies) {
                if (c.getName().equals(COOKIE_NAME)) {
                    return c;
                }
            }
        }
        
        return null;
    }
    
    
    
    public boolean getIsUsernameValid() 
        { return isUsernameValid; } 
    /** * @param isUsernameValid the isUsernameValid to set */ 
    public void setUsernameValid(boolean isUsernameValid) 
        { this.isUsernameValid = isUsernameValid; } 
    /** * @return the isPasswordValid */ 
    public boolean getIsPasswordValid() 
        { return isPasswordValid; } 
    /** * @param isPasswordValid the isPasswordValid to set */ 
    public void setPasswordValid(boolean isPasswordValid) 
        { this.isPasswordValid = isPasswordValid; } 
    /** * @return the validationComplete */ 
    public boolean isValidationComplete() 
        { if(validationComplete==true){
            return true;
            }
          else
            return false;
        } 
    /** * @param validationComplete the validationComplete to set */ 
    public void setValidationComplete(boolean validationComplete) 
        { this.validationComplete = validationComplete; } 
    
     private void SetupDB() throws ClassNotFoundException, SQLException{
        String host = "jdbc:mysql://localhost:3306/simple_blog_java?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = (Connection) DriverManager.getConnection(host, user, pwd);
        stmt = (Statement) con.createStatement();        
    }
    
    public void dbData(String user)
    {
        try
        {
            SetupDB();
            SQLuser="Select * from tb_user where username like ('" + user +"')";
            rs = stmt.executeQuery(SQLuser);
            rs.next();
            dbuser = rs.getString(2).toString();
            dbpassword = rs.getString(3).toString();
            dbrole = rs.getString(5).toString();
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            
        }
    }
    
    public String checkValidity() throws IOException, ClassNotFoundException, SQLException
    { 
        dbData(username);
        if(username.equalsIgnoreCase(dbuser))
        {  
            if(password.equals(dbpassword))
            {
                validationComplete = true;
                setCookie();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.sendRedirect("home.xhtml");
                return "success";    
            }
            else
            {
              //  validationComplete = true;
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage("Username atau Password salah!"));
                return "";
            }
        }
        else
        {
            //validationComplete = true;
            FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage("Username atau Password salah!"));
            return "";}
    } 
    
    public void logout() throws IOException {
        dbrole = "null";
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/loginuser.xhtml");
    }

}
