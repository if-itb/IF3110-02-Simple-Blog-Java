package bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "post", eager=true)
@RequestScoped
public class Post implements Serializable
{
    @ManagedProperty(value = "#{param.id}")
    private int id;
    
    private String judul;
    private String author;
    private String tanggal;
    private String konten;
    private String status;
    private String query_status;
    List<Post> daftar_post;
    
    public Post() 
    {
        System.out.println("Post created");
        id = 0;
        judul = "";
        author = "";
        tanggal = "";
        konten = "";
        status = "";
        query_status ="";
        daftar_post = new ArrayList<>();
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

    public String getStatus() {
        return status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getQuery_status() {
        return query_status;
    }

    public List<Post> getDaftar_post() {
        fetchPostsFromDB();
        return daftar_post;
    }
    
    public List<Post> getEditablePost()
    {
        List<Post> daftar_post = new ArrayList<>();
        try {
            System.out.println("getEditablePost");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root", "");
            System.out.println("Connection Created!");
            String query = "Select * from post where status != 'deleted'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            System.out.println("Query successful");
            while(result.next())
            {
                Post temp = new Post();
                temp.id = result.getInt("id");
                temp.judul = result.getString("judul");
                temp.tanggal = result.getString("tanggal");
                temp.konten = result.getString("konten");
                temp.status = result.getString("status");
                temp.author = result.getString("author");
                System.out.println(judul);
                daftar_post.add(temp);
            }
            con.close();
            System.out.println("Connection close");
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            System.out.println(ex.toString());
        }
        return daftar_post;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setDaftar_post(List<Post> daftar_post) {
        this.daftar_post = daftar_post;
    }

    public void setQuery_status(String query_status) {
        this.query_status = query_status;
    }
    
    public void fetchPostsFromDB()
    {
        try {
            System.out.println("Fetch Posts from DB");
            daftar_post = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java","root","");
            System.out.println("Connection Created!");
            if(query_status.length() == 0)
            {
                throw new Exception("Query_status has not been declared");
            }
            String query = "SELECT * FROM `post` WHERE status = " + "'" + query_status + "'";
            System.out.println("query:" + query);
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while(result.next())
            {
                Post temp = new Post();
                temp.id = result.getInt("id");
                temp.judul = result.getString("judul");
                temp.author = result.getString("author");
                temp.tanggal = result.getString("tanggal");
                temp.konten = result.getString("konten");
                temp.status = result.getString("status");
                daftar_post.add(temp);
            }
            System.out.println("Posts sucessfully fetched");
            con.close();
            System.out.println("connection closed");
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public void printDB()
    {
        fetchPostsFromDB();
        for(Post post : daftar_post)
        {
            System.out.println(post.judul);
            System.out.println(post.tanggal);
            System.out.println(post.konten);
        }
    }
    public void publish()
    {
        try {
            System.out.println("publish");
            System.out.println("ID = " + id);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java","root","");
            System.out.println("connection created");
            String query = "update post set `status` = 'published' where `id` = " + id;
            System.out.println("query : " + query);
            PreparedStatement ps = con.prepareStatement(query);
            int retval = ps.executeUpdate();
            if(retval < 0)
            {
                System.out.println("Query unsuccessful");
            }
            else
            {
                System.out.println("Query Succesfu  l");
            }
            con.close();
            System.out.println("connection close");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.toString());
        } 
    }
}
