/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Yusuf
 */
@ManagedBean(name = "edit", eager = true)
@SessionScoped
public class editPost {

    
    private int pid;
    private BlogPost post;
    
    /**
     * Creates a new instance of editPost
     */
    public editPost() {
        post = new BlogPost();
        pid = 0;
    }
    
    public String initiate(int ppid){
        UserData ud = new UserData();
        pid = ppid;
        post = ud.getBlogPost(ppid);
        BlogPost dummy = ud.getBlogPost(ppid);
        return "faces/edit_post.xhtml";
    }
    
    public BlogPost getPost(){
        return post;
    }
    
    public void setPost(BlogPost post){
        this.post.setDeleted(post.getDeleted());
        this.post.setPid(post.getPid());
        this.post.setPostcontent(post.getPostcontent());
        this.post.setPostdate(post.getPostdate());
        this.post.setPosttitle(post.getPosttitle());
        this.post.setPublished(post.getPublished());
        this.post.setUid(post.getUid());
        this.post.setUsername(post.getUsername());
    }
    
    public String submitPost(){
        UserData ud = new UserData();
        ud.writeBlogPost(post);
        return "faces/index.xhtml";
    }
}
