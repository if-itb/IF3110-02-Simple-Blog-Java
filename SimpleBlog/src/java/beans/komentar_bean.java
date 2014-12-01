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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author asus
 */
@ManagedBean
@RequestScoped
public class komentar_bean {

    /**
     * Creates a new instance of komentar_bean
     */
    private List<Komentar> items;
    Connection con;
    Statement ps;
    ResultSet rs;
    String SQL_Str;
    
    
    public komentar_bean() {
    }
    public List<Komentar> dbData(int id_post) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tubeswbd", "root", "");
        ps = con.createStatement();
        SQL_Str = "Select * from komen where id_post=" + id_post;
        rs = ps.executeQuery(SQL_Str);
        List<Komentar> list = new ArrayList<>();
        while(rs.next()) {
            Komentar p = new Komentar();
            p.setId_kom(rs.getInt(1));
            p.setId_post(rs.getInt(2));
            p.setUsername(rs.getString(3));
            p.setEmail(rs.getString(4));
            p.setWaktu(rs.getString(5));
            p.setKomentar(rs.getString(6));
            list.add(p);
        }
        con.close();
        return list;
    }
}
