/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

/**
 *
 * @author Rikysamuel
 */
public class Post {
    int Id;
    String Judul;
    String Tanggal;
    String Content;
    String Author;
    String Status;

    public Post(){
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

    public int getId() {
        return Id;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    public String getJudul() {
        return Judul;
    }

    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getContent() {
        return Content;
    }
    
    public String getContentPreview(){
        if(Content.length() > 300)
            return Content.substring(0, 300);
        else
            return Content;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }
    
    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }
}
