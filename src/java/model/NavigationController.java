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
        return "Login.xhtml";
    }
    
    public String gotoListUser(){
        return "List-User.xhtml";
    }
    
    public String gotoListPost() {
        return "List-Post.xhtml";
    }
    
    public String gotoAddPost() {
        return "Add-Post.xhtml";
    }
    
    public String gotoUnpublishedPost() {
        return "List-Unpublish-Post.xhtml";
    }
    
    public String gotoEditPost(int post_id) {
        return "Edit-Post.xhtml?id=" + post_id;
    }
    
    public String gotoDeletePost(int post_id) {
        return "Delete-Post.xhtml?id=" + post_id;
    }
    
    public String gotoViewPost(int post_id) {
        System.out.println("klik here:" +post_id);
        return "View-Post_2.xhtml?id=" + post_id;
    }
    
    public String gotoViewUnpublished(int post_id) {
        return "View-Post_unpublished.xhtml?id=" + post_id;
    }
    
    public String gotoViewUnpublished() {
        return "View-Post_unpublished.xhtml";
    }
    
    public String gotoEditPost() {
        return "Edit-Post.xhtml";
    }
    
    public String gotoDeletePost() {
        return "Delete-Post.xhtml";
    }
    
    public String gotoViewPost(){
        return "View-Post_2.xhtml";
    }
}