import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "post", eager = true)
@RequestScoped
public class Post {

    // attribute
    private int id;
    private String judul;
    private String tanggal;
    private String konten;
    private String status;
    
    // default constructor
    public Post() {
    }
    
    // setter
    public void setId(int i) {
        id = i;
    }
    public void setJudul(String s) {
        judul = s;
    }
    public void setTanggal(String s) {
        tanggal = s;
    }
    public void setKonten(String s) {
        konten = s;
    }
    public void setStatus(String s) {
        status = s;
    }
    
    // getter
    public int getId() {
        return id;
    }
    public String getJudul() {
        return judul;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getKonten() {
        return konten;
    }
    public String getStatus() {
        return status;
    }
}