/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Login.Login;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rikysamuel
 */
@ManagedBean(name="Posting", eager = true)
@SessionScoped
public class PostingDatabase {
    
    Login login;
    
    public PostingDatabase(){
        login = new Login();
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
   
    public void addPost() throws ClassNotFoundException, SQLException, IOException, ParseException{
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Judul = request.getParameter("Judul");
        String Tanggal = request.getParameter("Tanggal");
        String Konten = request.getParameter("Konten");
        ResultSet rs;
          Connection con = makeConnection();
          Statement stmt = con.createStatement();
          String query = "Select COUNT(Id) from post";
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
          java.util.Date parsed = format.parse(Tanggal);
          System.out.println(parsed);
          java.sql.Date datesql = new java.sql.Date(parsed.getTime());
          
          rs = stmt.executeQuery(query);
          PreparedStatement ps;
          int countsumId = 0;
          while(rs.next()){
             countsumId = rs.getInt(1);
           }
            String query2 = "INSERT INTO post (Judul, Tanggal, Content, Author, Status) VALUES (?,?,?,?,?)";
            ps= con.prepareStatement(query2);
            ps.setString(1,Judul);
            ps.setDate(2,datesql);
            ps.setString(3,Konten);
            ps.setString(4,"chobits");
            ps.setString(5,"unpublished");
            int i = ps.executeUpdate();
            ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
            extcon.redirect("Home.xhtml");
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
    
    public String setLoginOnLoad() throws ClassNotFoundException, SQLException{
        System.out.println("asdasdasd");
        Cookie cUsername = login.getUserCookie();
//        System.out.println(cUsername.getValue());
        Cookie cPassword = login.getPassCookie();
//        System.out.println(cPassword.getValue());
        if (cUsername!=null && cPassword!=null){
            ResultSet rs;
            int existUser=0;
            Statement stmt = makeConnection().createStatement();
            String query = "Select COUNT(Username) from user where Username=\""+cUsername.getValue()+"\" and Password=\""+cPassword.getValue()+"\";";
            rs = stmt.executeQuery(query);

            while(rs.next()){
                existUser = rs.getInt(1);
                System.out.println(existUser);
            }
            if (existUser>0){
                System.out.println("masuk");
                return "Role/Owner.xhtml?faces-redirect=true";
            }
            else{
                return "Home.xhtml";
            }
        }
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
