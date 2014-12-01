/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import java.sql.Date;
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

    public void setEmail(String email) {
        this.email = email;
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
    
    public String printTanggal(){
        int dd = Integer.parseInt(tanggal.substring(8,10));
        String s =  dd + " ";
        int mm = Integer.parseInt(tanggal.substring(5, 7));
        String m;
        switch(mm){
            case 1: m="Januari"; break;
            case 2: m="Februari"; break;
            case 3: m="Maret"; break;
            case 4: m="April"; break;
            case 5: m="Mei"; break;
            case 6: m="Juni"; break;
            case 7: m="Juli"; break;
            case 8: m="Agustus"; break;
            case 9: m="September"; break;
            case 10: m="Oktober"; break;
            case 11: m="November"; break;
            default: m="Desember"; break;
        }
        s = s + m + " " + (tanggal.substring(0,4));
        
        return s;
    }
}
