package Database;

import Model.Post;
import Model.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Managed Bean for JSF
 * @author Riva Syafri Rachmatullah
 */
@ManagedBean(name = "beans", eager = true)
@SessionScoped
public class DataManagement implements Serializable {
    private Cookie cookie;
    private String message;
    private User user;
    private Post post;
    private PostData postdata;

    public DataManagement() {
        user = new User();
        post = new Post();
        message = "";
        cookie = new Cookie("Username", null);
        cookie.setMaxAge(120);
    }
    
    public Cookie getCookie() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cookies;
        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (Cookie userCookie : userCookies) {
                if (userCookie.getName().equals("Username")) {
                    cookies = userCookie;
                    return cookies;
                }
            }
        }
        return null;
    }
    
    public String getMessage() {
        return message;
    }
    
    public PostData getPostData(){
        return postdata;
    }
    
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User newUser) {
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setRole(newUser.getRole());
    }
    
    public void setMessage(String s) {
        message = s;
    }
    
    /**
     * Log in and validate into web
     */
    public void login() {
        UserData ud = new UserData();
        User Validator = ud.getUser(user.getUsername());
        if (Validator != null) {
            if (user.getPassword().compareTo(Validator.getPassword()) == 0) {
                user.setName(Validator.getName());
                user.setRole(Validator.getRole());
                user.setEmail(Validator.getEmail());
                cookie.setValue(user.getUsername());
                FacesContext facesContex = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) facesContex.getExternalContext().getResponse();
                response.addCookie(cookie);
            } else {
                message = "Wrong Password";
            }
        } else {
            message = "Username not found";
        }
    }

    /**
     * Log out from web
     * @return a page of web
     */
    public String logout() {
        reset();
        message = "";
        cookie.setValue(null);
        cookie.setMaxAge(0);
        FacesContext facesContex = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContex.getExternalContext().getResponse();
        response.addCookie(cookie);
        return "index.xhmtl";
    }

    public void reset() {
        user = new User();
    }

    public boolean isLogin() {
        return user.getRole().compareTo("guest") != 0 && cookie.getValue() != null;
    }

    public boolean isAdmin() {
        return user.getRole().compareTo("admin") == 0 && cookie.getValue() != null;
    }
    
    public boolean isOwner() {
        return user.getRole().compareTo("owner") == 0 && cookie.getValue() != null;
    }
    
    public boolean isEditor() {
        return user.getRole().compareTo("editor") == 0 && cookie.getValue() != null;
    }
    
    public boolean activeMessage() {
        return !message.equals("") && !isLogin();
    }
}
