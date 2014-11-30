/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package User;
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
public class User {
	private static String Driver = "com.mysql.jdbc.Driver";
	private static String DbUser = "root";
	private static String DbPass = "";
	private static String DbName = "Tubes2WBD";
	private static String DbLoc1 = "jdbc:mysql://localhost:3306/";
	private static String DbLoc2 = DbLoc1+DbName;
	
	public static void Init() throws Exception{

	}
	
	public static void NewUser(String nama,String username,String password,String email,String status) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			String query = "INSERT INTO `users` (`nama`,`username`,`password`,`email`,`status`,`created`) VALUES (?,?,?,?,?,\'"+sdf.format(date)+"\');";
			ps=conn.prepareStatement(query);
			ps.setString(1,nama);
			SimpleDateFormat tp= new SimpleDateFormat("y-MM-dd");
			ps.setString(2,username);
			ps.setString(3,password);
			ps.setString(4,email);
			ps.setString(5,status);
			if (ps.executeUpdate()==0){
				throw new Exception("Error adding post");
			}
		} catch(SQLException e){
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
	
	public static userPaket ViewUser(String username) throws Exception{
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		userPaket uPaket	=  new userPaket();
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			statement = connection.createStatement();
			String Data = "select * from users WHERE username='"+username+"';";
			rs = statement.executeQuery(Data);
			rs.first();
			uPaket.nama = rs.getString("nama");
			uPaket.username = rs.getString("username");
			uPaket.password = rs.getString("password");
			uPaket.email = rs.getString("email");
			uPaket.status = rs.getString("status");
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw e;
		}

			return uPaket;		

	}
	
	public static void DelUser(String username) throws Exception{
		boolean updateQuery= true ;
		PreparedStatement preparedStatement;
		ResultSet rs = null;
		Class.forName(Driver);
		Connection connection = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
		try{
			preparedStatement = connection.prepareStatement("delete from users where username = ?");
			preparedStatement.setString(1,username);
			updateQuery = preparedStatement.execute();
			if(updateQuery != false){
				out.print("User berhasil dihapus");
				preparedStatement.close();
				connection.close();
			}
		}catch(SQLException ex){
			throw ex;
		}finally{

		}
	}
	
	public static void UpdateUser(String nama,String username,String password,String email,String status) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Class.forName(Driver).newInstance();
			conn = DriverManager.getConnection(DbLoc2,DbUser,DbPass);
			String query = "UPDATE users SET nama=?,username=?,password=?,email=?,status=?,created=? WHERE username='"+username+"';";
			ps=conn.prepareStatement(query);
			ps.setString(1,nama);
			ps.setString(2,username);
			ps.setString(3,password);
			ps.setString(4,email);
			ps.setString(5,status);
			ps.setString(6,sdf.format(date));
			if (ps.executeUpdate()==0){
				throw new Exception("Error adding post");
			}
		} catch(SQLException e){
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
