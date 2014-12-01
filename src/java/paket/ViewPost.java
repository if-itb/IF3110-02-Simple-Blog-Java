/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mario
 */
public class ViewPost {

    private String idpost, judul, konten, tanggal, status, del_stat, tgl_komen;
    private String komen, nama, email;
    private ResultSet rs;
    private ArrayList<ViewPost> listpost = new ArrayList<ViewPost>();
    private ArrayList<ViewPost> listkomen = new ArrayList<ViewPost>();

    public String getTgl_komen() {
        return tgl_komen;
    }

    public void setTgl_komen(String tgl_komen) {
        this.tgl_komen = tgl_komen;
    }
    
    public ArrayList<ViewPost> getListpost() {
        return listpost;
    }

    public void setListpost(ArrayList<ViewPost> listpost) {
        this.listpost = listpost;
    }

    public ArrayList<ViewPost> getListkomen() {
        return listkomen;
    }

    public void setListkomen(ArrayList<ViewPost> listkomen) {
        this.listkomen = listkomen;
    }

    public String getDel_stat() {
        return del_stat;
    }

    public void setDel_stat(String del_stat) {
        this.del_stat = del_stat;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
    
    public void ambilPostdanComment(){
        clear();
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
            rs = stmnt.executeQuery(sqlStr);
            
            if (rs.next()){
                tanggal = rs.getString("tanggal_post");
                judul = rs.getString("judul");
                konten = rs.getString("konten");
            }
            
            sqlStr = "SELECT * FROM `komen` WHERE id_post=" +idpost;
            rs = stmnt.executeQuery(sqlStr);
            
            while (rs.next()){
                ViewPost view = new ViewPost();
                view.setKomen(rs.getString("isi"));
                view.setNama(rs.getString("nama"));
                view.setTgl_komen(rs.getString("tangggal_kom"));
                listkomen.add(view);
            }
            
        } catch (SQLException e){
            
        }
    }
    
    public ArrayList<ViewPost> ambilPostList(){
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
                       
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "SELECT * FROM post";
            ResultSet rs = stmnt.executeQuery(sqlStr);
            
            while (rs.next()){
                ViewPost view = new ViewPost();
                view.setIdpost(rs.getString("id_post"));
                view.setJudul(rs.getString("judul"));
                view.setKonten(rs.getString("konten"));
                view.setTanggal(rs.getString("tanggal_post"));
                view.setStatus(rs.getString("status"));
                view.setDel_stat(rs.getString("del_stat"));
                if ("published".equals(view.getStatus().toString())&&("0".equals(view.getDel_stat().toString()))){
                    listpost.add(view);
                } 
            }
            
        } catch (SQLException e){
            
        }
        return listpost;
    }
    
    public void executeComment() throws IOException{
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
        
        System.out.print("commentid"+idpost);
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "INSERT INTO `komen`(`nama`, `email`, `isi`, `id_post`) VALUE('" +nama +"', '" +email +"', '" +komen +"', '" +idpost +"')";
            stmnt.executeUpdate(sqlStr);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("post.xhtml?id="+idpost);
                                   
        } catch (SQLException e){
            
        }
    }
    
    public void clear(){
        listpost.clear();
        listkomen.clear();
    }
    
    public void executeSoftDelete() throws IOException{
        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
        String uName = "root";
        String pass = "";
        
        Connection conn = null;
        Statement stmnt = null;
        
        System.out.print("commentid"+idpost);
        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Unable to load Driver");
            }
            conn = DriverManager.getConnection(dbURL, uName, pass);
            stmnt = conn.createStatement();
            
            String sqlStr = "UPDATE post SET del_stat=1 WHERE id_post="+idpost;
            stmnt.executeUpdate(sqlStr);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                                   
        } catch (SQLException e){
            
        }
    }
    
}
