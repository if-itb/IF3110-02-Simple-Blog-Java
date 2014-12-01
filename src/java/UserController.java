
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stanley
 */
@ManagedBean(name="usercontroller")
@RequestScoped
public class UserController {
    private ArrayList<User> users;
   
    private Connection connect;
    private Statement statement;
    private ResultSet resultset;
    private PreparedStatement preparedStatement;
    
    private String cookietype;
    private int cookieId;
    private String currentuser;
    
    private String url = "jdbc:mysql://localhost/";
    private String db = "wbdsimpleblog";
    private String dbuser = "root";
    private String dbpw = "";
    
    
    public ArrayList<User> getUsers() throws Exception{
        int nid;
        String nusername,npassword,nnama,nemail,ntipe;
        ArrayList<User> nusers=new ArrayList<>();
        User nuser;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url+db,dbuser,dbpw);
            statement = connect.createStatement();  
            resultset = statement.executeQuery("select * from user");
        }
        catch(Exception e){
        }
        
        while (resultset.next()){
            nid=resultset.getInt("id");
            nusername = resultset.getString("username");
            npassword = resultset.getString("password");
            ntipe = resultset.getString("tipe");
            nnama = resultset.getString("nama");
            nemail= resultset.getString("email");
            nuser = new User(nid,nusername,npassword,nnama,nemail,ntipe);
            nusers.add(nuser);
        }
        
        return nusers;
    } 
            
    public void addUsers(User U) throws Exception{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        if(!U.isNull()){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver passed");
                connect = DriverManager.getConnection(url+db,dbuser,dbpw);
                        preparedStatement=connect.prepareStatement("INSERT INTO `user`(`id`,`username`,`password`,`nama`,`email`,`tipe`) "
                    + "VALUES (NULL,'"+U.getUsername()+"','"+U.getPassword()+"','"
                    +U.getNama()+"','"+U.getEmail()+"','"+U.getTipe()+"');");
                preparedStatement.executeUpdate();   
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).sendRedirect("manage.xhtml");
            }
            catch(Exception e){

            }
        }

    }
    
    public User getSpecifiedUser(int userid) {        
        User user = new User();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url+db,dbuser,dbpw);
            statement=connect.createStatement();
            resultset = statement.executeQuery("select * from user where `id`='"+userid+"';");           
            if(resultset.next()){
            user.setId(resultset.getInt("id"));
            user.setUsername(resultset.getString("username"));
            user.setPassword(resultset.getString("password"));
            user.setNama(resultset.getString("nama"));
            user.setEmail(resultset.getString("email"));
            user.setTipe(resultset.getString("tipe"));
            }
        }
        catch (Exception e){
        }
        return user;
    }
    
    public void editUsers(User U) throws Exception{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url+db,dbuser,dbpw);
            preparedStatement=connect.prepareStatement("UPDATE `user` SET `username`='"+U.getUsername()+"', `password`='"+U.getPassword()+"', "
                + "`nama`='"+U.getNama()+"', `email`='"+U.getEmail()+"', `tipe`='"+U.getTipe()+"' "
                + "WHERE `id`="+U.getId()+";");
            preparedStatement.executeUpdate();
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).sendRedirect("manage.xhtml");
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    
    public void deleteUsers(int userid) throws Exception{
        System.out.println(userid);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url+db,dbuser,dbpw);
            preparedStatement=connect.prepareStatement("DELETE from user where id="+userid+";");
            preparedStatement.executeUpdate();
            System.out.println(userid + " deleted");
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).sendRedirect("manage.xhtml");
            connect.close();
        }
        catch(Exception e){
            
        }
    }
    
    public String checkCookie() throws IOException{
        FacesContext facesContext=FacesContext.getCurrentInstance();
        String cookieuser=null;
        String cookiepass=null;
        String cookietipe=null;
        String retval=null;
        Cookie[] cookies=((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
        
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equalsIgnoreCase("currentuser")){
                cookieuser=cookies[i].getValue();
                System.out.println("cookieuser = "+cookieuser);
            }
            if(cookies[i].getName().equalsIgnoreCase("currentpass")){
                cookiepass=cookies[i].getValue();
                System.out.println("cookiepass = "+cookiepass);
            }
            if(cookies[i].getName().equalsIgnoreCase("currenttipe")){
                cookietipe=cookies[i].getValue();
                System.out.println("cookietipe = "+cookietipe);
            }
        }
        
        if(cookieuser!=null && cookiepass!=null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection(url+db,dbuser,dbpw);
                statement = connect.createStatement();
                resultset = statement.executeQuery("select * from user where username='"+cookieuser+"' and password='"+cookiepass+"';");
            }
            catch(Exception e){
                System.out.println("MASUK EXCEPTION");
            }
        }
        retval="blog.xhtml";
        System.out.println(retval);
        return retval;
    }
    
    public String getCookieUser(){
        String cookieuser=null;
        String retval=null;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie[] cookies=((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
        
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equalsIgnoreCase("currentuser")){
                cookieuser=cookies[i].getValue();
                System.out.println("iduser = "+cookieuser);
            }
        }
        if(cookieuser!=null){
            retval=cookieuser;
        }
        else{
            retval="Guest";
        }
        return retval;
    }
    
    public String getCookieId(){
        String cookieid=null;
        String retval=null;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie[] cookies=((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
        
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equalsIgnoreCase("currentid")){
                cookieid=cookies[i].getValue();
                System.out.println("iduser = "+cookieid);
            }
        }
        if(cookieid!=null){
            retval=cookieid;
        }
        return retval;
    }
    
    public String getCookieType(){
        String cookietipe=null;
        String retval=null;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie[] cookies=((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
        
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equalsIgnoreCase("currenttipe")){
                cookietipe=cookies[i].getValue();
                System.out.println("cookieuser = "+cookietipe);
            }
        }
        if(cookietipe!=null){
            retval=cookietipe;
        }
        else{
            retval="Guest";
        }
        return retval;
    }
}
