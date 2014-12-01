import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "viewPost", eager = true)
@ViewScoped
public class ViewPost implements Serializable {

    // attribute
    private int id;
    private String judul;
    private String tanggal;
    private String konten;
    private String status;
    private ArrayList<Comment> komentar;
    
    // default constructor
    public ViewPost() {
        komentar = new ArrayList<Comment>();
    }
    
    // function
    public void runPost(int i) {
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
    public void runComment(int i) {
       try {
            komentar.clear();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from commentdata where id_post=" + i);
            while(rs.next() == true) {
                Comment c = new Comment();
                c.setIdc(rs.getInt(1));
                c.setIdp(rs.getInt(2));
                c.setNama(rs.getString(3));
                c.setTanggal(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setKomentar(rs.getString(6));
                komentar.add(c);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }
    public void addComment(String nama, String tanggal, String email, String komentar) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO commentdata (id_post, nama, tanggal, email, komentar) VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nama);
            preparedStatement.setString(3, tanggal);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, komentar);
            preparedStatement.executeUpdate();
//            FacesContext.getCurrentInstance().getExternalContext().redirect("publish.xhtml");
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
    public ArrayList getKomentar() {
        return komentar;
    }
}