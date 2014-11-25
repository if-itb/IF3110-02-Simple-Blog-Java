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
public class TempPostEdit {
    public int Id;
    public String Judul = "";
    public String Tanggal = "";
    public String Konten = "";
    /**
     * Creates a new instance of TempPostEdit
     */
    public TempPostEdit() {
    }
    public void editPost(int pid){
        
    }
    
}
