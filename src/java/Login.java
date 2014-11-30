import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@RequestScoped
public class Login {
    
    // attribute
    private ArrayList<User> user;
    private String cookiename = "username";
    private int expiry = 60*60*24;
    // default constructor
    public Login() {
        user = new ArrayList<User>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from userdata");
            while(rs.next() == true) {
                User u = new User();
                u.setUsername(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setNama(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setRole(rs.getString(5));
                user.add(u);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // function
    public boolean check(String username, String password) {
        int i=0;
        boolean found = false;
        while(!found && i < user.size()) {
            if(user.get(i).getUsername().equals(username) && user.get(i).getPassword().equals(password)) {
                found = true;
            }
            else {
                i++;
            }
        }
        return found;
    }
    
    public void login(String username, String password) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        if(check(username, password)){
            Cookie usernameCookie = new Cookie(cookiename, username);
            usernameCookie.setMaxAge(expiry);
            response.addCookie(usernameCookie);
            response.sendRedirect("index.xhtml");
        }
        else{
            Cookie usernameCookie = new Cookie(cookiename, "guest");
            usernameCookie.setMaxAge(expiry);
            response.addCookie(usernameCookie);
            response.sendRedirect("login.xhtml");
        }
    }
    
    public void logout() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie cookie = getCookie();
        cookie.setValue(null);
        cookie.setMaxAge(0);
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.addCookie(cookie);
        response.sendRedirect("login.xhtml");
    }
    
    public Cookie getCookie(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cookie = null;
        
        Cookie[] userCookies = request.getCookies();
        if(userCookies != null && userCookies.length > 0){
            for (Cookie userCookie : userCookies) {
                if (userCookie.getName().equals(cookiename)) {
                    cookie = userCookie;
                    return cookie;
                }
            }
        }
        return null;
    }
}