
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
@ManagedBean(name="user")
@RequestScoped
public class User {
    private int id;
    private String username;
    private String password;
    private String tipe;
    private String nama;


    private String email;
    
    private int authentication = 0;
    private String url = "jdbc:mysql://localhost:3306/";
    private String db = "wbdsimpleblog";
    private String dbuser = "root";
    private String dbpw = "";
    
    private Connection connect;
    private Statement statement;
    private ResultSet resultset;
    
    public User(){
    }
    
    public User(int _id,String _username, String _password, String _tipe, String _nama, String _email){
        id=_id;
        username=_username;
        password=_password;
        tipe=_tipe;
        nama=_nama;
        email=_email;
    }
    
    public void readdb() throws Exception{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url+db,dbuser,dbpw);
            statement = connect.createStatement();
            resultset = statement.executeQuery("select * from user where username='"+username+"' and password='"+password+"'");
        }
        catch(Exception e){
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
    public int getAuthentication() throws Exception{
        authentication = 0;        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url+db,dbuser,dbpw);
            statement = connect.createStatement();
            resultset = statement.executeQuery("select * from user where username='"+username+"' and password='"+password+"'");
        }
        catch(Exception e){
        }        
        if(resultset.next()){
            authentication=1;
            tipe=resultset.getString("tipe");
            nama=resultset.getString("nama");
            email=resultset.getString("email");
            login();
        }
        return authentication;
    }
    
    public void login() throws IOException, SQLException {   
        String cookieuser = null;
        String cookiepass = null;
        String cookietipe = null;
        String cookieid = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        try{
            System.out.println("Logging in");
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url+db,dbuser,dbpw);
            statement = connect.createStatement();
            resultset = statement.executeQuery("select * from user where username='"+username+"' and password='"+password+"';");
            System.out.println("Logged in");
        }
        catch(Exception e){
            System.out.println("MASUK EXCEPTION");
        }
        
        if(resultset.next()){
            tipe=resultset.getString("tipe");
            nama=resultset.getString("nama");
            email=resultset.getString("email");
            id = resultset.getInt("id");
            String nusername = username;
            String npassword = password;
            String ntipe = tipe;
            int nid = id;

            System.out.println(nama);
            System.out.println(nusername);
            Cookie[] cookies=((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
            for(int i=0;i<cookies.length;i++){
                System.out.println("MASUK SINI");
                if(cookies[i].getName().equalsIgnoreCase("currentuser")){
                    cookieuser=cookies[i].getValue();
                    System.out.println("MASUKCOY");
                }
                if(cookies[i].getName().equalsIgnoreCase("currentpass")){
                    cookiepass=cookies[i].getValue();
                }
                if(cookies[i].getName().equalsIgnoreCase("currenttipe")){
                    cookietipe=cookies[i].getValue();
                }
                if(cookies[i].getName().equalsIgnoreCase("currentid")){
                    cookieid=cookies[i].getValue();
                }                
            }

            System.out.println(nusername+" "+npassword+" "+ntipe);
            Cookie userCookie = new Cookie("currentuser",nusername);
            userCookie.setMaxAge(3600);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(userCookie);
            Cookie passCookie = new Cookie("currentpass",npassword);
            passCookie.setMaxAge(3600);
            ((HttpServletResponse) facesContext.getExternalContext()
           .getResponse()).addCookie(passCookie);
            Cookie tipeCookie = new Cookie("currenttipe",ntipe);
            tipeCookie.setMaxAge(3600);
            ((HttpServletResponse) facesContext.getExternalContext()
           .getResponse()).addCookie(tipeCookie);
            Cookie idCookie = new Cookie("currentid",Integer.toString(nid));
            idCookie.setMaxAge(3600);
            ((HttpServletResponse) facesContext.getExternalContext()
           .getResponse()).addCookie(idCookie);
            //if(nusername.equals(cookieuser) && npassword.equals(cookiepass)){    
                ((HttpServletResponse) facesContext.getExternalContext()
                .getResponse()).sendRedirect("cookie.xhtml");
            //}
        }
    }
    
    public void logout() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie loginCookie=null;
        Cookie passCookie=null;
        Cookie tipeCookie=null;
        Cookie idCookie=null;
        String cookieuser=new String("");
        String cookiepass=new String("");
        String cookietipe=new String("");
        String cookieid=new String("");
        Cookie[] cookies=((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equalsIgnoreCase("currentuser")){
                    loginCookie=cookies[i];
                }
                if(cookies[i].getName().equalsIgnoreCase("currentpass")){
                    passCookie=cookies[i];
                }
                if(cookies[i].getName().equalsIgnoreCase("currenttipe")){
                    tipeCookie=cookies[i];
                }
                if(cookies[i].getName().equalsIgnoreCase("currentid")){
                    idCookie=cookies[i];
                }
            }
        }    
        
        
        if(loginCookie!=null && passCookie!=null){
            loginCookie.setMaxAge(0);
            passCookie.setMaxAge(0);
            tipeCookie.setMaxAge(0);
            idCookie.setMaxAge(0);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(loginCookie);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(passCookie);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(tipeCookie);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(idCookie);
        }
        ((HttpServletResponse) facesContext.getExternalContext().getResponse()).sendRedirect("cookie.xhtml");
        id=0;
        username="";
        password="";
        tipe="";
        nama="";
        email="";
    }
    
    public boolean isNull(){
        if(username.isEmpty() || password.isEmpty() || nama.isEmpty() || email.isEmpty() || tipe.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean isOwner(){
        if(tipe.equalsIgnoreCase("Owner")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isAdmin(){
        if(tipe.equalsIgnoreCase("Admin")){
            return true;
        }
        else{
            return false;
        }    
    }
    public boolean isEditor(){
        if(tipe.equalsIgnoreCase("Editor")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isGuest(){
        if(tipe.equalsIgnoreCase("Guest")){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void Copy(User U){
        id=U.id;
        username=U.getUsername();
        password=U.getPassword();
        nama=U.getNama();
        email=U.getEmail();
        tipe=U.getTipe();
    }
    
}
