/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import java.util.Date;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anggi
 */
@ManagedBean(name = "listPost", eager = true)
@RequestScoped
public class ListPost {
	private ArrayList<Post> arrPostPublished;
	private ArrayList<Post> arrPostUnpublished;
	private ArrayList<Post> arrPostDeleted;
	
	public ListPost(){
		arrPostPublished = new ArrayList<>();
		arrPostUnpublished = new ArrayList<>();
                arrPostDeleted = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/datapost";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url,userName,password);
			Statement st = conn.createStatement();
			ResultSet res= st.executeQuery("Select * from posts");
			Post pos = null;
			while(res.next()){ 
				pos = new Post();
				pos.setIDPost(res.getInt("PID"));
				pos.setTitle(res.getString("Judul"));
				pos.setDate(res.getDate("Tanggal"));                
				pos.setContent(res.getString("Konten"));                
				pos.setStatus(res.getString("Status"));
				pos.setDeletedStatus(res.getString("Delete"));
				if ((pos.getStatus().equals("published")) && (pos.getDeletedStatus().equals("notdeleted"))){
                                    arrPostPublished.add(pos);
				} else if ((pos.getStatus().equals("unpublished")) && (pos.getDeletedStatus().equals("notdeleted"))){
                                    arrPostUnpublished.add(pos);
				} else {
                                    arrPostDeleted.add(pos);
                                }
			}
			conn.close();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
		}
	}
	
	public ArrayList<Post> getArrPostPublished(){
		return arrPostPublished;
	}
	
	public ArrayList<Post> getArrPostUnpublished(){
		return arrPostUnpublished;
	}
        
        public ArrayList<Post> getArrPostDeleted(){
		return arrPostDeleted;
	}
}
