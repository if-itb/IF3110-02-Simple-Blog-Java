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
import java.sql.SQLException;
import java.util.Map;
import javax.faces.bean. ManagedProperty ;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andarias Silvanus
 */
@RequestScoped
@ManagedBean(name="deletepost",eager = true)
public class deletepost implements Serializable
{
    private String judul;
    private String author;
    private String action;
    @ManagedProperty(value ="#{user}")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    
    public String getDelete()
    {
        try
        {
            System.out.println("=============================================================Masuk map params");
            Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String id_pars = params.get("deleteAction");
            System.out.println("id pars: "+id_pars);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "UPDATE post SET status='deleted' WHERE (id) = (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id_pars);
            int executeUpdate = 0;
            //int executeUpdate = ps.executeUpdate();
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
        return "owner.xhtml";
    }

    public void setAction(String action) {
        this.action = action;
    }
    
}
