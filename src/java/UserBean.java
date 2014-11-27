/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fahziar
 */
@ManagedBean(eager=true)
@RequestScoped
public class UserBean {

    
    @ManagedProperty(value="#{username}")
    private String username;
    @ManagedProperty(value="#{password}")
    private String password;
    @ManagedProperty(value="#{role}")
    private String role;
    @ManagedProperty(value="#{email}")
    private String email;
    private boolean lastLoginFailed;
    private boolean justLoggedIn;
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
   
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setRole(String role)
    {
        this.role = role;
    }
    
    public String getRole()
    {
        return role;
    }
    
    public UserBean()
    {
        System.out.println("UserBean");
        username = "";
        password = "";
        email = "";
        role = "guest";
        lastLoginFailed = false;
        justLoggedIn = false;
        
        Cookie cookie = loadCookie();
        
        if (cookie != null)
        {
            
            User user = new User();
            if (user.loginFromCookie(cookie.getValue()))
            {
                username = user.getUsername();
                password = user.getPassword();
                email = user.getEmail();
                this.role = user.getRole();
                System.out.println(role);
                
                
            } else {
                cookie.setMaxAge(0);
                ((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(cookie);
                
            }
        }
    }
    
    private Cookie loadCookie()
    {
        System.out.println("Load cookie");
        Cookie out = null;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        Cookie[] cookies = request.getCookies();
        
        int i;
        for (i=0; i< cookies.length; i++)
        {
            if (cookies[i].getName().equalsIgnoreCase("username"))
            {
                out = cookies[i];
            }
        }
        
        return out;
    }
    
    private Cookie loadRoleCookie()
    {
        System.out.println("Load cookie");
        Cookie out = null;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        Cookie[] cookies = request.getCookies();
        
        int i;
        for (i=0; i< cookies.length; i++)
        {
            if (cookies[i].getName().equalsIgnoreCase("role"))
            {
                out = cookies[i];
            }
        }
        
        return out;
    }
    
    
    public void login()
    {
        System.out.println("Login");
        User user = new User();
        System.out.println("Username:" + username);
        if (user.login(username, password))
        {
            role = user.getRole();
            email = user.getEmail();
            Cookie cookie = new Cookie("username", username);
            Cookie roleCookie = new Cookie("role", role);
            cookie.setMaxAge(36000);
            roleCookie.setMaxAge(36000);
            ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).setHeader("Location", "index.xhtml");
            ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(cookie);
            ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(roleCookie);
            try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(getAbsoluteContextPath());
            } catch (Exception e)
            {
                
            }
        } else {
            username = "";
            password = "";
            role = "guest";
            lastLoginFailed=true;
        }
    }
    
    public void logout()
    {
        System.out.println("Logout");
        Cookie cookie = loadCookie();
        
        cookie.setMaxAge(0);
        cookie.setValue("");
        ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(cookie);
    }
    
    public boolean isLoggedIn()
    {
        System.out.println("isLoggedIn");
        Cookie cookie = loadCookie();
        System.out.println(role);
        if (cookie != null)
        {
            User user = new User();
            user.loginFromCookie(cookie.getValue());
            if (user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("owner") || user.getRole().equalsIgnoreCase("editor"))
            {
                return true;
            } else{
                return justLoggedIn;
            }
        } else {
            return false;
        }
    }
    
    public boolean isLastLoginFailed()
    {
        return lastLoginFailed;
    }
    private String getAbsoluteContextPath(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getScheme());  //http,https,...
        buffer.append("://");
        buffer.append(request.getServerName()); //localhost
        if (request.getServerPort() != 80){
            buffer.append(":"); //localhost
            buffer.append(request.getServerPort()); //8080
        }
        if (request.getContextPath() !=""){
            buffer.append("/");
            buffer.append(request.getContextPath());
        }
        else 
            buffer.append("/");
        return buffer.toString();
    }

}
