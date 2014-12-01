/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.*;
import models.Comment;
import models.Post;
import models.User;
import services.CookieService;
import services.DBConnector;

@ManagedBean(name = "postCtrl", eager = true)
@SessionScoped
public class PostController implements Serializable {

    private final int POSTS_PER_PAGE = 5;
    @ManagedProperty(value="#{post}")
    private Post post;
    private ArrayList<Post> posts;

    public PostController() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = req.getSession();
        User userIdentity = (User) session.getAttribute("userIdentity");
        if (userIdentity == null || userIdentity.getIsGuest()){
            CookieService.loginWithCookies();
        }
//int page = Integer.parseInt(req.getParameter("page"));
        //int page = 1;
        //this.loadPosts((page - 1) * POSTS_PER_PAGE);
    }

    public String doSubmit() {
        if (post.getUserId() == 0) {
            post.setUserId(1);
        }
        if (post.save()) {
            return "success";
        } else {
            return "fail";
        }
    }
    
    public String showView(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = req.getSession();
        post = (Post) session.getAttribute("post");
        User userIdentity = (User) session.getAttribute("userIdentity");
        Comment comment = (Comment) session.getAttribute("comment");
        if (comment == null){
            comment = new Comment();
        }
        if (requestParam.containsKey("id")) {
            int post_id = Integer.parseInt(requestParam.get("id"));
            post = new Post();
            post.setId(post_id);
            post.load(post_id);
            comment.setPostId(post_id);
            comment.setNama(userIdentity.getNama());
            comment.setEmail(userIdentity.getEmail());
            session.setAttribute("post", post);
            session.setAttribute("comment",comment);
            return "view";
        } else {
            return "fail";
        }
    }
    
    public String showCreate() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        //HttpServletResponse res = (HttpServletResponse) context.getExternalContext().getResponse();
        HttpSession session = req.getSession();
        
        post = (Post) session.getAttribute("post");
        post.clearAttributes();
        post.setIsNewRecord(true);
        session.setAttribute("post",post);
        return "create";
    }
    
    public String showDelete(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
        if (requestParam.containsKey("id")) {
            this.post = new Post();
            this.post.setId(Integer.parseInt(requestParam.get("id")));
            this.post.delete();
            return "delete";
        }
        return "fail";
    }
  

    public String showIndex() {
        this.loadPosts(0);
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return req.getContextPath() + "/faces/post/index.xhtml";
    }

    public String showUpdate() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestParam = context.getExternalContext().getRequestParameterMap();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = req.getSession();
        
        User userIdentity = (User) session.getAttribute("userIdentity");
        if (requestParam.containsKey("id")) {
            post = (Post) session.getAttribute("post");
            post.load(Integer.parseInt(requestParam.get("id")));
            post.setIsNewRecord(false);
            session.removeAttribute("post");
            session.setAttribute("post",post);
            return "update";
        } else {
            return "fail";
        }

    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean loadPosts(int offset) {
        try {
            DBConnector dbc = new DBConnector();
            Statement st = dbc.getCon().createStatement();

            String query = "SELECT `id` FROM `post` ORDER BY `tanggal` DESC";
            System.out.println(query);
            ResultSet result = st.executeQuery(query);
            posts = new ArrayList<>();

            while (result.next()) {
                Post post = new Post();
                post.load(result.getInt("id"));
                posts.add(post);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Post> getPosts() {
        loadPosts(0);
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

}
