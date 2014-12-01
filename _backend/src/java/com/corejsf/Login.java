/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corejsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "login")
@RequestScoped
public class Login {
    private String email;
    private String password;
    private String role;
    private int id;
    private String dbEmail;
    private String dbPassword;
    private String dbRole;
    private int dbId;
    
    public Login(){
        checkCookie();
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRole(){
        return role;
    }
    
    public String getDbRole(){
        return dbRole;
    }
    
    public int getId(){
        return id;
    }
    
    public String getDbEmail(){
        return dbEmail;
    }
    
    public String getDbPassword(){
        return dbPassword;
    }
    
    public int getDbId(){
        return dbId;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public void setDbEmail(String dbEmail){
        this.dbEmail = dbEmail;
    }
    
    public void setDbPassword(String dbPassword){
        this.dbPassword = dbPassword;
    }
    
    public void setDbRole(String dbRole){
        this.dbRole = dbRole;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setDbId(int dbId){
        this.dbId = dbId;
    }
    
    public void fetchDb(String email_){
        if(email_ != null){
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/simpleblog";
            String user = "root";
            String password = "";
            String driver = "com.mysql.jdbc.Driver";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(url, user, password);
                Statement sm = con.createStatement();
                String sql = "SELECT id,Email,Password,Role FROM member WHERE Email = '" + email_ + "'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                rs.next();
                dbEmail = rs.getString("Email");
                dbPassword = rs.getString("Password");
                dbRole = rs.getString("Role");
                dbId = rs.getInt("id");
                role = rs.getString("Role");
                id = rs.getInt("id");
                // close connection
                con.close();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
            finally{}
        }
    }
    
    public String startLogin(){
        fetchDb(email);
        if((email.equals(dbEmail)) && (password.equals(dbPassword))){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            
            Cookie cEmail = new Cookie("cEmail", email);
            Cookie cPassword = new Cookie("cPassword", password);
            Cookie cRole = new Cookie("cRole", role);
            Cookie cId = new Cookie("cId", Integer.toString(id));
            
            cEmail.setMaxAge(86400);
            cPassword.setMaxAge(86400);
            cRole.setMaxAge(86400);
            cId.setMaxAge(86400);
		  
            ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cEmail);
            ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cPassword);
            ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cRole);
            ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cId);
            return "success";
        }
        else{
            return "failure";
        }
    }
    
    public String startLogout(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        email = "";
        password = "";
        role = "";
        String id_ = "";
        
        Cookie cEmail = new Cookie("cEmail", email);
        Cookie cPassword = new Cookie("cPassword", password);
        Cookie cRole = new Cookie("cRole", role);
        Cookie cId = new Cookie("cId", id_);
        
        cEmail.setMaxAge(0);
        cPassword.setMaxAge(0);
        cRole.setMaxAge(0);
        cId.setMaxAge(0);
		  
        ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cEmail);
        ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cPassword);
        ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cRole);
        ((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cId);
        
        return "logout";
    }
    
    public void checkCookie(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookieName = null;
        Cookie cookie[] = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
        if(cookie != null && cookie.length > 0){
            for(int i = 0; i<cookie.length; i++){
                cookieName = cookie[i].getName();
                if(cookieName.equals("cEmail")){
                    email = cookie[i].getValue();
                }
                else if(cookieName.equals("cPassword")){
                    password = cookie[i].getValue();
                }
                else if(cookieName.equals("cRole")){
                    role = cookie[i].getValue();
                }
            }
        }
        else{
            System.out.println("Cannot find any cookie");
        }
    }
}
