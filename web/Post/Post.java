/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Post;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.jasper.*;
/**
 *
 * @author adwisatya
 */
@ManagedBean
@RequestScoped

public class Post {
	private static String Driver = "com.mysql.jdbc.Driver";
	private static String DbUser = "root";
	private static String DbPass = "";
	private static String DbName = "Tubes2WBD";
	private static String DbLoc1 = "jdbc:mysql://localhost:3306/";
	private static String DbLoc2 = DbLoc1+DbName;
	
	public static void Init() throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet resultSet = null;
		try {
			Class.forName(Driver);
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc1,DbUser,DbPass);
			resultSet = conn.getMetaData().getCatalogs();
	        while (resultSet.next()) {
	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(DbName)){
	    			st = conn.createStatement();
	    			st.executeUpdate("CREATE DATABASE IF NOT EXISTS `"+DbName+"` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;");
	    			st.executeUpdate("USE `"+DbName+"`;");
	    			st.executeUpdate(""
	    			+ "CREATE TABLE IF NOT EXISTS `comment` ("
	    			+ "  `ID` bigint(20) NOT NULL AUTO_INCREMENT,"
	    			+ "  `Time` datetime NOT NULL,"
	    			+ "  `Parent` bigint(20) NOT NULL,"
	    			+ "  `Name` varchar(255) NOT NULL,"
	    			+ "  `Email` varchar(255) NOT NULL,"
	    			+ "  `Content` text NOT NULL,"
	    			+ " PRIMARY KEY (`ID`),"
	    			+ "  UNIQUE KEY `ID` (`ID`),"
	    			+ "  KEY `Parent` (`Parent`)"
	    			+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
	    			st.executeUpdate(""
	    			+ "CREATE TABLE IF NOT EXISTS `post` ("
	    			+ "  `ID` bigint(20) NOT NULL AUTO_INCREMENT,"
	    			+ "  `Title` varchar(255) NOT NULL,"
	    			+ "  `Date` date NOT NULL,"
	    			+ "  `Content` text,"
	    			+ " PRIMARY KEY (`ID`),"
	    			+ "  UNIQUE KEY `ID` (`ID`)"
	    			+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
	    			st.executeUpdate(""
	    			+ "ALTER TABLE `comment`"
	    			+ "ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`Parent`) REFERENCES `post` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;");
	            }
	        }
		} catch(Exception e){
			throw e;
		} finally{
			try {
				if (st!=null)st.close();
				if (conn!=null)conn.close();
				if (resultSet!=null)resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void publish(String id) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			String query = "UPDATE `post` SET `Status`=? WHERE ID='"+id+"';";
			ps=conn.prepareStatement(query);
			ps.setString(1,"1");
			if (ps.executeUpdate()==0){
				throw new Exception("Error publish post");
			}
		} catch(Exception e){
			throw e;
		} finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	} 
	public static void TrashPost(String id) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubes2wbd", "root", "");
			String queryUpdate = "UPDATE `post` SET `Status`=? WHERE ID='"+id+"';";
			ps = conn.prepareStatement(queryUpdate);
			ps.setString(1, "3");
			if (ps.executeUpdate()==0){
				throw new Exception("Error trashing post");
			}
		}catch(Exception e){
			
		}finally{
			try{
				ps.close();
				conn.close();
			}catch(SQLException e){
				throw e;
			}
		}
	} 
	public static void RecovPost(String id) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubes2wbd", "root", "");
			String queryUpdate = "UPDATE `post` SET `Status`=? WHERE ID='"+id+"';";
			ps = conn.prepareStatement(queryUpdate);
			ps.setString(1, "0");
			if (ps.executeUpdate()==0){
				throw new Exception("Error recovering post");
			}
		}catch(Exception e){
			
		}finally{
			try{
				ps.close();
				conn.close();
			}catch(SQLException e){
				throw e;
			}
		}
	}
	public static void NewPost(String Judul, Date Tanggal, String Konten,String Owner) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			String query = "INSERT INTO `post` (`ID`,`Title`,`Date`,`Content`,`Owner`,`Status`) VALUES (NULL,?,?,?,?,'0');";
			ps=conn.prepareStatement(query);
			ps.setString(1,Judul);
			SimpleDateFormat tp= new SimpleDateFormat("y-MM-dd");
			ps.setString(2,tp.format(Tanggal));
			ps.setString(3,Konten);
			ps.setString(4,Owner);
			if (ps.executeUpdate()==0){
				throw new Exception("Error adding post");
			}
		} catch(Exception e){
			throw e;
		} finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	
	public static Paket ViewPost(String id) throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Paket res=new Paket();
		try {
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			st=conn.createStatement();
			rs=st.executeQuery("SELECT * FROM post WHERE ID="+id+";");
			if (rs.isBeforeFirst()){
				res.valid=true;
				while(rs.next()){
					res.Judul=rs.getString("Title");
					res.Tanggal=rs.getDate("Date");
					res.Konten=rs.getString("Content");
					res.Owner=rs.getString("Owner");
					res.Status=rs.getInt("Status");
				}
			} else {
				res.valid=false;
			}
			return res;
		} catch(Exception e){
			throw e;
		} finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	
	public static void EditPost(String id, String Judul, Date Tanggal, String Konten) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			String query = "UPDATE `post` SET `Title`=?,`Date`=?,`Content`=? WHERE ID='"+id+"';";
			ps=conn.prepareStatement(query);
			ps.setString(1,Judul);
			SimpleDateFormat tp= new SimpleDateFormat("y-MM-dd");
			ps.setString(2,tp.format(Tanggal));
			ps.setString(3,Konten);
			if (ps.executeUpdate()==0){
				throw new Exception("Error editing post");
			}
		} catch(Exception e){
			throw e;
		} finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	
	public static void DeletePost(String id) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			String query = "DELETE FROM `post` WHERE ID='"+id+"';";
			ps=conn.prepareStatement(query);
			if (ps.executeUpdate()==0){
				throw new Exception("Error deleting post");
			}
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		} finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	
	public static ArrayList<Paket> ViewMany() throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Paket res=new Paket();
		ArrayList<Paket> hsl=new ArrayList<Paket>();
		try {
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			st=conn.createStatement();
			rs=st.executeQuery("SELECT * FROM post ORDER BY Date DESC;");
			if (rs.isBeforeFirst()){
				while(rs.next()){
					res.valid=true;
					res.Judul=rs.getString("Title");
					res.Tanggal=rs.getDate("Date");
					res.Konten=rs.getString("Content");
					res.Owner=rs.getString("Owner");
					res.id=Integer.toString(rs.getInt("ID"));
					res.Status = rs.getInt("Status");
					hsl.add(res);
					res=new Paket();
				}
			} else {
				res.valid=false;
				hsl.add(res);
			}
			return hsl;
		} catch(Exception e){
			throw e;
		} finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
}

