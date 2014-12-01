/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author user
 */
@ManagedBean(name = "User",eager = true)
@SessionScoped
public class userMgnt{

    /**
     * Creates a new instance of userMgnt
     */
    private String NameUser;
    private String Username;
    private String Password;
    private String Messege;
    private String Role;
    private String Email;
    private Cookie cookie;
    //nanti ditambah-tambah informasi user yang mau ditampilin
    public userMgnt() {
        NameUser="guest";
        Username="guest";
        Password="pass";
        Messege="tidak ada";
        Role="geust";
        Email="geust@simple-blog.com";
        cookie= new Cookie("Username",null);
        cookie.setMaxAge(120);
    }
    public void login(){
        String user="nisa";
        String pass="nisadian";
        if(user.equals(Username)){
            if(pass.equals(Password)){
                NameUser="Nisa";
                Role="Admin";//dari data base
                Email="nisa@simple-blog.com";//data base
                cookie.setValue(Username);
                FacesContext facesContex = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) facesContex.getExternalContext().getResponse();
                response.addCookie(cookie);
            }
            else{
                Messege="Password salah";
            }
        }
        else{
            Messege="Username not found";
        }
    }
    public void Logout(){
        reset();
        Messege="tidak ada";
        cookie.setValue(null);
        cookie.setMaxAge(0);
        FacesContext facesContex = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContex.getExternalContext().getResponse();
        response.addCookie(cookie);
    }
    public void reset(){
        NameUser="guest";
        Username="guest";
        Password="pass";
    }
    
    //validator
    public boolean isLogin(){
        return cookie.getValue()!=null;
    }
    public boolean activeMessege(){
        return !Messege.equals("tidak ada") && !isLogin();
    }
    //getter
    public String getName(){
        return NameUser;
    }
    public String getUsername(){
        return Username;
    }
    public String getPassword(){
        return Password;
    }
    public String getMessege(){
        return Messege;
    }
    public String getRole(){
        return Role;
    }
    public String getEmail(){
        return Email;
    }
    //setter
    public void setName(String S){
        NameUser=S;
    }
    public void setUsername(String S){
        Username=S;
    }
    public void setPassword(String S){
        Password=S;
    }
    public void setMessege(String S){
        Messege=S;
    }
    public Cookie getCookie() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cook = null;

        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0 ) {
            for (int i = 0; i < userCookies.length; i++) {
                if (userCookies[i].getName().equals("Username")) {
                    cook = userCookies[i];
                    return cook;
                }
            }
        }
        return null;
    }
}
    