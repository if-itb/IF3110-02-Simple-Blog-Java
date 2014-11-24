/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Afik
 */
@ManagedBean(name = "NavigationController" , eager = true)
@RequestScoped
public class NavigationController implements Serializable{

    /**
     * Creates a new instance of NavigationController
     */
    public NavigationController() {
    }
    
    public String gotoLogin() {
        return "Login";
    }
    
    public String gotoListUser(){
        return "List-User";
    }
    
    public String gotoListPost() {
        return "List-Post";
    }
}
