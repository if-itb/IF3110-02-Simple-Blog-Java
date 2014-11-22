/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Function;

import java.util.Date;

/**
 *
 * @author ASUS
 */

public class ViewPost {
    private int postid;
    private String judul;
    private String konten;
    private Date tanggal;
    
    public int getPostid(){
    return postid;}
    
    public String getJudul(){
    return judul;}
    
    public String getKonten(){
    return konten;}
    
    public Date getTanggal(){
    return tanggal;}
}
