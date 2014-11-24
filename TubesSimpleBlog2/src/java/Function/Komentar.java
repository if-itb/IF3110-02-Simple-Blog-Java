/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author ASUS
 */
@ManagedBean(name = "komentar", eager = true)
@SessionScoped
public class Komentar {
    int commentid;   
    String nama;
    String email;
    String komentar;
    String tanggal;
    private static final DateFormat dateFormat = new SimpleDateFormat ("yyyy/mm/dd");
            
    public int getCommentid(){
    return commentid;}
    
    public String getNama(){
    return nama;}
    
    public String getEmail(){
    return email;}
    
    public String getKomentar(){
    return komentar;}
    
    public String getTanggal(){
    return tanggal;}
    
    public void setCommentid(int x){
    this.commentid=x;}
    
    public void setNama(String n){
    this.nama=n;}
    
    public void setEmail(String e){
    this.email=e;}
    
    public void setKomentar(String k){
    this.komentar=k;}
    
    public void setTanggal(String d){
    this.tanggal=d;}
    
    public String addComment(){
     String url = "jdbc:mysql://localhost:3306/datapost";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root"; 
	String password = "";
	 try {
		  Class.forName(driver).newInstance();
		  Connection conn = DriverManager.getConnection(url,userName,password);
		  Statement st = conn.createStatement();
                  java.util.Date today = new java.util.Date();
                  tanggal= dateFormat.format(today.getTime());
                  int res= st.executeUpdate("insert into komentar (`Nama`,`Email`,`Komentar`,`postid`) value ('"+this.nama+"','"+this.email+"','"+this.komentar+"',29)");
                  //kalau masukin pake execute update
                  conn.close();
		  } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		  }
    return "";

       
    }
}
