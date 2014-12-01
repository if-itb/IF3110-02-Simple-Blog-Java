
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kanya
 */
@ManagedBean( name = "userController", eager = true)
@SessionScoped
public class UserController {
    
    
    
    Connection con = null;
    Statement stmt = null;
   
    private User currentUser = new User();
    public UserController() throws ClassNotFoundException, SQLException {
        SetupDB();
    }
    
    //Cookie
    
     // set cookie according to current user
  
    
  public User getUserByUsername(String username) {
        try {
            String q = "SELECT * FROM `user` WHERE `username` = ?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(q);
            String[] uname = new String[]{username};
            for (int i = 0; i < uname.length; i++) {
                ps.setString(i+1, uname[i]);
            }
            ResultSet result = stmt.executeQuery(q);
            while (result.next()) {
                return new User(result);
            }
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    private void SetupDB() throws ClassNotFoundException, SQLException{
        String host = "jdbc:mysql://localhost:3306/simple_blog_java?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = (Connection) DriverManager.getConnection(host, user, pwd);
        stmt = (Statement) con.createStatement();        
    }
    
    public void addUser(String usern, String passw, String mail, String rol) throws SQLException, IOException {
        String a = "INSERT INTO tb_user (username,password,email,role) VALUES ('"+usern+"','"+passw+"','"+mail+"','"+rol+"')";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(a);
        int j = ps.executeUpdate();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("manageuser.xhtml");
    }
    
    public void deleteUser(int id) throws SQLException, IOException {
        //System.out.println("disini");
        String q = "DELETE FROM tb_user WHERE userid = "+id+";";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(q);
        int i = ps.executeUpdate(q);    
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("manageuser.xhtml");
    }
    
    public void editUser(int id, String usern, String passw, String mail, String rol) throws SQLException, IOException{
        getCurrentUser().setId(id);
        getCurrentUser().setUsername(usern);
        getCurrentUser().setPassword(passw);
        getCurrentUser().setEmail(mail);
        getCurrentUser().setRole(rol);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("edit_user.xhtml");
    }
    
    public void updateUser(int id, String usern, String passw, String mail, String rol) throws SQLException, IOException{
        String a = "UPDATE tb_user SET username = '"+usern+"', password = '"+passw+"', email = '"+mail+"', role = '"+rol+"' WHERE userid = "+id+";";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(a);
        int j = ps.executeUpdate();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("manageuser.xhtml");
    }
    
    public ArrayList<User> getListUser() throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        String q = "SELECT * FROM tb_user;";
        ResultSet rs = stmt.executeQuery(q);
        while (rs.next()) {
            User mUser = new User();
            mUser.setId(rs.getInt("userid"));
            mUser.setUsername(rs.getString("username"));
            mUser.setPassword(rs.getString("password"));
            mUser.setEmail(rs.getString("email"));
            mUser.setRole(rs.getString("role"));
            result.add(mUser);
        }
        return result;
    }

    /**
     * @return the currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

   
}

