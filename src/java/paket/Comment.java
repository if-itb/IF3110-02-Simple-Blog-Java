/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author User
 */
@ManagedBean (name="comment", eager=true)
@RequestScoped
public class Comment {
    private int id_kom;
    private String nama;
    private Date tanggal_kom;
    private String email;
    private String isi;
    private int id_post;

    /**
     * @return the id_kom
     */
    public int getId_kom() {
        return id_kom;
    }

    /**
     * @param id_kom the id_kom to set
     */
    public void setId_kom(int id_kom) {
        this.id_kom = id_kom;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the tanggal_kom
     */
    public Date getTanggal_kom() {
        return tanggal_kom;
    }

    /**
     * @param tanggal_kom the tanggal_kom to set
     */
    public void setTanggal_kom(Date tanggal_kom) {
        this.tanggal_kom = tanggal_kom;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the isi
     */
    public String getIsi() {
        return isi;
    }

    /**
     * @param isi the isi to set
     */
    public void setIsi(String isi) {
        this.isi = isi;
    }

    /**
     * @return the id_post
     */
    public int getId_post() {
        return id_post;
    }

    /**
     * @param id_post the id_post to set
     */
    public void setId_post(int id_post) {
        this.id_post = id_post;
    }
   
}
