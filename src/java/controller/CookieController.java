package controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

public class CookieController {
    // constants for cookies
    private static final String COOKIE_NAME = "SimpleBlogSessionCookie";
    private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60;
    
    // set cookie according to current user
    public static void setCookie(User user) {
        FacesContext context = FacesContext.getCurrentInstance();
//        Cookie cookie = getCookie();
//        
//        // update cookie if it already exist
//        if (cookie != null) {
//            cookie.setValue(user.getUsername());
//        }
//        else {
//            cookie = new Cookie(COOKIE_NAME, user.getUsername());
//        }
//        
//        cookie.setPath("/");
//        cookie.setMaxAge(COOKIE_MAX_AGE);
//        
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.addCookie(encrypt(user.getUsername()));
    }
    
    public static Cookie getCookie() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
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
    
    public static void removeCookie() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.setContentType("text/html");
        response.addCookie(cookie);
    }
    
    public static Cookie encrypt(String username) {
        Cookie cookie = new Cookie(COOKIE_NAME, username);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_MAX_AGE);
        return cookie;
    }
    
    public static String decrypt(Cookie cookie) {
        return cookie.getValue();
    }
}
