package lingga;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jelink
 */
@ManagedBean(name="user",eager=true)
@SessionScoped
public class User {
    private int userid;
    private String name;
    private String password;
    private String output_msg;
    private String email;
    private int type;
    private boolean loggedon=false;
    private User saved;
    
    public User(){
	
    }

    public User(User p) {
	this.userid = p.userid;
	this.name = p.name;
	this.password = p.password;
	this.email = p.email;
	this.type = p.type;
	this.loggedon = p.loggedon;
	saved = new User();
    }
    
    public void copy(User p) {
	this.userid = p.userid;
	this.name = p.name;
	this.password = p.password;
	this.email = p.email;
	this.type = p.type;
	this.loggedon = p.loggedon;
	saved = new User();
    }
    
    public User(int userid, String name, String password, String email, int type) {
	this.userid = userid;
	this.name = name;
	this.password = password;
	this.email = email;
	this.type = type;
	this.loggedon = false;
	this.output_msg = "lala";
	saved = new User();
    }
    
    public void copyToSaved(User p){
	System.out.println("lalalalala " + p.userid);
	saved = new User(p);
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public User getSaved() {
	return saved;
    }

    public void setSaved(User saved) {
	this.saved = saved;
    }

    public int getUserid() {
	return userid;
    }

    public void setUserid(int userid) {
	this.userid = userid;
    }

    public int getType() {
	return type;
    }

    public void setType(int type) {
	this.type = type;
    }

    public boolean isLoggedon() {
	return loggedon;
    }

    public void setLoggedon(boolean loggedon) {
	this.loggedon = loggedon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOutput_msg() {
        return output_msg;
    }

    public void setOutput_msg(String output_msg) {
        this.output_msg = output_msg;
    }
    
    public void loggedOnRedirectToPub() throws IOException {
	if(loggedon){
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/faces/Published_Posts.xhtml");
	}
    }
    
    public void loggedOffRedirectToPub() throws IOException {
	if(!isLoggedon()){
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/faces/Published_Posts.xhtml");
	}
    }
    
    public void loggedOffRedirectToInd() throws IOException {
	if(!loggedon){
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/");
	}
    }
    
    public String addButton(){
	if (!(loggedon && (type==1 || type==3))) return "none";
	else return "block-inline";
    }
    
    public String unPubButton(){
	if(loggedon && (type==2 || type==3)) return "block-inline";
	else return "none";
    }
    
    public String logInButton(){
	if(type==4) return "block-inline";
	else return "none";
    }
    
    public String logOutButton(){
	if(type==4) return "none";
	else return "block-inline";
    }
    
    public String userMgtButton(){
	if(loggedon && type==3) return "block-inline";
	else return "none";
    }
    
    public String logOut(CookieHandler co){
	//user
	name = "";
	password = "";
	loggedon = false;
	type = 4;
	
	//cookie
	co.deleteCookie("if3110_sb_uid");
	
	//action
	return "Login?faces-redirect=true";
    }
    
    public void makeEmptySaved(){
	saved = new User();
	saved.name = "";
	saved.password = "";
	saved.email = "";
	saved.type = 4;
	saved.loggedon = false;
	saved.output_msg = "";
    }
}
 