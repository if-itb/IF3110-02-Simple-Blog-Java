/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Post;

/**
 *
 * @author Ahmad
 */
@ManagedBean(name="postController", eager=true)
@SessionScoped
public class PostController {
    // current Post
    private Post post;
    
    private DBController dbController;
    
    public PostController() throws SQLException, ClassNotFoundException {
        dbController = DBController.getInstance();
        post = null;
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Post post) {
        this.post = post;
    }
    

    /**
     * @return the dbController
     */
    public DBController getDbController() {
        return dbController;
    }
    
    public Post getPostById(Integer postId) throws SQLException, IOException {
        ResultSet result = getDbController().executeQuery("SELECT * FROM `post` WHERE id ="+postId.toString());
        Post tempPost = new Post();
        while (result.next()) {
            tempPost.setId(result.getInt("id"));
            tempPost.setTitle(result.getString("title"));
            tempPost.setDate(result.getString("date"));
            tempPost.setContent(result.getString("content"));
            tempPost.setPublished(true);
        }
        return tempPost;
    }
    
    public void viewPost(Integer postId) throws SQLException, IOException{
        setPost(getPostById(postId));
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("post.xhtml");
    }
    
    public List<Post> publish() throws SQLException{
        ResultSet result = getDbController().executeQuery("SELECT * FROM `post`");
        List<Post> postList = new ArrayList<>();
        while (result.next()) {
            Post post = new Post();
            
            post.setId(result.getInt("id"));
            post.setContent(result.getString("content"));
            post.setDate(result.getString("date"));
            post.setTitle(result.getString("title"));
            
            boolean publish = false;
            if(result.getInt("published")==1){
                publish = true;
            }
            post.setPublished(publish);
            
            if(!post.getPublished()){
                postList.add(post);
            }
        }
        return postList;
    }
    
    public List<Post> getPostList() throws SQLException {
        ResultSet result = getDbController().executeQuery("SELECT * FROM `post`");
        List<Post> postList = new ArrayList<>();
        while (result.next()) {
            Post post = new Post();
            
            post.setId(result.getInt("id"));
            post.setContent(result.getString("content"));
            post.setDate(result.getString("date"));
            post.setTitle(result.getString("title"));
            
            boolean publish = false;
            if(result.getInt("published")==1){
                publish = true;
            }
            post.setPublished(publish);
            
            postList.add(post);
        }
        return postList;
    }
    
    public void addPost(String title, String date, String content, boolean publicationStatus) throws SQLException, IOException{
        Integer publication = 0;
        if(publicationStatus){
            publication = 1;
        }
        getDbController().executeUpdate("INSERT INTO POST (title, date, content,published) VALUES ('"+title+"','"+date+"','"+content+"',"+publication.toString()+")");
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    public void editPost(Post _post) throws IOException{
        setPost(_post);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("edit_post.xhtml");
    }
    
    public void updatePost(Integer id, String title, String date, String content, boolean publicationStatus) throws SQLException, IOException{
        Integer publication = 0;
        
        if(publicationStatus){
            publication = 1;
        }
        
        getDbController().executeUpdate("UPDATE POST SET title='"+title+"', date='"+date+"', content='"+content+"',published="+publication.toString()+" WHERE id="+id+"");
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    public void deletePost(Integer id) throws SQLException, IOException{
        getDbController().executeUpdate("DELETE FROM POST WHERE id="+id+"");
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    public void publishPost(Integer id) throws SQLException, IOException {
        Post tempPost = getPostById(id);
        tempPost.setPublished(true);
        
        updatePost(tempPost.getId(), tempPost.getTitle(), tempPost.getDate(),
                tempPost.getContent(), tempPost.getPublished());
    }
    
    public void unpublishPost(Integer id) throws SQLException, IOException {
        Post tempPost = getPostById(id);
        tempPost.setPublished(false);
        
        updatePost(tempPost.getId(), tempPost.getTitle(), tempPost.getDate(),
                tempPost.getContent(), tempPost.getPublished());
    }
}
