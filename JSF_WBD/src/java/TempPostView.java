/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@ManagedBean
@SessionScoped
public class TempPostView {
    public int Pid;
    public String Judul = "";
    public String Tanggal = "";
    public String Konten = "";
    /**
     * Creates a new instance of TempPostView
     */
    public TempPostView() {
    }
    public void viewPost(int pid) throws ClassNotFoundException, SQLException, IOException{
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
        System.out.println("Judul : " + getJudul());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("view_post.xhtml");
        
    }

    public int getPid() {
        return Pid;
    }

    public String getJudul() {
        return Judul;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public String getKonten() {
        return Konten;
    }

    public void setPid(int Pid) {
        this.Pid = Pid;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }

    public void setKonten(String Konten) {
        this.Konten = Konten;
    }
    
   
}
