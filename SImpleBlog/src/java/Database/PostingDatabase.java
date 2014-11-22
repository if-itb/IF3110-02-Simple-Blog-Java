/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rikysamuel
 */
@ManagedBean(name="Posting", eager = true)
@SessionScoped
public class PostingDatabase {
    public PostingDatabase(){
    }
    
    public Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/blog";
        String user = "root";
        String password = "";
        con = DriverManager.getConnection(url,user,password);
        return con;
    }
    
    public List<Post> getPost() throws ClassNotFoundException{
        ResultSet rs;
        List<Post> records = new ArrayList<>();
        try {
          Statement stmt = makeConnection().createStatement();
          String query = "Select * from post where status=\"Published\"";
          rs = stmt.executeQuery(query);

          while(rs.next()){
              Post post = new Post();
              post.setId(rs.getInt(1));
              post.setJudul(rs.getString(2));
              post.setTanggal(rs.getString(3));
              post.setContent(rs.getString(4));
              post.setAuthor(rs.getString(5));
              post.setStatus(rs.getString(6));
              records.add(post);
           }
        } catch (SQLException e) {
           System.err.println(e);
        }
        return records;
   }
    
    public List<Post> getAuthorPost() throws ClassNotFoundException{
        ResultSet rs;
        List<Post> records = new ArrayList<>();
        try {
          Statement stmt = makeConnection().createStatement();
          String query = "Select * from post where author=\"chobits\"";
          rs = stmt.executeQuery(query);

          while(rs.next()){
              Post post = new Post();
              post.setId(rs.getInt(1));
              post.setJudul(rs.getString(2));
              post.setTanggal(rs.getString(3));
              post.setContent(rs.getString(4));
              post.setAuthor(rs.getString(5));
              post.setStatus(rs.getString(6));
              records.add(post);
           }
        } catch (SQLException e) {
           System.err.println(e);
        }
        return records;
   }
   
    public void addPost() throws ClassNotFoundException, SQLException{
          ResultSet rs;
          Connection con = makeConnection();
          Statement stmt = con.createStatement();
          String query = "Select COUNT(Id) from posting";
          rs = stmt.executeQuery(query);
          PreparedStatement ps;
          int countsumId = 0;
          while(rs.next()){
             countsumId = rs.getInt(1);
           }
          System.out.println(countsumId);
          System.out.println("PINGGG");
            String query2 = "INSERT INTO `posting` (`Id`,`Judul`, `Tanggal`, `Content`, `Author`, `Status`) VALUES (?,?,?,?,?,?)";
            ps= con.prepareStatement(query2);
            ps.setInt(1,countsumId+1);
            ps.setString(2,"Doremi");
            ps.setString(3,"1-2-3");
            ps.setString(4,"ini adalah not");
            ps.setString(5,"Doni");
            ps.setString(6,"unpublished");
            int i = ps.executeUpdate();     
    }
    
    public String addUserOwner() throws ClassNotFoundException, SQLException{
        
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Name = request.getParameter("Name");
        String Username = request.getParameter("Username");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        
        Connection con = makeConnection();
        PreparedStatement ps;
        String query = "INSERT INTO `user` (`Username`,`Password`, `Name`, `email`, `Role`) VALUES (?,?,?,?,?)";
        ps= con.prepareStatement(query);
        ps.setString(1,Username);
        ps.setString(2,Password);
        ps.setString(3,Name);
        ps.setString(4,Email);
        ps.setString(5,"Owner");
        int i = ps.executeUpdate();
        return "Home.xhtml";
    }
    
    public String Login() throws ClassNotFoundException, SQLException{
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Username = request.getParameter("login_username");
        String Password = request.getParameter("login_password");
        ResultSet rs;
        int existUser=0;
        Statement stmt = makeConnection().createStatement();
        String query = "Select COUNT(Username) from user where Username=\""+Username+"\" and Password=\""+Password+"\";";
        rs = stmt.executeQuery(query);

        while(rs.next()){
            existUser = rs.getInt(1);
         }
        if (existUser>0){
            return "Role/Owner.xhtml?faces-redirect=true";
        }
        else{
            return "Home.xhtml";
        }
    }
    
}
