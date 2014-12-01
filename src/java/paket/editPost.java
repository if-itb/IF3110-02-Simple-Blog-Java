/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paket;

<<<<<<< HEAD
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

=======
>>>>>>> parent of 4c157a1... tambah edit post
/**
 *
 * @author Mario
 */
public class editPost {

<<<<<<< HEAD
    private String tanggal, konten, judul;
    private String username, idpost;

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    
=======
>>>>>>> parent of 4c157a1... tambah edit post
    /**
     * Creates a new instance of editPost
     */
    public editPost() {
    }
    
<<<<<<< HEAD
    public void ambilTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.tanggal = dateFormat.format(date);
    }
    
    public void getPost(){
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        String peta = requestMap.toString();
        
        Pattern pattern = Pattern.compile( "id=(\\d+)" );
        Matcher matcher = pattern.matcher( peta );
        if ( matcher.find() )
            idpost = matcher.group( 1 );
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT * FROM `post` WHERE id_post='" +idpost +"'";
            ResultSet rs = stmnt.executeQuery(sqlStr);
            
            if (rs.next()){
                tanggal = rs.getString("tanggal_post");
                judul = rs.getString("judul");
                konten = rs.getString("konten");
                username = rs.getString("usrname");
            }
            
        } catch (SQLException e){
            
        }
    }
    
    public void saveEditPost() throws IOException {
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
        
        System.out.print(idpost);
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "UPDATE post SET judul='" +judul +"', konten='" +konten +"', tanggal_post='" +tanggal +"' WHERE id_post=" +idpost;
            stmnt.executeUpdate(sqlStr);
                        
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            
        } catch (SQLException e){
            
        }
    }
    
=======
>>>>>>> parent of 4c157a1... tambah edit post
}
