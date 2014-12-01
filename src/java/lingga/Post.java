package lingga;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jelink
 */
@ManagedBean(name="post",eager=true)
@SessionScoped
public class Post {
    int id_post;
    String judul;
    String tanggal;
    String konten;
    boolean published;
    boolean isReadMore=false;
    
    public Post(){
	
    }
    
    public void copy(Post p){
	id_post = p.id_post;
	judul = p.judul;
	tanggal = p.tanggal;
	konten = p.konten;
	published = p.published;
    }
    
    public void makeEmpty(){
	judul = tanggal = konten = null;
	published = false;
    }

    public Post(int id_post, String judul, String tanggal, String konten, boolean published) {
        this.id_post = id_post;
        this.judul = judul;
        this.tanggal = tanggal;
        this.konten = konten;
        this.published = published;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    /*public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }*/
    
    public void setTanggal(String tanggal){
	this.tanggal = tanggal;
    }

    public String getKonten() {
        return konten;
    }
    
    public String getExcerptHTML(int n) {
	List<String> l = new ArrayList<String>();
	int pos = 0;
	boolean br = false;
	while (l.size() < n && !br) {
	    int excerpt = konten.indexOf(' ', pos);
	    if (excerpt == -1) {
		l.add(konten.substring(pos));
		br=true;
	    }
	    if(!br){
		l.add(konten.substring(pos, excerpt));
		pos = excerpt + 1;
	    }
	}
	String retval="";
	for(String word : l){
	    retval = retval + ' ' + word;
	}
	//System.out.println(">>>"+l.size()+" " + n);
	if(l.size()<n){ 
	    this.isReadMore = false;
	    return retval.replace("\n", " ");
	}
	else{
	    this.isReadMore = true;
	    return retval.replace("\n", " ")+". . .";
	}
	
    }
    
    public String getKontenHTML(){
	return konten.replace("\n", "<br />");
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
    
    public String editButton(int role){
	if (!(role==2 || role==3 || (role==1 && published))) return "none";
	else return "block-inline";
    }
    
    public String publishButton(int role){
	if(published || role==1 || role==4) return "none";
	else return "block-inline";
    }
    
    public String deleteButton(int role){
	if(role==4) return "none";
	else return "block-inline";
    }
    
    public String hardDeleteButton(int role){
	if(role==3) return "block-inline";
	else return "none";
    }
    
    public String readMoreButton(){
	
	if(!isReadMore){ 
	    System.out.println("none");
	    return "none";
	}
	else{
	    return "block-inline";
	}
    }
    
}