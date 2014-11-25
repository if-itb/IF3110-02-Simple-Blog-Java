package Model;

import java.sql.Timestamp;

/**
 * Representation of comment
 * @author Luthfi Hamid Masykuri
 * @modified Riva Syafri Rachmatullah
 */
public class Comment {
    private String name;
    private String email;
    private String content;
    private Timestamp time;
    
    /**
     * Create an instance of comment
     * @param name name of commentator
     * @param email email of commentator
     * @param content content of comment
     * @param time time taken
     */
    public Comment(String name,String email,String content,Timestamp time) {
        this.name = name;
        this.email = email;
        this.content = content;
        this.time = time;
    }
    
    /**
     * Get name of commentator
     * @return name of commentator
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get email of commentator
     * @return email of commentator
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Get content of comment
     * @return content of comment
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Get time taken on comment
     * @return time taken on comment
     */
    public Timestamp getTime() {
        return time;
    }
    
    /**
     * Set name of commentator
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Set content of comment
     * @param content new content
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Set time taken on comment
     * @param time new time of comment
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
