import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "comment", eager = true)
@RequestScoped
public class Comment {
    
    // attribute
    private int idc;
    private int idp;
    private String nama;
    private String tanggal;
    private String email;
    private String komentar;
    
    // defaul constructor
    public Comment() {
    }
    
    // getter
    public int getIdc() {
        return idc;
    }
    public int getIdp() {
        return idp;
    }
    public String getNama() {
        return nama;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getEmail() {
        return email;
    }
    public String getKomentar() {
        return komentar;
    }  
    
    // setter
    public void setIdc(int idc) {
        this.idc = idc;
    }
    public void setIdp(int idp) {
        this.idp = idp;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}