package Model;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Representation of Post
 * @author Luthfi Hamid Masykuri
 * @modified Riva Syafri Rachmatullah 
 */
@ManagedBean(name="Post")
@RequestScoped
public class Post {
    private int id;
    private String title;
    private String image;
    private Date date;
    private String content;
    private User author;
    private boolean published;
    private boolean deleted;
    
    /**
     * Create an empty instance of Post
     */
    public Post()
    {
        id = 0;
        title = "";
        image = "";
        date = null;
        content = "";
        author = null;
        published = false;
    }
    
    /**
     * Create a new instance of post
     * @param id id of post
     * @param title title of post
     * @param date date of post
     * @param content content of post
     * @param author author of post
     * @param published published/draft post
     * @param deleted deleted post
     */
    public Post(int id, String title,String image, Date date, String content, User author, boolean published, boolean deleted) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.date = date;
        this.content = content;
        this.author = author;
        this.published = published;
        this.deleted = deleted;
    }
    
    /**
     * Get the id of post
     * @return id of post
     */
    public int getId() {
        return id;
    }
    
    /**
     * Get the title of post
     * @return title of post
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Get the image of post
     * @return image of post
     */
    public String getImage() {
        return image;
    }
    
    /**
     * Get the date of post
     * @return date of post
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Get the content of post
     * @return content of post
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Get the author of post
     * @return author of post
     */
    public User getAuthor() {
        return author;
    }
    
    /**
     * Check whether the post is already published or not
     * @return publishing status of post
     */
    public boolean getPublished() {
        return published;
    }
    
    /**
     * Check whether the post is already deleted or not
     * @return deleted status of post
     */
    public boolean getDeleted() {
        return deleted;
    }
    
    /**
     * Set the new id of post
     * @param id new id of post
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Set the new title of post
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Set the new image of post
     * @param image new image
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     * Set the new date of post
     * @param date new date
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * Set the new content of post
     * @param content new content
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Set the new author of post
     * @param author new author
     */
    public void setAuthor(User author) {
        this.author = author;
    }
    
    /**
     * Change status of post to be published or draft
     * @param published status of published
     */
    public void setPublished(boolean published) {
        this.published = published;
    }
    
    /**
     * Change status of post to be deleted or not
     * @param deleted status of published
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    public boolean isAuthor(User user){
        return (this.author.getUsername().compareTo(user.getUsername()) == 0);
    }
}
