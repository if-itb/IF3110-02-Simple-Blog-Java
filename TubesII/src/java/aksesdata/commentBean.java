/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aksesdata;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@ViewScoped
public class commentBean implements Serializable{

    private int id_post;
    private String nama;
    private String email;
    private String komentar;
    
    @Resource(name="jdbc/test")
    private DataSource ds;
    
    public commentBean() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/test");
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public List<Komentar> getCommentList(int id) throws SQLException {
        
        if(ds==null)
            throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        
        setId_post(id);
        PreparedStatement ps = con.prepareStatement("select nama, tanggal, komentar from komentar where id_post="+id);
        ResultSet result = ps.executeQuery();
        List<Komentar> list = new ArrayList<Komentar>();
        
        while(result.next()) {
           Komentar comment = new Komentar();
            
            comment.setNama(result.getString("nama"));
            comment.setTanggal(result.getDate("tanggal"));
            comment.setKomentar(result.getString("komentar"));
            list.add(comment);
        }
        con.close();
        return list;
    }
    
    public void guestKomentar() throws Exception{
        
        if(ds==null)
        throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date date = cal.getTime();
        java.sql.Date now = new Date(date.getTime());
         HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
        PreparedStatement ps = con.prepareStatement("insert into komentar(id_post,nama,tanggal,email,komentar) values("+getId_post()+",'"+nama+"', '"+now+"', '"+email+"', '"+komentar+"')");
        ps.executeUpdate();
        httpServletResponse.sendRedirect("guest_view.xhtml?id="+getId_post());
    }
    
    public void memberKomentar(String u_name, String u_email) throws Exception {
       
        if(ds==null)
        throw new SQLException("Can't get data source");
        
        Connection con = ds.getConnection();
        if(con==null)
            throw new SQLException("Can't get database connection");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date date = cal.getTime();
        java.sql.Date now = new Date(date.getTime());
        HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
        //String u_name = FacesContext.getCurrentInstance().getExternalContext().
        PreparedStatement ps = con.prepareStatement("insert into komentar(id_post,nama,tanggal,email,komentar) values("+getId_post()+",'"+u_name+"', '"+now+"', '"+u_email+"', '"+komentar+"')");
        ps.executeUpdate();
        httpServletResponse.sendRedirect("view_post.xhtml?id="+getId_post());
    }
}

