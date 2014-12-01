/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Login.Login;
import java.io.IOException;
import java.sql.Connection;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class PostingDatabase {
    
    Login login;
    String SelectedItem;
    
    @ManagedProperty(value="#{param.throwedauthor}")
    private String author;
    
    @ManagedProperty(value ="#{param.throwedid}")
    private int id;
    
    @ManagedProperty(value="#{param.throwediduser}")
    private String username;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getSelectedItem() {
        return SelectedItem;
    }

    public void setSelectedItem(String SelectedItem) {
        this.SelectedItem = SelectedItem;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public PostingDatabase(){
        login = new Login();
    }
    
    public void toHome() throws IOException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        extCont.redirect("/SImpleBlog/Home.xhtml");
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
        Connection con;
        List<Post> records = new ArrayList<>();
        try {
          con = makeConnection();
          Statement stmt = con.createStatement();
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
          con.close();
        } catch (SQLException e) {
           System.err.println(e);
        }
        return records;
   }
    
    public List<Post> getAuthorPost() throws ClassNotFoundException{
        ResultSet rs;
        Connection con;
        List<Post> records = new ArrayList<>();
        try {
          con = makeConnection();
          Statement stmt = con.createStatement();
          String query = "Select * from post where author=\""+login.getUserCookie().getValue()+"\" and status=\"published\";";
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
          con.close();
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
        try (Connection con = makeConnection()) {
            Statement stmt = con.createStatement();
            String query = "Select COUNT(Id) from post";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(Tanggal);
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
            ps.setString(4,login.getUserCookie().getValue());
            ps.setString(5,"unpublished");
            int i = ps.executeUpdate();
            con.close();
        }
            ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
            extcon.redirect("/SImpleBlog/Home.xhtml");
    }

    public void deletePost() throws ClassNotFoundException, SQLException, IOException, ParseException{
        try (Connection con = makeConnection()) {
            Statement stmt = con.createStatement();
            String query = "Update post Set status=\"deleted\" WHERE ID="+id;
            int rs;
            rs = stmt.executeUpdate(query);
            con.close();
        }
          ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
          extcon.redirect("/SImpleBlog/Home.xhtml");
    }
    
    public void PublishPost() throws ClassNotFoundException, IOException, ParseException, SQLException{
        try (Connection con = makeConnection()){
            Statement stmt = con.createStatement();
            String query = "Update post Set status=\"published\" WHERE ID="+id;
            int rs;
            rs = stmt.executeUpdate(query);
            PreparedStatement ps;
            con.close();
        }
            ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
            extcon.redirect("/SImpleBlog/Home.xhtml");
    }

    public void setLoginOnLoad() throws ClassNotFoundException, SQLException, IOException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        Cookie cUsername = login.getUserCookie();
        Cookie cPassword = login.getPassCookie();
        if (cUsername!=null && cPassword!=null){
            ResultSet rs,rs2;
            Connection con;
            con = makeConnection();
            int existUser=0;
            String UserRole = null;
            Statement stmt = con.createStatement();
            String query = "Select COUNT(Username) from user where Username=\""+cUsername.getValue()+"\" and Password=\""+cPassword.getValue()+"\";";
            rs = stmt.executeQuery(query);  
            while(rs.next()){
                existUser = rs.getInt(1);
                if (existUser>0){
                    if(getUserRole().equals("Editor"))
                    {
                        extCont.redirect("/SImpleBlog/Role/Editor.xhtml");
                    }
                    if(getUserRole().equals("Owner"))
                    {
                        extCont.redirect("/SImpleBlog/Role/Owner.xhtml");
                    }
                    if(getUserRole().equals("Admin"))
                    {
                        extCont.redirect("/SImpleBlog/Role/Admin.xhtml");
                    }
                }
                else{
                    extCont.redirect("/SImpleBlog/Home.xhtml");
                }
            }
            con.close();
        }
        
    }
    
    public void Login() throws ClassNotFoundException, SQLException, IOException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Username = request.getParameter("login_username");
        String Password = request.getParameter("login_password");
        String UserRole = null;
        ResultSet rs,rs2;
        int existUser;
        Connection con;
        con = makeConnection();
        Statement stmt = con.createStatement();
        String query = "Select COUNT(Username) from user where Username=\""+Username+"\" and Password=\""+Password+"\";";
        rs = stmt.executeQuery(query);

        while(rs.next()){
            existUser = rs.getInt(1);
            if(existUser>0){
                login.setCookie(Username,Password);
                String query2 = "Select Role from user where Username=\""+Username+"\";";
                rs2 = stmt.executeQuery(query2);
                while(rs2.next()){
                    UserRole = rs2.getString("Role");
                    if(UserRole.equals("Editor"))
                        {
                            extCont.redirect("/SImpleBlog/Role/Editor.xhtml");
                        }
                        if(UserRole.equals("Owner"))
                        {
                            extCont.redirect("/SImpleBlog/Role/Owner.xhtml");
                        }
                        if(UserRole.equals("Admin"))
                        {
                            extCont.redirect("/SImpleBlog/Role/Admin.xhtml");
                        } 
                    }
                }
            else{
                extCont.redirect("/SImpleBlog/Home.xhtml");
            }
        }
        con.close();
    }
    
    public String getActiveUser() throws ClassNotFoundException, SQLException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        Cookie cUsername = login.getUserCookie();
        String activeUser = null;
        
        ResultSet rs;
        Connection con;
        con = makeConnection();
        Statement stmt = con.createStatement();
        String query = "Select Name from user where Username=\""+cUsername.getValue()+"\";";
        rs = stmt.executeQuery(query);
        while(rs.next()){
            activeUser = rs.getString("Name");
         }
        con.close();
        return activeUser;
    }
    
    public String getActiveUsername() throws ClassNotFoundException, SQLException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        Cookie cUsername = login.getUserCookie();
        String activeUsername = null;
        
        ResultSet rs;
        Connection con;
        con = makeConnection();
        Statement stmt = con.createStatement();
        String query = "Select Username from user where Username=\""+cUsername.getValue()+"\";";
        rs = stmt.executeQuery(query);
        while(rs.next()){
            activeUsername = rs.getString("Username");
         }
        return activeUsername;
    }
    
    public String getUserRole() throws ClassNotFoundException, SQLException{
        Cookie cUsername = login.getUserCookie();
        String UserRole = null;
        
        ResultSet rs;
        Connection con;
        con = makeConnection();
        Statement stmt = con.createStatement();
        String query = "Select Role from user where Username=\""+cUsername.getValue()+"\";";
        rs = stmt.executeQuery(query);
        while(rs.next()){
            UserRole = rs.getString("Role");
         }
        con.close();
        return UserRole;
    }
    
    public void setLogout() throws ClassNotFoundException, SQLException, IOException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        login.delUserCookie();
        login.delPassCookie();
        extCont.redirect("/SImpleBlog/Home.xhtml");
    }
    
    public boolean getLoginState(){
        return(login.getUserCookie() != null);
    } 
    
    public String getActiveUserEmail() throws ClassNotFoundException, SQLException{
        Cookie cUsername = login.getUserCookie();
        String activeUserEmail = null;
        
        ResultSet rs;
        Connection con;
        con = makeConnection();
        Statement stmt = con.createStatement();
        String query = "Select email from user where Username=\""+cUsername.getValue()+"\";";
        rs = stmt.executeQuery(query);
        while(rs.next()){
            activeUserEmail = rs.getString("email");
         }
        con.close();
        return activeUserEmail;
    }
    
    public void checkAdminRole() throws ClassNotFoundException, SQLException, IOException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        if(getLoginState())
        {
            Cookie cUsername = login.getUserCookie();
            Cookie cPassword = login.getPassCookie();
            if (cUsername!=null && cPassword!=null){
                ResultSet rs,rs2;
                Connection con;
                con = makeConnection();
                int existUser=0;
                String UserRole = null;
                Statement stmt = con.createStatement();
                String query = "Select COUNT(Username) from user where Username=\""+cUsername.getValue()+"\" and Password=\""+cPassword.getValue()+"\";";
                rs = stmt.executeQuery(query);  
                while(rs.next()){
                    existUser = rs.getInt(1);
                    if (existUser>0){
                        if(!getUserRole().equals("Admin"))
                        {
                            extCont.redirect("/SImpleBlog/Home.xhtml");
                        }
                    }
                    else{
                        extCont.redirect("/SImpleBlog/Home.xhtml");
                    }
                }
                con.close();
            }
            else
            {
                extCont.redirect("/SImpleBlog/Home.xhtml");
            }
        }
        else
        {
            extCont.redirect("/SImpleBlog/Home.xhtml");
        }
        
    }
    
    public void CheckEditorRole() throws ClassNotFoundException, SQLException, IOException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        if(getLoginState())
        {
            Cookie cUsername = login.getUserCookie();
            Cookie cPassword = login.getPassCookie();
            if (cUsername!=null && cPassword!=null){
                ResultSet rs,rs2;
                Connection con;
                con = makeConnection();
                int existUser=0;
                String UserRole = null;
                Statement stmt = con.createStatement();
                String query = "Select COUNT(Username) from user where Username=\""+cUsername.getValue()+"\" and Password=\""+cPassword.getValue()+"\";";
                rs = stmt.executeQuery(query);  
                while(rs.next()){
                    existUser = rs.getInt(1);
                    if (existUser>0){
                        if(!getUserRole().equals("Editor"))
                        {
                            extCont.redirect("/SImpleBlog/Home.xhtml");
                        }
                    }
                    else{
                        extCont.redirect("/SImpleBlog/Home.xhtml");
                    }
                }
                con.close();
            }
            else
            {
                extCont.redirect("/SImpleBlog/Home.xhtml");
            }
        }
        else
        {
            extCont.redirect("/SImpleBlog/Home.xhtml");
        }
        
    }
    
    public void CheckOwnerRole() throws ClassNotFoundException, SQLException, IOException{
        ExternalContext extCont = FacesContext.getCurrentInstance().getExternalContext();
        if(getLoginState())
        {
            Cookie cUsername = login.getUserCookie();
            Cookie cPassword = login.getPassCookie();
            if (cUsername!=null && cPassword!=null){
                ResultSet rs,rs2;
                Connection con;
                con = makeConnection();
                int existUser=0;
                String UserRole = null;
                Statement stmt = con.createStatement();
                String query = "Select COUNT(Username) from user where Username=\""+cUsername.getValue()+"\" and Password=\""+cPassword.getValue()+"\";";
                rs = stmt.executeQuery(query);  
                while(rs.next()){
                    existUser = rs.getInt(1);
                    if (existUser>0){
                        if(!getUserRole().equals("Owner"))
                        {
                            extCont.redirect("/SImpleBlog/Home.xhtml");
                        }
                    }
                    else{
                        extCont.redirect("/SImpleBlog/Home.xhtml");
                    }
                }
                con.close();
            }
            else
            {
                extCont.redirect("/SImpleBlog/Home.xhtml");
            }
        }
        else
        {
            extCont.redirect("/SImpleBlog/Home.xhtml");
        }
    }   
    
    public List<User> getUsers() throws ClassNotFoundException, SQLException{
        List<User> records = new ArrayList<User>();
        
        ResultSet rs;
        Connection con;
        con = makeConnection();
        Statement stmt = con.createStatement();
        String query = "Select * from user where Name<>\""+getActiveUser()+"\";";
        rs = stmt.executeQuery(query);
        while(rs.next()){
              User user = new User();
              user.setUsername(rs.getString(1));
              user.setPassword(rs.getString(2));
              user.setName(rs.getString(3));
              user.setEmail(rs.getString(4));
              user.setRole(rs.getString(5));
              records.add(user);
        }
        con.close();
        return records;
    }
    
    public void setInsertNewUser() throws ClassNotFoundException, SQLException{
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Name = request.getParameter("Name");
        String Username = request.getParameter("Username");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        String Role = SelectedItem;
        
        try (Connection con = makeConnection()) {
            PreparedStatement ps;
            String query = "INSERT INTO `user` (`Username`,`Password`, `Name`, `email`, `Role`) VALUES (?,?,?,?,?)";
            ps= con.prepareStatement(query);
            ps.setString(1,Username);
            ps.setString(2,Password);
            ps.setString(3,Name);
            ps.setString(4,Email);
            ps.setString(5,Role);
            int i = ps.executeUpdate();
            con.close();
        }
    }
    
    public void setDeleteUser() throws ClassNotFoundException, SQLException{
        try (Connection con = makeConnection()) {
            Statement stmt = con.createStatement();
            String query = "Delete from user where username=\"" + username + "\";";
            int rs;
            rs = stmt.executeUpdate(query);
            PreparedStatement ps;
            con.close();
        }
    }
    
    public List<Post> getDeletedPosts() throws ClassNotFoundException{
      ResultSet rs;
      Connection con;
      List<Post> records = new ArrayList<>();
      try {
        con = makeConnection();
        Statement stmt = con.createStatement();
        String query = "Select * from post where status=\"deleted\";";
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
        con.close();
      } catch (SQLException e) {
         System.err.println(e);
      }
      return records;
  }
    
    public void setRestorePost(int id) throws ClassNotFoundException, SQLException, IOException{
        try (Connection con = makeConnection()) {
            Statement stmt = con.createStatement();
            String query = "Update post Set status=\"unpublished\" WHERE ID="+id;
            int rs;
            rs = stmt.executeUpdate(query);
            ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
            extcon.redirect("/SImpleBlog/Home.xhtml");
            con.close();
        }
    }
    
    public void setRealDeletePost(int id) throws ClassNotFoundException, SQLException, IOException{
        try (Connection con = makeConnection()) {
            Statement stmt = con.createStatement();
            String query = "Delete from post where id=\"" + id + "\";";
            int rs;
            rs = stmt.executeUpdate(query);
            ExternalContext extcon = FacesContext.getCurrentInstance().getExternalContext();
            extcon.redirect("/SImpleBlog/Home.xhtml");
            con.close();
        }
    }
    
    public void updateUser() throws ClassNotFoundException, SQLException, IOException
    {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String Name = request.getParameter("EditName");
        String Username = request.getParameter("EditUsername");
        String Email = request.getParameter("EditEmail");
        String Password = request.getParameter("EditPassword");
        String Role = SelectedItem;
        
        try (Connection con = makeConnection()) {
            PreparedStatement ps;
            String query = "UPDATE `user` SET `Password`=?,`Name`=?,`email`=?,`Role`=? WHERE `Username`=?";
            ps= con.prepareStatement(query);
            
            ps.setString(1,Password);
            ps.setString(2,Name);
            ps.setString(3,Email);
            ps.setString(4,Role);
            ps.setString(5,Username);
            int i = ps.executeUpdate();
            con.close();
        }
    }
    
}
