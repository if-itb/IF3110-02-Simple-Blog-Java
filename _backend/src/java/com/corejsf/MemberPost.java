/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.corejsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "member_post")
@RequestScoped
public class MemberPost {
    private int idPost;
    private String memberEmail;
    
    public MemberPost() {
    }
    
    public int getIdPost(){
        return idPost;
    }
    
    public String getMemberEmail(){
        return memberEmail;
    }
    
    public void setIdPost(int idPost){
        this.idPost = idPost;
    }
    
    public void setMemberEmail(String memberEmail){
        this.memberEmail = memberEmail;
    }
    
}
