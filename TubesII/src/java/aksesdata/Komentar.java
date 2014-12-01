/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aksesdata;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean(name="komentar")
@SessionScoped
public class Komentar {
    
    private String nama;
    private String email;
    private String komentar;
    private Date tanggal;
    @Resource(name="jdbc/test")
    private DataSource ds;
    
    public Komentar() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/test");
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
    public String getNama() {
        return nama;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Date getTanggal() {
        return tanggal;
    }
    
    public String getKomentar() {
        return komentar;
    }
    
    public void setNama(String _nama) {
        nama = _nama;
    }
    
    public void setEmail(String _email) {
        email = _email;
    }
    
    public void setTanggal(Date _tanggal) {
        tanggal = _tanggal;
    }
    
    public void setKomentar(String _komentar) {
        komentar = _komentar;
    }
    
    public void updateKomentar(int id) throws SQLException{
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        Komentar c = new Komentar();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date date = cal.getTime();
        java.sql.Date now = new Date(date.getTime());
        
        PreparedStatement ps = con.prepareStatement("insert into komentar(id_post,nama,tanggal,email,komentar) values("+id+",'"+nama+"', '"+now+"', '"+email+"', '"+komentar+"')");
        ps.executeUpdate();
    }
    
    public String getNewComment() {
        
        String comment;
        
        comment = "<li class='art-list-item'>"+    
                    "<div class='art-list-item-title-and-time'>"+
                        "<h2 class='art-list-title'><a href='post.html'>"+nama+"</a></h2>"+
                        "<div class='art-list-time'>"+tanggal+"</div>"+
                    "</div>"+
                    "<p>"+komentar+"</p>"+                        
                "</li>";
        return comment;
    }                    
}
