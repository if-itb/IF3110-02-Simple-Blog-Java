package lingga;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jelink
 */
@ManagedBean(name="cookiehandler",eager=true)
@SessionScoped
public class CookieHandler {
    public void setCookie(String name, String value, int expire) {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
	Cookie cookie = null;
	Cookie[] userCookies = request.getCookies();
	if(userCookies != null && userCookies.length > 0 ){
	    for (int i = 0; i < userCookies.length; i++){
		if (userCookies[i].getName().equals(name)){
		    cookie = userCookies[i];
		    break;
		}
	    }
	}

	if(cookie != null){
	    cookie.setValue(value);
	}
	else{
	    cookie = new Cookie(name, value);
	    cookie.setPath(request.getContextPath());
	}

	cookie.setMaxAge(expire);
	cookie.setPath("/");

	HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	response.addCookie(cookie);
    }

    public Cookie getCookie(String name) {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
	Cookie cookie = null;
	Cookie[] userCookies = request.getCookies();
	if(userCookies != null && userCookies.length > 0){
	    for(int i = 0; i < userCookies.length; i++){
		if(userCookies[i].getName().equals(name)){
		    cookie = userCookies[i];
		    return cookie;
		}
	    }
	}
	return null;
    }
    
    public void deleteCookie(String name){
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
	HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	Cookie[] cookies = request.getCookies();
	if (cookies != null) { // Yes, this can return null! The for loop would otherwise throw NPE.
	    for (Cookie cookie : cookies) {
		if (cookie.getName().equals(name)) {
		    System.out.println("ada sb uid di " + cookie.getPath());
		    cookie.setMaxAge(0);
		    cookie.setPath("/");
		    response.addCookie(cookie);
		    break;
		}
	    }
	}
    }
    
}