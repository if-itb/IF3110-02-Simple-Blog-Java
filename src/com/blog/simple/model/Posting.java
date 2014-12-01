package com.blog.simple.model;

import java.util.Date;


public class Posting {
    int Identifier;
    Date  Tanggal;
    String Judul;
    String Konten;

    public int getId() {
        return Identifier;
    }

    public void setId(int Id) {
        this.Identifier = Id;
    }

    public Date getTanggal() {
        return Tanggal;
    }

    public void setTanggal(Date Tanggal) {
        this.Tanggal = Tanggal;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    public String getKonten() {
        return Konten;
    }

    public void setKonten(String Konten) {
        this.Konten = Konten;
   }
}
