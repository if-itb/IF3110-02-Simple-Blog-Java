/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.bean.SessionScoped;
import javax.faces.bean. ManagedProperty ;

/**
 *
 * @author Andarias Silvanus
 */
@ManagedBean(name = "newpost", eager=true)
@SessionScoped
public class newpost implements Serializable
{
    /**
     * Creates a new instance of newpost
     */
    private String judul;
    @ManagedProperty(value="#{user.username}")
    private String author;
    private Date tanggal;
    private String konten;
    @ManagedProperty(value="#{user.role}")
    private String status;
   
    /*
    @ManagedProperty(value="#{user}")
    private User user; */
    
    public newpost() 
    {
        judul = "";
        tanggal = null;
        konten = "";
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
    
    public void add_post()
    {
        System.out.println("new post");
        try
        {
            java.sql.Date newTanggal = new java.sql.Date(tanggal.getTime());
            System.out.println("Author: "+author);
            System.out.println("Status: "+status);
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "INSERT INTO post (judul, author, tanggal, konten, status) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, judul);
            ps.setString(2, author);
            ps.setDate(3, newTanggal);
            ps.setString(4, konten);
            ps.setString(5, status);
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
            /*
            judul = "";
            konten = "";
            tanggal = null; */
        } 
        catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
        {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
}
