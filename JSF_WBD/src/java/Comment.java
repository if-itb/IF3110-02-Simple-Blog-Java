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
    int getCid(){return Cid;}
    int getPid(){return Pid;}
    String getName(){return Nama;}
    String getEmail(){return Email;}
    String Ccontent(){return Konten;}
    void setCid(int i){Cid=i;}
    void setPid(int i){Pid=i;}
    void getName(String s){Nama=s;}
    void getEmail(String s){Email=s;}
    void Ccontent(String s){Konten=s;}
    
}
