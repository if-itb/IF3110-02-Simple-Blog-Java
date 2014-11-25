/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.faces.bean. ManagedProperty ;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andarias Silvanus
 */
@ManagedBean(name="editpost")
@SessionScoped
public class editpost implements Serializable
{
    @ManagedProperty(value="#{user}")
    private User user;
    private Post post;
    private String judul;
    private String author;
    private Date tanggal;
    private String tanggalString;
    private String konten;
    private String status;
    private String postID;

    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public String getAuthor() {
        return author;
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

    public Date getTanggal() {
        return tanggal;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setTanggalString(String tanggalString) {
        this.tanggalString = tanggalString;
    }

    public String getTanggalString() {
        return tanggalString;
    }
    
    public editpost() 
    {
        post = new Post();
    }
    
    public String getAutoFill()
    {
        System.out.println("getAutoFill");
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        List<Post> posts = post.getPosts();
        postID = params.get("id");
        System.out.println("id = " + postID);
        for(Post p : posts)
        {
            if(p.getId() == Integer.parseInt(postID))
            {
                judul = p.getJudul();
                status = p.getStatus();
                konten = p.getKonten();
                author = user.getUsername();
                tanggalString = p.getTanggal();
                System.out.println("judul = " + judul);
                System.out.println("status = " + status);
                System.out.println("konten = " + konten);
                System.out.println("author = " + author);
                System.out.println("tanggal = " + tanggalString);
            }
        }
        return null;
    }
    
    public String changepost()
    {
        System.out.println("edit post");
        try
        {
            System.out.println("Change Post ID = " + postID);
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(tanggalString);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "UPDATE post SET judul=?, author=?, tanggal=?, konten=?, status=? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, judul);
            ps.setString(2, author);
            ps.setDate(3, new java.sql.Date(tanggal.getTime()));
            ps.setString(4, konten);
            ps.setString(5, status);
            ps.setInt(6, Integer.parseInt(postID));
            System.out.println(ps.toString());
            int executeUpdate = ps.executeUpdate();
            if(executeUpdate > 0)
            {
                System.out.println("update succesful");
            }
            else
            {
                System.out.println("update failure");
            }
            con.close();
        }
        catch (ParseException | InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
        {
            System.out.println(ex.toString());
        }
        if(user.getRole().equals("owner"))
        {
            return "edit_post_owner.xhtml";
        }
        else
        {
            return user.getRole()+".xhtml";
        }
    }
}
