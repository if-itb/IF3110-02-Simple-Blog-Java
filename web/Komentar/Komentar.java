/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Komentar;
import static java.lang.System.out;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author adwisatya
 */
public class Komentar {
	private static String Driver = "com.mysql.jdbc.Driver";
	private static String DbUser = "root";
	private static String DbPass = "";
	private static String DbName = "Tubes2WBD";
	private static String DbLoc1 = "jdbc:mysql://localhost:3306/";
	private static String DbLoc2 = DbLoc1+DbName;
	
	public static void NewKomentar(String Parent, String Name, String Email, String Content) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			String query = "INSERT INTO `comment` (`Time`,`Parent`,`Name`,`Email`,`Content`) VALUES (?,?,?,?,?);";
			ps=conn.prepareStatement(query);
			//ps.setString(1,ID);
			ps.setString(1,sdf.format(date));
			ps.setString(2,Parent);
			ps.setString(3,Name);
			ps.setString(4,Email);
			ps.setString(5,Content);
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

}
