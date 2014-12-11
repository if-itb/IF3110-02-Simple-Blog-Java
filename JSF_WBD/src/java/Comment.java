/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class Comment {

    /**
     * Creates a new instance of Comment
     */
    int Cid;
    int Pid;
    String Nama;
    String Email;
    String Konten;
    
    public Comment() {
    }

    public void setCid(int Cid) {
        this.Cid = Cid;
    }

    public void setPid(int Pid) {
        this.Pid = Pid;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setKonten(String Konten) {
        this.Konten = Konten;
    }

    public int getCid() {
        return Cid;
    }

    public int getPid() {
        return Pid;
    }

    public String getNama() {
        return Nama;
    }

    public String getEmail() {
        return Email;
    }

    public String getKonten() {
        return Konten;
    }
    
    
}
