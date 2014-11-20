/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.*;
import models.Post;

@ManagedBean(name="postController", eager = true)
@RequestScoped
public class PostController implements Serializable {
    
    private Post post;
    private List<Post> posts;
    private String test;
    
    public String getTest(){ return test; }
    
    public PostController(){
        test = "hai halo";
    }
    
    public String showCreate(){
        return "post/create";
    }
    
    public String submit(){
        //save the data
        //redirect
        return "post/index";
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
}
