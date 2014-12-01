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
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@RequestScoped
public class Login {
    
    // attribute
    private ArrayList<User> user;
    private int expiry = 60*60*24;
    private String role;
    private String email;
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
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("select role, email from userdata where username='"+username+"'");
                rs.next();
                role = rs.getString(1);
                email = rs.getString(2);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
            Cookie usernameCookie = new Cookie("username", username);
            Cookie roleCookie = new Cookie("role", role);
            Cookie emailCookie = new Cookie("email", email);
            usernameCookie.setMaxAge(expiry);
            roleCookie.setMaxAge(expiry);
            emailCookie.setMaxAge(expiry);
            response.addCookie(usernameCookie);
            response.addCookie(roleCookie);
            response.addCookie(emailCookie);
            
            System.out.println("inirole"+role);
            if(role.equals("admin")){
                response.sendRedirect("index.xhtml");
            }
            else if(role.equals("editor")){
                response.sendRedirect("publish_editor.xhtml");
            }
            else if(role.equals("owner")){
                response.sendRedirect("index_owner.xhtml");
            }
            else{
                response.sendRedirect("index_guest.xhtml");
            }            
        }
        else{
            response.sendRedirect("login_guest.xhtml");
        }
    }
    
    public void logout() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Cookie usernameCookie = new Cookie("username", "");
        Cookie roleCookie = new Cookie("role", "");
        Cookie emailCookie = new Cookie("email", "");
        usernameCookie.setMaxAge(0);
        roleCookie.setMaxAge(0);
        emailCookie.setMaxAge(0);
        response.addCookie(usernameCookie);
        response.addCookie(roleCookie);
        response.addCookie(emailCookie);
        response.sendRedirect("index_guest.xhtml");
    }
}