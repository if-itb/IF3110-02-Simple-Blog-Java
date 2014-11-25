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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class post_bean {
    private List<Post> items;
    
    public List<Post> dbData() throws SQLException, ClassNotFoundException{
        Connection con;
        Statement ps;
        ResultSet rs;
        String SQL_Str;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "Select * from post";
        rs = ps.executeQuery(SQL_Str);
        List<Post> list = new ArrayList<>();
        while(rs.next()) {
            Post p = new Post();
            p.setId_post(rs.getInt(1));
            p.setJudul(rs.getString(2));
            p.setTanggal(rs.getString(3));
            p.setKonten(rs.getString(4));
            p.setStatus(rs.getBoolean(5));
            list.add(p);
        }
        con.close();
        return list;
    }
    
}
