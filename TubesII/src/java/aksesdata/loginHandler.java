/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aksesdata;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class loginHandler {

    @Resource(name="jdbc/test")
    private DataSource ds;
    private boolean auth;
    private List<User> user;
    private String username;
    private String password;
    private Cookie cookie;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
    
    public loginHandler() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/test");
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
    
    public void getUserList(String usname) throws SQLException {
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("select * from user where username='"+usname+"'");
        ResultSet result = ps.executeQuery();
        user = new ArrayList<User>();
        
        while(result.next()) {
          User users = new User();
            
            users.setUsername(result.getString("username"));
            users.setPassword(result.getString("password"));
            users.setRole(result.getInt("role"));
            user.add(users);
        }
        con.close();
    }
    
    public void login() throws Exception{
        getUserList(username);
        if (username.equals(user.get(0).getUsername()) && password.equals(user.get(0).getPassword())) {
            setCookies(user.get(0).getRole());
        }
    }
    
    public void logout() throws Exception{
        HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cook = new Cookie("name", "");
        cook.setMaxAge(0);
        cook.setValue(null);
        httpServletResponse.addCookie(cook);
        httpServletResponse.sendRedirect("guest.xhtml");
    }
    
    public void setCookies(int role) throws Exception{
        HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cook = new Cookie("name", username);
        cook.setMaxAge(3600);
        cook.setComment("Login");
        httpServletResponse.addCookie(cook);
        setCookie(cook);
       
        httpServletResponse.sendRedirect("daftar_post.xhtml");
        
        
    }
    
    public String getCookies() {
        HttpServletRequest httpServletRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] cookies = httpServletRequest.getCookies();
        String cookieValue ="";
        
        if(cookies != null) {
            for(int i=0; i<cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase("name")) {
                    cookieValue = cookies[i].getValue();
                }
            }
        }
        return cookieValue;
    }
    
    public boolean isLoggedIn() {
        if(getCookies()==null|| getCookies().compareTo("")==0) {
            auth = true;
        }
        else {
            auth = false;
        }
        
        return auth;
    }
    
    public boolean isAdmin() throws Exception{
        if(user.get(0).getRole()==3) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isEditor() throws Exception{
        if(user.get(0).getRole()==2) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isOwner() throws Exception{
        if(user.get(0).getRole()==1) {
            return true;
        }
        else {
            return false;
        }
    }

}

