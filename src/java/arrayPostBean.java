/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Fahziar
 */
@ManagedBean
@RequestScoped
public class arrayPostBean {

    /**
     * Creates a new instance of arrayPostBean
     */
    
    private ArrayList <postBean> posts;
    public arrayPostBean() 
    {
        posts = new ArrayList<postBean>();
        fetchPosts();
    }
    
    public void fetchPosts()
    {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        getPosts().clear();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + Config.dbName + "?user=" + Config.dbUsername + "&password=" + Config.dbPassword);

            preparedStatement = connect.prepareStatement("SELECT * from `posts` ORDER BY `tanggal` DESC");
            resultSet = preparedStatement.executeQuery();
            
            System.out.println(resultSet.isAfterLast());
            while (resultSet.next())
            {
                postBean post = new postBean();
                
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                String konten = resultSet.getString("konten");
                String author = resultSet.getString("penulis");
                String tanggal = resultSet.getString("tanggal");
                boolean published = resultSet.getBoolean("published");
                boolean deleted = resultSet.getBoolean("deleted");
                
                System.out.println("Published:" + published);
                System.out.println("Deleted:" + deleted);
                post.setId(id);
                post.setJudul(judul);
                post.setAuthor(author);
                post.setKonten(konten);
                post.setDate(tanggal);
                post.setPublished(published);
                post.setDeleted(deleted);
                System.out.println("Posts published:" + post.isPublished());
                posts.add(post);
            }
            
                
        } catch (Exception e)
        {
            System.out.println("Class UserManagementArrayBean: Failed to fetch users");
        } finally {
           Close(resultSet, preparedStatement, connect);
        }   
    }
    
     private void Close(ResultSet r, PreparedStatement p, Connection c)
    {
        if (r != null)
        {
            try{
                r.close();
            } catch (Exception e)
            {
            }
        }
        
        if (p != null)
        {
            try{
                p.close();
            } catch (Exception e)
            {
            }
        }
        if (c != null)
        {
            try{
                c.close();
            } catch (Exception e)
            {
                
            }
        }
    }

    /**
     * @return the posts
     */
    public ArrayList <postBean> getPosts() {
        return posts;
    }
    
}
