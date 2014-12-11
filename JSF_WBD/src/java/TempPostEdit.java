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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 
 * @author ASUS
 */
@ManagedBean(name="tempPostEdit", eager = true)
@SessionScoped
public class TempPostEdit {
    public int Pid;
    public String Judul = "";
    public String Tanggal = "";
    public String Konten = "";

    public TempPostEdit() {        
    }
    public void editPost(int pid) throws ClassNotFoundException, SQLException, IOException{
        String host = "jdbc:mysql://localhost:3306/simple_blog_java?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "";
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = (Connection) DriverManager.getConnection(host, user, pwd);
        Statement stmt = (Statement) con.createStatement(); 
        String q = "SELECT * FROM tb_post WHERE pid=" + pid + ";";
        System.out.println(q);
        ResultSet rs = stmt.executeQuery(q);
        if(rs.next()){
            Judul = rs.getString("ptitle");
            Konten = rs.getString("pcontent");
            Pid = rs.getInt("pid");
            Tanggal = rs.getDate("pdate").toString();

        }else{
            Judul = "Ga ada judul";
            Konten = "Ga ada konten";
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("edit_post.xhtml");
    
    }
    public void submitEdit() throws ClassNotFoundException, SQLException, IOException{
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
            
            String q="UPDATE tb_post SET "
                    + "pdate = '"+ Tanggal 
                    + "', ptitle = '" + Judul
                    + "', pcontent = '" + Konten
                    + "' WHERE pid = " + Pid + ";";
            
            System.out.println(q);
            
            stmt = (PreparedStatement) con.prepareStatement(q); 
            
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
    public int getPid(){
        return Pid;
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
    public void setPid(int i){
        Pid = i;
    }
}
