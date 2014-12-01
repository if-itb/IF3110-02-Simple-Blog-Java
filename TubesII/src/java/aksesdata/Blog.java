/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aksesdata;

import java.util.Date;
import java.util.List;

public class Blog {
    
    private int id;
    private String judul;
    private String isi;
    private String tanggal;
    public List result;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public String getJudul() {
        return judul;
    }
    
    public String getIsi() {
        return isi;
    }
    
    public String getTanggal() {
        return tanggal;
    }
    
    public void setJudul(String _judul) {
        judul = _judul;
    }
    
    public void setIsi(String _isi) {
        isi = _isi;
    }
    
    public void setTanggal(String _tanggal) {
        tanggal = _tanggal;
    }
    
    public List getResult() {
        return result;
    }
    
    public void setResult() throws Exception{
        customerBean blog = new customerBean();
        result = blog.getCustomerList();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
