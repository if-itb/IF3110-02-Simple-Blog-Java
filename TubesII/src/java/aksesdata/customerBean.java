/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aksesdata;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Lenovo
 */
@ManagedBean(name="customerBean")
@SessionScoped

public class customerBean implements Serializable{
    @Resource(name="jdbc/test")
    private DataSource ds;
    public customerBean() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/test");
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
    
    public List<Blog> getCustomerList() throws SQLException {
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("select id, judul, isi, tanggal from blog where status=1");
        ResultSet result = ps.executeQuery();
        List<Blog> list = new ArrayList<Blog>();
        
        while(result.next()) {
            Blog blog = new Blog();
            
            blog.setId(result.getInt("id"));
            blog.setJudul(result.getString("judul"));
            blog.setIsi(result.getString("isi"));
            blog.setTanggal(result.getString("tanggal"));
            list.add(blog);
        }
        con.close();
        return list;
    }
    
    public List<Blog> getUnpublished() throws SQLException {
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("select id, judul, isi, tanggal from blog where status=0");
        ResultSet result = ps.executeQuery();
        List<Blog> list = new ArrayList<Blog>();
        
        while(result.next()) {
            Blog blog = new Blog();
            
            blog.setId(result.getInt("id"));
            blog.setJudul(result.getString("judul"));
            blog.setIsi(result.getString("isi"));
            blog.setTanggal(result.getString("tanggal"));
            list.add(blog);
        }
        con.close();
        return list;
    }
    
    public List<Blog> getPost(int id) throws SQLException {
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        PreparedStatement ps = con.prepareStatement("select id, judul, isi, tanggal from blog where id="+id);
        ResultSet result = ps.executeQuery();
        List<Blog> list = new ArrayList<Blog>();
        
        while(result.next()) {
            Blog blog = new Blog();
            
            blog.setId(result.getInt("id"));
            blog.setJudul(result.getString("judul"));
            blog.setIsi(result.getString("isi"));
            blog.setTanggal(result.getString("tanggal"));
            list.add(blog);
        }
        con.close();
        return list;
    }
    
    public void setKomentar() throws SQLException {
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        Komentar comment = new Komentar();
        PreparedStatement ps = con.prepareStatement("");
        ResultSet result = ps.executeQuery();
    }
    
    public void setPublished(int id) throws SQLException {
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        Komentar comment = new Komentar();
        PreparedStatement ps = con.prepareStatement("update blog set status=1 where id='"+id+"'");
        ps.executeUpdate();
    }
    
}
