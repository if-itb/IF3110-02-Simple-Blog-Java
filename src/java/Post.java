/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Bagaskara Pramudita
 */
@ManagedBean(name="post")
@RequestScoped
public class Post {

    private String judul;
    private String tanggal;
    private int status;
    private String konten;
    private int id;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }
    
    public String getKontenHTML(){
        String s = konten.replaceAll("\n", "<br></br>");
        return s;
    }
    
    public String getKontenFront(){
        if(konten.length()>250){
            String s = konten.substring(0, 250);
            return s.replaceAll("\n", "<br></br>")+".........";
        }
        else{
            return konten;
        }
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }
    /**
     * Creates a new instance of Post
     */
    public Post() {
    }
    
    public Post(int _id, String _judul, String _tanggal, String _konten){
        id = _id;
        judul=_judul;
        tanggal=_tanggal;
        konten=_konten;
    }
    
        public Post(int id, String judul, String tanggal, String konten, int status) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.status = status;
        this.konten = konten;
        this.id = id;
    }
    
    public void Copy(Post P){
        id = P.id;
        judul = P.judul;
        tanggal = P.tanggal;
        konten= P.konten;
        status=P.status;
    }
    
}
