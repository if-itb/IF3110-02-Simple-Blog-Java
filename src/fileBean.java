
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Riady
 */
@ManagedBean(name = "filebean",eager=true)
@RequestScoped
public class fileBean {
    private Part file;
    
    
    public fileBean(){
    
    }
    
    public Part getFile(){
        return file;
    }
    
    public void setFile(Part newfile){
        file=newfile;
    }
    
    public void uploadFile() throws IOException{
        InputStream fileContent = file.getInputStream();
        File uploads = new File("/path");
        File file2 = new File(uploads, getFilename(file));
        Files.copy(fileContent,file2.toPath());
    }
    
    private static String getFilename(Part part) {
    for (String cd : part.getHeader("content-disposition").split(";")) {
        if (cd.trim().startsWith("filename")) {
            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
        }
    }
    return null;
}
}
