package bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "addUser", eager=true)
@RequestScoped
public class addUser {

    private String username;
    private String email;
    private String role;
    private String password;
    private String param;

    public List<addUser> fetchFromDB()
    {
        System.out.println("fetch from db add user");
        List<addUser> records = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java","root","");
            PreparedStatement query = con.prepareStatement("select * from user");
            System.out.println("Connected!");
            ResultSet result = query.executeQuery();
            while(result.next())
            {
                addUser temp = new addUser();
                temp.username = result.getString("username");
                temp.password = result.getString("password");
                temp.role = result.getString("role");
                temp.email = result.getString("email");
                records.add(temp);
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("FETCH Success");
        return records;
    }
    
    public String getCheckAvail()
    {
        List<addUser> records = fetchFromDB();
        boolean avail = true;
        for(addUser u : records)
        {
            if(u.getUsername().equals(username))
            {
                avail = false;
                break;
            }
        }
        if(avail)
        {
            System.out.println("username = " + username);
            System.out.println("password = " + password);
            System.out.println("email = " + email);
            System.out.println("role = " + role);
            insert();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("userManagement.xhtml");
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        return null;
    }
    
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
    
    public addUser() {
        username="";
        role="";
        email="";
        password="";
        param="";
    }
    
    public String getAutoFill()
    {
        System.out.println("AUTO FILL");
        Map<String, String> params =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(params.get("username")!=null)
        {
            param = params.get("username");
        }
        System.out.println("param:"+param);
        List<addUser>records = fetchFromDB();
        for(addUser u : records)
        {
            if(u.getUsername().equals(param))
            {
                username = u.getUsername();
                password=u.getPassword();
                role=u.getRole();
                email=u.getEmail();
                break;
            }
        }
        //delete
        delete();
        System.out.println("finish");
        return null;
    }
    
    public void insert()
    {
        System.out.println("insert");
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
            System.out.println("connected to database!");
            String query = "INSERT INTO user (username, password, email, role) VALUES (?,?,?,?)";
            System.out.println("QUERY = " + query);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, role);
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
            System.out.println("connection closed");
        } 
        catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
        {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete()
    {
        if(param != null)
        {
            System.out.println("delete");
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/simple_blog_java", "root","");
                System.out.println("connected to database!");
                String query = "DELETE FROM user WHERE username = '"+param+"'";
                System.out.println("QUERY = " + query);
                PreparedStatement ps = con.prepareStatement(query);
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
                System.out.println("connection closed");
            } 
            catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) 
            {
                System.out.println(ex.toString());
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
