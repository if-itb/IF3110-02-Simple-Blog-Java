/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ASUS
 */
@ManagedBean(name = "listKomentar", eager = true)
@SessionScoped
public class ListKomentar {
    private ArrayList<Komentar> arrKomentar;
    @ManagedProperty(value="#{viewPost.postId}")
    private int postid;
   
    public ArrayList<Komentar> getArrKomentar(){
		return arrKomentar;
	}
	public void setArrKomentar(ArrayList<Komentar> a){
		this.arrKomentar = a;
	}
	
	public void showPage(){
		//postid = id;
		System.out.println("listkomentar="+postid);
		arrKomentar = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/datapost";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "";
		try {
			 Class.forName(driver).newInstance();
			 Connection conn = DriverManager.getConnection(url,userName,password);
			 Statement st = conn.createStatement();
			ResultSet res= st.executeQuery("Select * from komentar where postid="+postid);

			Komentar komen = null;
			while(res.next()){ 
				komen = new Komentar();
				komen.setCommentid(res.getInt("commentid"));
				komen.setNama(res.getString("Nama"));
				komen.setEmail(res.getString("Email"));                
				komen.setKomentar(res.getString("Komentar"));                
				komen.setTanggal(res.getString("Tanggal"));
				System.out.println("komenid="+komen.commentid);
				arrKomentar.add(komen);
			}
			conn.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		}
	}
	
    public int getPostid(){
    return postid;}
    public void setPostid(int p){
    this.postid=p;}
}

