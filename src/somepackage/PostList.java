package somepackage;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "postList", eager = true)
@SessionScoped
public class PostList implements Serializable {

	public Connection getConnection() {
		Connection connection = null;

		String url = "jbdc:mysql://localhost:3306/wbd_db";
		String user = "root";
		String password = "";

		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection completed");
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}
		return connection;
	}
	

}
