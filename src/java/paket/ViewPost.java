/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paket;

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
 * @author Mario
 */
public class ViewPost {

    private String idpost, judul, konten, tanggal;

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    /**
     * Creates a new instance of ViewPost
     */
    public ViewPost() {
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
            
            String sqlStr = "SELECT * FROM `post` WHERE id_post='" +idpost +"'";
            ResultSet rs = stmnt.executeQuery(sqlStr);
            
            if (rs.next()){
                tanggal = rs.getString("tanggal_post");
                judul = rs.getString("judul");
                konten = rs.getString("konten");
            }
            
        } catch (SQLException e){
            
        }
    }
    
}
