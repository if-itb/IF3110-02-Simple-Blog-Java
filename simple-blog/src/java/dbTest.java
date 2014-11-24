/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Luqman
 */
@ManagedBean
@SessionScoped
public class dbTest {
    private DataSource ds;
    /**
     * Creates a new instance of dbTest
     */
    public dbTest() {
    }
    public String test() throws SQLException, NamingException{
 
	  //get database connection
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/simpleBlogDb");

        Connection conn = ds.getConnection();
        PreparedStatement ps 
            = conn.prepareStatement(
                "SELECT * FROM user"); 
	ResultSet result =  ps.executeQuery();
        result.next();
        String nama = result.getString("name");
                
        conn.close();
        return nama;
    }
}
