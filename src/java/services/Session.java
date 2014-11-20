/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import models.*;

@ManagedBean(name="session", eager=true)
@SessionScoped
public class Session implements Serializable{
    @ManagedProperty(value="#{userIdentity}")
    private User userIdentity;

    public User getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(User userIdentity) {
        this.userIdentity = userIdentity;
    }
    
    
}
