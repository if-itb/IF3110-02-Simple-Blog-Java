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
    private Date date;
    private String content;
    private User author;
    
    /**
     * Create new instance of post
     * @param id id of post
     * @param title title of post
     * @param date date of post
     * @param content content of post
     * @param author author of post
     */
    public Post(int id,String title,Date date,String content,User author) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.author = author;
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
     * Set the new title of post
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
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
    
    public static void main(String args[]) {
       // Instantiate a Date object
       int year = 2012;
       int month = 12;
       int day = 21;
       Date date = new Date(year-1900,month-1,day);
        
       // display time and date using toString()
       System.out.println(date.toString());
   }
}
