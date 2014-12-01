/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleblog.databaseController;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kevhnmay94
 */
@ManagedBean(name="Pages")
@RequestScoped
public class PagesController {
    
    public class Page
    {
        int id;
        String title;
        String content;
        String owner;
        Date date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
        
        
    }
    private ArrayList<Page> pagelist;
    private String current_title;
    private String current_date;
    private String current_content;
    private String current_owner;
    private int current_id;
    private final Connection c;

    public String getCurrent_owner() {
        return current_owner;
    }

    public void setCurrent_owner(String current_owner) {
        this.current_owner = current_owner;
    }

    public int getCurrent_id() {
        return current_id;
    }

    public void setCurrent_id(int current_id) {
        this.current_id = current_id;
    }

    public String getCurrent_title() {
        return current_title;
    }

    public void setCurrent_title(String current_title) {
        this.current_title = current_title;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getCurrent_content() {
        return current_content;
    }

    public void setCurrent_content(String current_content) {
        this.current_content = current_content;
    }

    public ArrayList<Page> getPagelist() {
        return pagelist;
    }

    /**
     * Creates a new instance of PagesController
     * @throws java.lang.Exception
     */
    public PagesController() throws Exception {
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/simpleblog","root", "");
    }
    
    public void listPost() throws Exception
    {
        String query = "SELECT * FROM public.pages WHERE publish=true AND"
                + "soft_delete=false";
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(query);
        while(rs.next())
        {
            Page p = new Page();
            p.setTitle(rs.getString("title"));
            p.setContent(rs.getString("content"));
            p.setOwner(rs.getString("owner"));
            p.setId(rs.getInt("id"));
            p.setDate(rs.getDate("date"));
            pagelist.add(p);
        }
    }
    
    public void newPost() throws Exception
    {
        String query = "INSERT INTO public.pages (title,content,owner,date) "
                        + "('" + current_title + "','" + current_content + "','"
                        + current_owner + "','" + current_date +"')";
        Statement s = c.createStatement();
        s.executeUpdate(query);
    }
    
    public void editPost(int id) throws Exception
    {
        String query = "SELECT * FROM public.pages WHERE id=" + id;
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(query);
        rs.first();
        current_title = rs.getString("title");
        current_content = rs.getString("content");
        current_date = rs.getString("date");
        current_id = id;
    }
    
    public void updatePost() throws Exception
    {
        String query = "UPDATE public.pages SET title='"+current_title +"', "
                        + "content='" + current_content + "', date=" + current_date + " WHERE "
                        + "ID=" + current_id;         
        Statement s = c.createStatement();
        s.executeUpdate(query);
        c.close();
    }
    
    public void softdeletePost(int id) throws Exception
    {
        String query = "UPDATE public.pages SET soft_delete=true WHERE "
                        + "ID=" + current_id;   
        Statement s = c.createStatement();
        s.executeUpdate(query);
        c.close();
    }
    
    public void deletePost(int id) throws Exception
    {
        String query = "DELETE FROM public.pages WHERE id=" + id;
        Statement s = c.createStatement();
        s.executeUpdate(query);
        c.close();
    }
    
    public void publishPost(int id) throws Exception
    {
        String query = "UPDATE public.pages SET publish=true WHERE id=" + id;
        Statement s = c.createStatement();
        s.executeUpdate(query);
        c.close();
    }
}
