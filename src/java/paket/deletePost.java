/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;

/**
 *
 * @author TOSHIBA
 */
public class deletePost {
    
    private String idpost, judul, konten, tanggal, status, del_stat;

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getDel_stat() {
        return del_stat;
    }

    public void setDel_stat(String del_stat) {
        this.del_stat = del_stat;
    }

    /**
     * Creates a new instance of deletePost
     */
    public void deletePost() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
//        String dbURL = "jdbc:mysql://localhost:3306/simple_blog";
//        String uName = "root";
//        String pass = "";
//        
//        Connection conn = null;
//        Statement stmnt = null;
//                       
//        try {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                System.out.println("Unable to load Driver");
//            }
//            conn = DriverManager.getConnection(dbURL, uName, pass);
//            stmnt = conn.createStatement();
//            
//            String sqlStr = "UPDATE post SET del_stat=1 WHERE id_post=" +idpost;
//            ResultSet rs = stmnt.executeQuery(sqlStr);
//            
//        } catch (SQLException e){
//            
//        }
    }
}
