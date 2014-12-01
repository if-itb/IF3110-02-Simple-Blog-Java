/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Afik
 */
public class AddKomentar {
   
    // TODO semua, sambungin ke List-User.xhtml, dao, dan teman - teman
    
    private UserBean user;
    public AddKomentar() {
        
    }
    
    public void add(Komentar komentar) throws IOException {
        if (user.getRole()!=UserBean.getGuest()) {
            komentar.setNama(user.getUsername());
            komentar.setEmail(user.getEmail());
        }
        String Tanggal = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        komentar.setTanggal(Tanggal);
        System.out.println("FLAG : "+komentar.getPID());
        DAO.CommentDAO DB = DAO.DAOFactory.getInstance("javabase.jdbc").getKomentarDAO();
        DB.create(komentar);
    }
    
    public String submit(Komentar komentar) throws IOException{
        add(komentar);
        return "succes?faces-redirect=true&includeViewParams=true";
        
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }
}
