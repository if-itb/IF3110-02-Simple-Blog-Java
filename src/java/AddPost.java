import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class AddPost {
    
    // default constructor
    public AddPost() {
    }
    
    // function
    public void add(String judul, String tanggal, String konten) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO postdata (judul, tanggal, konten, status) VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, judul);
            preparedStatement.setString(2, tanggal);
            preparedStatement.setString(3, konten);
            preparedStatement.setString(4, "unpublished");
            preparedStatement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}