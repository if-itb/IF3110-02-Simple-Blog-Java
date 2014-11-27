package Model;

import java.sql.Date;

/**
 * Representation of Post
 * @author Luthfi Hamid Masykuri
 * @modified Riva Syafri Rachmatullah 
 */
public class Post {
    private int id;
    private String title;
    private PostCategory category;
    private Date date;
    private String content;
    private User author;
    private boolean ispublished;
    private boolean isdeleted;
    
    /**
     * Create a new instance of empty post
     */
    public Post() {}
    
    /**
     * Create a new instance of post
     * @param id id of post
     * @param title title of post
     * @param category category of post
     * @param date date of post
     * @param content content of post
     * @param author author of post
     * @param ispublished published/draft post
     * @param isdeleted deleted post
     */
    public Post(int id, String title, PostCategory category, Date date, String content, User author, boolean ispublished, boolean isdeleted) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.content = content;
        this.author = author;
        this.ispublished = ispublished;
        this.isdeleted = isdeleted;
    }
    
    /**
     * Get the id of post
     * @return id of post
     */
    public int getID() {
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
     * Get the category of post
     * @return category of post
     */
    public PostCategory getCategory() {
        return category;
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
    public boolean IsPublished() {
        return ispublished;
    }
    
    /**
     * Check whether the post is already deleted or not
     * @return deleted status of post
     */
    public boolean IsDeleted() {
        return isdeleted;
    }
    
    /**
     * Set the new id of post
     * @param id new id of post
     */
    public void setID(int id) {
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
     * Set the new category of post
     * @param category new category
     */
    public void setCategory(PostCategory category) {
        this.category = category;
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
     */
    public void changePublishedStatus() {
        ispublished = !ispublished;
    }
    
    /**
     * Change status of post to be deleted or not
     */
    public void changeDeletedStatus() {
        isdeleted = !isdeleted;
    }
}
