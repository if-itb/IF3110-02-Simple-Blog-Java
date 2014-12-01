/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name="comment")
@RequestScoped
public class Comment {
    
    private String nama;
    private String tanggal;
    private String email;
    private String komen;

    public Comment(String nama, String email, String komen) {
        this.nama = nama;
        this.email = email;
        this.komen = komen;
    }

    public Comment(String nama, String tanggal, String email, String komen) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.email = email;
        this.komen = komen;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }
    /**
     * Creates a new instance of Comment
     */
    public Comment() {
    }
    
    public void Copy(Comment C){
        this.nama=C.nama;
        this.email=C.email;
        this.komen=C.komen;
    }
    
    public void NewComment(String _nama, String _email, String _komen){
        nama=_nama;
        email=_email;
        komen=_komen;
    }
}
