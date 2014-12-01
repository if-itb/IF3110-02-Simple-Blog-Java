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
@ManagedBean(name = "post")
@RequestScoped
public class Post {
    private int id;
    private String status;
    private String judul;
    private String tanggal;
    private String konten;
    private int deleted;
    
    public Post() {
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
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
