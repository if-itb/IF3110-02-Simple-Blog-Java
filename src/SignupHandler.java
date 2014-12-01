/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Riady
 */
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "signupHandler",eager=true)
public class SignupHandler {
    private String username;
    private String password;
    private String password2;
    private String nama;
    private int role; //0=guest, 1=owner, 2=editor, 3=admin 
    private Connection con;
    
    public SignupHandler(){
        username="";
        password="";
        role=2;
    }
    
    public void setNama(String snama){
        nama=snama;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setUsername(String user){
        username=user;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setPassword(String pass){
        password=pass;
    }
    
    public String getPassword(){
        return password;
    }

    
    public void makeConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd_db","root","");
        
    }
    
    public void execute() throws SQLException, ClassNotFoundException{
        if(password==password2){
            makeConnection();
            PreparedStatement st;
            st = (PreparedStatement)con.prepareStatement("INSERT INTO user (username,password,nama,role) VALUES (?,?,?,?)");
            st.setString(1,username);
            st.setString(2,password);
            st.setString(3,nama);
            st.setString(4,"editor");
        }
        
        
    }

}
    
    
 
 