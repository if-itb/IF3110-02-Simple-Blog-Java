package UserBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@ManagedBean(name = "beans", eager = true)
@SessionScoped
public class userMgnt implements Serializable {

    /**
     * Creates a new instance of userMgnt
     */
    private String Message;
    private Cookie cookie;
    private User user;
    private User newUser;
    private BlogPost post;
    private BlogPost lihatpost;
    private ArrayList<BlogPost> allPosts;
    private ArrayList<BlogPost> unpublishedPosts;
    private ArrayList<Komentar> comments;
    private Komentar komen;
    private int pid;
    private ArrayList<User> users;
    private ArrayList<BlogPost> deletedPosts;

    public userMgnt() {
        user = new User();
        post = new BlogPost();
        Message = "tidak ada";
        cookie = new Cookie("Username", null);
        cookie.setMaxAge(120);
        post = new BlogPost();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String passedParam = (String) facesContext.getExternalContext().getRequestParameterMap().get("pid");
        if(passedParam != null)
            pid = Integer.parseInt(passedParam);
        
        komen = new Komentar();
        newUser = new User();
    }
    
    public void login() {
        UserData ud = new UserData();
        User userValidator = ud.getUser(user.getUsername());
        if (userValidator != null) {
            if (user.getPassword().compareTo(userValidator.getPassword()) == 0) {
                user.setUid(userValidator.getUid());
                user.setFullname(userValidator.getFullname());
                user.setRole(userValidator.getRole());
                user.setEmail(userValidator.getEmail());
                cookie.setValue(user.getUsername());
                FacesContext facesContex = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) facesContex.getExternalContext().getResponse();
                response.addCookie(cookie);
                
                komen.setEmail(userValidator.getEmail());
                komen.setKomentator(userValidator.getFullname());
            } else {
                Message = "Password is wrong";
            }
        } else {
            Message = "Username not found";
        }
    }

    public String Logout() {
        reset();
        Message = "tidak ada";
        cookie.setValue(null);
        cookie.setMaxAge(0);
        FacesContext facesContex = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContex.getExternalContext().getResponse();
        response.addCookie(cookie);
        return "faces/index.xhtml";
    }

    public void reset() {
        user.setFullname("guest");
        user.setUsername("guest");
        user.setPassword("pass");
        user.setEmail("");
        user.setRole("guest");
        user.setUid(0);
    }

    public boolean isLogin() {
        return user.getUsername().compareTo("guest") != 0 && cookie.getValue() != null;
    }

    public boolean isAdmin(){
        return user.getRole().compareTo("admin")==0;
    }
    
    public boolean isOwner(){
        return user.getRole().compareTo("owner")==0;
    }
    
    public boolean isEditor(){
        return user.getRole().compareTo("editor")==0;
    }
    
    public boolean activeMessage() {
        return !Message.equals("tidak ada") && !isLogin();
    }
    
    public void submitPost(){
        UserData ud = new UserData();
        post.setUid(user.getUid());
        post.setUsername(user.getFullname());
        ud.writeBlogPost(post);
        post.reset();
    }
    
    public void publishPost(int pid){
        UserData ud = new UserData();
        ud.publishPost(pid);
    }
    
    public void softDeletePost(int ppid){
        UserData ud = new UserData();
        ud.softDeletePost(ppid);
    }
    
    public void deletedPost(int ppid){
        UserData ud = new UserData();
        ud.deletePost(ppid);
    }
    
    public void writeComment(){
        UserData ud = new UserData();
        komen.setPid(post.getPid());
        ud.writeKomentar(komen);
    }
        
    public ArrayList<Komentar> getComments(){
        UserData ud = new UserData();
        comments = ud.getListKomentar(post.getPid());
        return comments;
    }
    
    public Komentar getKomen(){
        return komen;
    }
    
    public void setKomen(Komentar komen){
        this.komen.setCid(komen.getCid());
        this.komen.setCommentDate(komen.getCommentDate());
        this.komen.setEmail(komen.getEmail());
        this.komen.setKomen(komen.getKomen());
        this.komen.setKomentator(komen.getKomentator());
        this.komen.setPid(komen.getPid());
    }
    
    public ArrayList<BlogPost> getUnpublishedPosts(){
        UserData ud = new UserData();
        return ud.getAllUnpublishedBlogPost();
    }
    
    public void setUnpublishedPosts(ArrayList<BlogPost> unpublishedPostSet){
        unpublishedPosts.clear();
        for (BlogPost unpublishedPostSet1 : unpublishedPostSet) {
            unpublishedPosts.add(unpublishedPostSet1);
        }
    }
    
    public User getUser() {
        return this.user;
    }

    public void setUser(User newUser) {
        user.setEmail(newUser.getEmail());
        user.setFullname(newUser.getFullname());
        user.setPassword(newUser.getPassword());
        user.setRole(newUser.getRole());
        user.setUid(newUser.getUid());
        user.setUsername(newUser.getUsername());
    }

    public ArrayList<BlogPost> getAllPosts() {
        UserData ud = new UserData();
        return ud.getAllBlogPost();
    }

    public void setAllPosts(ArrayList<BlogPost> postSet) {
        allPosts.clear();
        for (BlogPost postSet1 : postSet) {
            allPosts.add(postSet1);
        }
    }

    public BlogPost getPost(){
        return post;
    }
    
    public void setPost(BlogPost post){
        this.post.setPid(post.getPid());
        this.post.setUid(post.getUid());
        this.post.setUsername(post.getUsername());
        this.post.setPosttitle(post.getPosttitle());
        this.post.setPostcontent(post.getPostcontent());
        this.post.setPostdate(post.getPostdate());
        this.post.setPublished(post.getPublished());
        this.post.setDeleted(post.getDeleted());
    }

    public String getMessage() {
        return Message;
    }
    
    public ArrayList<User> getUsers(){
        UserData ud = new UserData();
        users =  ud.getListUser();
        return users;
    }
    
    public void setMessage(String S) {
        Message = S;
    }

    public Cookie getCookie() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cook = null;

        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (int i = 0; i < userCookies.length; i++) {
                if (userCookies[i].getName().equals("Username")) {
                    cook = userCookies[i];
                    return cook;
                }
            }
        }
        return null;
    }
    
    public String genEditLink(int editPid){
        return "faces/edit_post.xhtml?pid="+editPid;
    }
    
    public String genPostLink(int ppid){
        UserData ud = new UserData();
        lihatpost = ud.getBlogPost(ppid);
        return "faces/post.xhtml";
    }
            
    public String updateUserOption(int usersIndex){
        if(users.size() >= usersIndex){
            UserData ud = new UserData();
            ud.updateUserDB(users.get(usersIndex));
            return "alert('update berhasil');";
        }
        return "alert('update gagal');";
    }
    
    public ArrayList<BlogPost> getDeletedPosts(){
        UserData ud = new UserData();
        return ud.getDeletedPosts();
    }
    
    public void restoreDeletedPost(int delPid){
        UserData ud = new UserData();
        ud.restorePost(delPid);
    }
    
    public String genAddPostLink(){
        post.reset();
        return "faces/add_post.xhtml";
    }
    
    public User getNewUser(){
        return newUser;
    }
    
    public void createNewUser(){
        UserData ud = new UserData();
        ud.createUserDB(newUser);
    }
    
    public BlogPost getLihatpost(){
        return lihatpost;
    }
}
