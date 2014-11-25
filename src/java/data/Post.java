/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author user
 */
public class Post {
    private String judul;
    private String tanggal;
    private String konten;
    private boolean published;
    Post(){
        published = false;
    }
    public void setJudul(String value){
        judul = value;
    }
    public void setTanggal(String value){
        tanggal = value;
    }
    public void setKonten(String value){
        konten = value;
    }
    public void setKategori(boolean value){
        published = value;
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
    public boolean getPublished(){
        return published;
    }
}
