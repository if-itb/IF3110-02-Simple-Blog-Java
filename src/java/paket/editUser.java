/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;

/**
 *
 * @author TOSHIBA
 */
public class editUser {

    private String usrname, passwrd, nama_usr, email_usr, role;

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public String getNama_usr() {
        return nama_usr;
    }

    public void setNama_usr(String nama_usr) {
        this.nama_usr = nama_usr;
    }

    public String getEmail_usr() {
        return email_usr;
    }

    public void setEmail_usr(String email_usr) {
        this.email_usr = email_usr;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    /**
     * Creates a new instance of editUser
     */
    public editUser() {
    }
    
    public void ambilUser(){
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        String peta = requestMap.toString();
        
        Pattern pattern = Pattern.compile( "param=(\\w+)" );
        Matcher matcher = pattern.matcher( peta );
        if ( matcher.find() )
            usrname = matcher.group( 1 );
        
        System.out.print(usrname);
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT * FROM `usr` WHERE usrname='" +usrname +"'";
            ResultSet rSet = stmnt.executeQuery(sqlStr);
            
            if (rSet.next()){
                usrname = rSet.getString("usrname");
                passwrd = rSet.getString("passwrd");
                nama_usr = rSet.getString("nama_usr");
                email_usr = rSet.getString("email_usr");
                role = rSet.getString("role");
            }
            
        } catch (SQLException e){
            
        }
    }
    
    public void updateUser() throws IOException {
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
        
//        System.out.print(idpost);
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "UPDATE usr SET passwrd='" +passwrd +"', nama_usr='" +nama_usr +"','"+email_usr+"','"+role+"' WHERE usrname=" +usrname;
            stmnt.executeUpdate(sqlStr);
                        
            FacesContext.getCurrentInstance().getExternalContext().redirect("ManageUser.xhtml");
            
        } catch (SQLException e){
            
        }
    }
}
