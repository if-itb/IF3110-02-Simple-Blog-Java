/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aksesdata;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean(name="newpost")
@SessionScoped

public class newpost implements Serializable {
    private String judul;
    private String tanggal;
    private String isi;
    
    private DataSource ds;
    public newpost() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/test");
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }

    public String getJudul() {
        return judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getIsi() {
        return isi;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
    
    public void insertNewPost() throws SQLException, IOException{
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("insert into blog(judul, isi, tanggal, status) values ('"+judul+"', '"+isi+"', '"+tanggal+"', 0)");
        ps.executeUpdate();
        ExternalContext mas = FacesContext.getCurrentInstance().getExternalContext();
        mas.redirect("daftar_post.xhtml");
    }      
    
    public void deletePost(int id) throws SQLException, IOException{
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("delete from blog where id='"+id+"'");
        ps.executeUpdate();
        ExternalContext mas = FacesContext.getCurrentInstance().getExternalContext();
        mas.redirect("daftar_post.xhtml");
    }
    
     public void insertEditedPost(int ID) throws Exception{
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
       
        PreparedStatement ps = con.prepareStatement("update blog set judul='judul', isi='isi', tanggal='tanggal' where id="+ID);
        ps.executeUpdate();
        ExternalContext mas = FacesContext.getCurrentInstance().getExternalContext();
        mas.redirect("daftar_post.xhtml");
    }
}
