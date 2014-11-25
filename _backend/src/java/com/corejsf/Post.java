/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "post")
@RequestScoped
public class Post {
    private int id;
    private String status;
    private String judul;
    private String tanggal;
    private String konten;
    
    public Post() {
    }
    
    public int getId(){
        return id;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String getJudul(){
        return judul;
    }
    
    public String getTanggal(){
        return tanggal;
    }
    
    public String getKonten(){
        return konten;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setJudul(String judul){
        this.judul = judul;
    }
    
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    
    public void setKonten(String konten){
        this.konten = konten;
    }
}
