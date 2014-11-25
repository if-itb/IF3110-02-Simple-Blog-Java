/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TOSHIBA
 */
public class Login implements Serializable{

    String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
    String uName = "root";
    String pass = "";
    String usrname;
    String passwrd;

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
    
    /**
     * Creates a new instance of Login
     */
    public void login() throws IOException {
        Connection conn = null;
        Statement stmnt = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT * FROM `usr` WHERE usrname ='" + usrname+ "'";
            
            ResultSet rset = stmnt.executeQuery(sqlStr);
            
            if(!rset.first()){
                System.out.print("username tidak ada");
            }else{
                String passwrd1 = rset.getString("passwrd");
                String role = rset.getString("role");
                if (passwrd1.equals(passwrd)){
                    
                    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                    String name = rset.getString("nama_usr");
                    //create cookies
                    Cookie cookie = new Cookie("user", name);
                    cookie.setMaxAge(3600);
                    Cookie cookie2 = new Cookie("role", role);
                    cookie2.setMaxAge(3600);
                    
                    response.addCookie(cookie);
                    response.addCookie(cookie2);
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                    
                }else{
                    System.out.print("password salah");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.print("Unable to connect database");
            System.out.print(ex.toString());
        }finally{
            try {
                // Step 5: Close the Statement and Connection
                if (stmnt != null) {
                    stmnt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
