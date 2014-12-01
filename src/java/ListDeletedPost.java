import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "listDeletedPost", eager = true)
@RequestScoped
public class ListDeletedPost {

    // attribute
    private ArrayList<Post> post;
    
    // default constructor
    public ListDeletedPost() {
        post = new ArrayList<Post>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from postdata where status='deleted'");
            while(rs.next() == true) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setJudul(rs.getString(2));
                p.setTanggal(rs.getString(3));
                p.setKonten(rs.getString(4));
                p.setStatus(rs.getString(5));
                post.add(p);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // function
    public void restoredelete(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE postdata SET status='unpublished' where id_post=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("deleted.xhtml");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void search(String judul) {
        post = new ArrayList<Post>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simpleblog2", "root", "");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from postdata where judul like %" + judul + "%");
            while(rs.next() == true) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setJudul(rs.getString(2));
                p.setTanggal(rs.getString(3));
                p.setKonten(rs.getString(4));
                p.setStatus(rs.getString(5));
                post.add(p);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // getter
    public ArrayList<Post> getPost() {
        return post;
    }
}