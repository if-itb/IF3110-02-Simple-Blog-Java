package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "postdata", eager=true)
@RequestScoped
public class PostData implements Serializable
{
    @ManagedProperty(value = "#{param['id']}")
    private int id;
    
    private String judul;
    private String author;
    private String tanggal;
    private String konten;
    
    public PostData() 
    {
        System.out.println("PostData created");
        judul = "";
        author = "";
        tanggal = "";
        konten = "";
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getKonten() {
        return konten;
    }

    public String getTanggal() {
        return tanggal;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
        fetchPostsFromDB();
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    public void fetchPostsFromDB()
    {
        try {
            System.out.println("Fetch Posts from DB");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java","root","");
            System.out.println("Connection Created!");
            String query = "SELECT * FROM `post` WHERE id = " + id ;
            System.out.println("query:" + query);
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while(result.next())
            {
                this.judul = result.getString("judul");
                this.author = result.getString("author");
                this.tanggal = result.getString("tanggal");
                this.konten = result.getString("konten");
            }
            System.out.println("Post sucessfully fetched");
            con.close();
            System.out.println("connection closed");
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

}
