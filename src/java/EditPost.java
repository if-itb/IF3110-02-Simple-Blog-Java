import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class EditPost implements Serializable {

    // attribute
    private int id;
    private String judul;
    private String tanggal;
    private String konten;
    private String status;
    
    // default constructor
    public EditPost() {
    }
    
    // function
    public void run(int i) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from postdata where id_post=" + i);
            while(rs.next() == true) {
                id = i;
                judul = rs.getString(2);
                tanggal = rs.getString(3);
                konten = rs.getString(4);
                status = rs.getString(5);
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
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE postdata SET judul=?, tanggal=?, konten=? where id_post=?");
            preparedStatement.setString(1, judul);
            preparedStatement.setString(2, tanggal);
            preparedStatement.setString(3, konten);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // getter
    public int getId() {
        return id;
    }
    public String getJudul() {
        return judul;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getKonten() {
        return konten;
    }
    public String getStatus() {
        return status;
    }
    
    // setter
    public void setId(int id) {
        this.id = id;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public void setKonten(String konten) {
        this.konten = konten;
    }
    public void setStatus(String status) {
        this.status = status;
    }  
}