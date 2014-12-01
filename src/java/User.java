import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class User {
    
    // attribute
    private String username;
    private String password;
    private String nama;
    private String email;
    private String role;
    // default constructor
    public User() {
    }
    
    // getter
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getNama() {
        return nama;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
   
    // setter
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(String role) {
        this.role = role;
    }
}