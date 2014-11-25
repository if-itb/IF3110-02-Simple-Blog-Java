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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
    
    public void coba(){
	tanggal = komentar + " " + nama + " " + email;}
    
    public void addComment(int pid){
     String url = "jdbc:mysql://localhost:3306/datapost";
	   String driver = "com.mysql.jdbc.Driver";
	   String userName = "root"; 
	   String password = "";
		try {
		   Class.forName(driver).newInstance();
		//   Class.forName(driver);
		   Connection conn = DriverManager.getConnection(url,userName,password);
		   
		   Date today = new Date();
		   Timestamp date = new Timestamp(today.getTime());
		   String insertToDB = "insert into komentar (`Nama`,`Email`,`Komentar`,`postid`,`tanggal`) value (?,?,?,?,?)";
		   PreparedStatement preparedStatement = conn.prepareStatement(insertToDB);
		   preparedStatement.setString(1, this.nama);
		   preparedStatement.setString(2, this.email);
		   preparedStatement.setString(3, this.komentar);
		   preparedStatement.setInt(4,pid);
		   preparedStatement.setTimestamp(5, date);
		   preparedStatement.executeUpdate();
		   
		   
		 /*  Statement st = conn.createStatement();
		   java.util.Date today = new java.util.Date();
		   tanggal= dateFormat.format(today.getTime());
		  // st.executeUpdate("insert into komentar (`Nama`,`Email`,`Komentar`,`postid`) value ('"+this.nama+"','"+this.email+"','"+this.komentar+"',29)");
		   st.executeUpdate("insert into komentar (`Nama`,`Email`,`Komentar`,`postid`) value ('tesnama','tesemail','teskomentar',49)");
		 */  //kalau masukin pake execute update
		   conn.close();
                   nama="";
                   email="";
                   komentar="";
                   tanggal="";
	   } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
	   }
		

       
    }
}
