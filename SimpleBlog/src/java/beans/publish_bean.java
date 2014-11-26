/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author asus
 */
@ManagedBean
@SessionScoped
public class publish_bean {
    int id;
    Connection con;
    Statement ps;
    String SQL_Str;
    /**
     * Creates a new instance of publish_bean
     */
    public publish_bean() {
    }
    public void setId(int id) {
        this.id = id;
    }
    public String changeStatus(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "UPDATE post SET status=true WHERE id_post=" + id;
        ps.executeUpdate(SQL_Str);
        con.close();
        
        return "publish";
    }
}
