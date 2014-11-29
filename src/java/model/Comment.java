package model;

/**
 *
 * @author Eldwin Christian | 13512002
 * Kelas yang berisi model komentar
 */
public class Comment {
    private int id;
    private int post_id;
    private String name;
    private String email;
    private String time;
    private String content;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the post_id
     */
    public int getPost_id() {
        return post_id;
    }

    /**
     * @param post_id the post_id to set
     */
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
