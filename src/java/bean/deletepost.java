/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.IOException;
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
import java.util.Map;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.bean.SessionScoped;
import javax.faces.bean. ManagedProperty ;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andarias Silvanus
 */
@ManagedBean(name="deletepost", eager=true)
@SessionScoped
public class deletepost implements Serializable
{
    private String judul;
    //@ManagedProperty(value="#{user.username}")
    private String author;
    private String action;
    //@ManagedProperty(value="#{user.role}")
    private String peran;
    @ManagedProperty(value="#{user}")
    private User user;

    public void setPeran(String peran) {
        this.peran = peran;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPeran() {
        return peran;
    }

    public User getUser() {
        return user;
    }
    
    public deletepost() 
    {
        judul = "";
    }

    public String getAuthor() {
        return author;
    }

    public String getJudul() {
        return judul;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public void delete()
    {
        System.out.println("Masuk void delete");
        try
        {
            System.out.println("Masuk map params");
            
            Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String action = params.get("id");
        
            String id_pars = action;
            System.out.println("id pars: "+id_pars);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "UPDATE post SET status='deleted' WHERE (id) = (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id_pars);
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
        catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
        {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
        System.out.println("Username: "+user.getUsername()+", Role: "+user.getRole());
        RedirectPage();
    }
    
    public void RedirectPage()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try 
        {
            context.redirect(context.getRequestContextPath() + "/" + user.getRole() + ".xhtml");
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setAction(String action) {
        this.action = action;
        System.out.println("masuk setAction, action yg diterima= "+action);
    }
}
