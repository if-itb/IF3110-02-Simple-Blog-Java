import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class EditUser {
    
    // attribute
    private String username;
    private String password;
    private String nama;
    private String email;
    private String role;
    
    // default constructor
    public EditUser() {
    }
    
    // function
    public void run(String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from userdata where username='" + user + "'");
            while(rs.next() == true) {
                username = user;
                password = rs.getString(2);
                nama = rs.getString(3);
                email = rs.getString(4);
                role = rs.getString(5);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void edit() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE userdata SET password=?, nama=?, email=?, role=? where username=?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, nama);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, username);
            System.out.println(username+password+nama+email+role);
            preparedStatement.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
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