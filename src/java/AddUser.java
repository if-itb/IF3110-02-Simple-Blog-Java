import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class AddUser {

    // default constructor
    public AddUser() {
    }
    
    // function
    public void add(String username, String password, String nama, String email, String role) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO userdata (username, password, nama, email, role) VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nama);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, role);
            preparedStatement.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}