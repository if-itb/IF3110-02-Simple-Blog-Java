package Bean;


import Model.User;
import Model.Users;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevinyu
 */
@ManagedBean
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    
    @ManagedProperty(value="#{user}")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUsername() {
        return username;
    }
   
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String doNavigation() {
        boolean isExist = true;
        User new_user;
        new_user = Users.getInstance().validateUser(username,password);
        isExist = new_user!=null;  
        
        if (isExist){
            user.setId(new_user.getId());
            user.setPassword(new_user.getPassword());
            user.setRole(new_user.getRole());
            user.setUsername(new_user.getUsername());
            user.setEmail(new_user.getEmail());
            HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            req.getSession().setAttribute("isLogin", "yes");
            return "user_management?faces-redirect=true";
        }
        else {
            return "login?faces-redirect=true";
        }
    }

}
