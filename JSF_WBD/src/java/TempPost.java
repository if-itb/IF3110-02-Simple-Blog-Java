/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 
 * @author ASUS
 */
@ManagedBean(name="tempPost", eager = true)
@RequestScoped
public class TempPost {
    public int Id;
    public String Judul = "";
    public String Tanggal = "";
    public String Konten = "";
    /**
     * Creates a new instance of TempPost
     */
    public TempPost() {        
    }
    public void reset(){
            Id = -1;
            Judul = "";
            Tanggal = "";
            Konten = "";
    }
    public void submitPost() throws ClassNotFoundException, SQLException, IOException{
            String host = "jdbc:mysql://localhost:3306/simple_blog_java?zeroDateTimeBehavior=convertToNull";
            String user = "root";
            String pwd = "";
            Connection con = null;
            PreparedStatement stmt = null;
    
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = (Connection) DriverManager.getConnection(host, user, pwd);
            
            String q="INSERT INTO tb_post (`pid`, `pdate`, `ptitle`, `pcontent`, `timestamp`, `isPublished`) "
            + "VALUES (NULL, "
            + "'"+ Tanggal +"', "
            + "'"+ Judul +"', "
            + "'"+ Konten +"', "
            + "CURRENT_TIMESTAMP, '0');";
            
            stmt = (PreparedStatement) con.prepareStatement(q); 
            
            System.out.println(q);
            int i = stmt.executeUpdate(q);

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.sendRedirect("home.xhtml");    
    
    
    }
    public String getJudul(){
        return Judul;
    }
    public String getTanggal(){
        return Tanggal;
    }
    public String getKonten(){
        return Konten;
    }
    public int getId(){
        return Id;
    }
    public void setJudul(String s){
        Judul = s;
    }
    public void setTanggal(String d){
        Tanggal = d;
    }
    public void setKonten(String s){
        Konten = s;
    }
    public void setId(int i){
        Id = i;
    }
}
