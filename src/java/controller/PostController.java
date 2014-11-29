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
 * @author Ahmad Shahab
 * Kelas Post Controller, mengatur penambahan, perubahan, dan penghapusan post
 */
@ManagedBean(name="postController", eager=true)
@SessionScoped
public class PostController {
    // current Post
    private Post post;
    
    private final DBController dbController;
    
    public PostController() throws SQLException, ClassNotFoundException {
        dbController = DBController.getInstance();
        post = null;
    }

    // Getter-Setter untuk post
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

    // mengembalikan post jika diketahui id-nya
    public Post getPostById(Integer postId) throws SQLException, IOException {
        ResultSet result = dbController.executeQuery("SELECT * FROM `post` "
                + "WHERE `id` = ?", new String[]{postId.toString()});
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
    
    // menampilkan post dengan id postId
    public void viewPost(Integer postId) throws SQLException, IOException{
        setPost(getPostById(postId));
        FacesContext.getCurrentInstance().getExternalContext().redirect("post.xhtml");
    }
    
    // menampilkan seluruh post yang belum dipublish
    public List<Post> publish() throws SQLException{
        ResultSet result = dbController.executeQuery("SELECT * FROM `post`");
        List<Post> postList = new ArrayList<>();
        while (result.next()) {
            Post _post = new Post();
            
            _post.setId(result.getInt("id"));
            _post.setContent(result.getString("content"));
            _post.setDate(result.getString("date"));
            _post.setTitle(result.getString("title"));
            
            boolean publish = false;
            if(result.getInt("published")==1){
                publish = true;
            }
            _post.setPublished(publish);
            
            if(!_post.getPublished()){
                postList.add(_post);
            }
        }
        return postList;
    }
    
    // Mengembalikan list seluruh post yang ada
    public List<Post> getPostList() throws SQLException {
        ResultSet result = dbController.executeQuery("SELECT * FROM `post`");
        List<Post> postList = new ArrayList<>();
        while (result.next()) {
            Post _post = new Post();
            
            _post.setId(result.getInt("id"));
            _post.setContent(result.getString("content"));
            _post.setDate(result.getString("date"));
            _post.setTitle(result.getString("title"));
            
            boolean publish = false;
            if(result.getInt("published")==1){
                publish = true;
            }
            _post.setPublished(publish);
            
            postList.add(_post);
        }
        return postList;
    }
    
    // menambahkan post dengan atribut sesuai parameter fungsi ini
    public void addPost(String title, String date, String content, boolean publicationStatus) 
            throws SQLException, IOException{
        Integer publication = 0;
        if(publicationStatus){
            publication = 1;
        }
        dbController.executeUpdate("INSERT INTO POST"
                + "(`title`, `date`, `content`, `published`) "
                + "VALUES (?, ?, ?, ?)",
                new String[]{title, date, content, publication.toString()});
        
        // redirect ke laman utama
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    // mengedit post; redirect ke halaman edit post
    public void editPost(Post _post) throws IOException{
        setPost(_post);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("edit_post.xhtml");
    }
    
    // update post dengan atribut sesuai parameter fungsi
    public void updatePost(Integer id, String title, String date, String content, 
            boolean publicationStatus) throws SQLException, IOException{
        Integer publication = 0;
        
        if(publicationStatus){
            publication = 1;
        }
        
        dbController.executeUpdate("UPDATE `post` SET "
                + "`title` = ?, `date` = ?, `content` = ?, `published` = ?"
                + "WHERE `id` = ?",
                new String[]{title, date, content, publication.toString(), id.toString()});
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    // menghapus post dengan id tertentu
    public void deletePost(Integer id) throws SQLException, IOException{
        dbController.executeUpdate("DELETE FROM POST WHERE id=?",
                new String[]{id.toString()});
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    // mempublish post dengan id tertentu
    public void publishPost(Integer id) throws SQLException, IOException {
        Post tempPost = getPostById(id);
        tempPost.setPublished(true);
        
        updatePost(tempPost.getId(), tempPost.getTitle(), tempPost.getDate(),
                tempPost.getContent(), tempPost.getPublished());
    }
    
    // menonpublish post dengan id tertentu
    public void unpublishPost(Integer id) throws SQLException, IOException {
        Post tempPost = getPostById(id);
        tempPost.setPublished(false);
        
        updatePost(tempPost.getId(), tempPost.getTitle(), tempPost.getDate(),
                tempPost.getContent(), tempPost.getPublished());
    }
}
