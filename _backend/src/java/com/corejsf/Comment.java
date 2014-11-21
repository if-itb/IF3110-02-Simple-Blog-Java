/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "comment")
@RequestScoped
public class Comment {

    private int id;
    private int idPost;
    private String nama;
    private String email;
    private String tanggal;
    private String komentar;
    
    public Comment() {
    }
    
    public int getId(){
        return id;
    }
    
    public int getIdPost(){
        return idPost;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getTanggal(){
        return tanggal;
    }
    
    public String getKomentar(){
        return komentar;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setIdPost(int idPost){
        this.idPost = idPost;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public void getEMail(String email){
        this.email = email;
    }
    
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    
    public void setKomentar(String komentar){
        this.komentar = komentar;
    }
}
