import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ListUser {
    
    // attribute
    private ArrayList<User> user;
    
    public ListUser() {
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
    public void delete(String username) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM userdata WHERE username=?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // getter
    public ArrayList getUser() {
        return user;
    }
}