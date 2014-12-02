package model;

/**
 *
 * @author Ahmad Shahab | 13512033
 * Kelas yang berisi model Post
 */
public class Post {
    /* Atribut Post */
    private int id;
    private String title;
    private String date;
    private String content;
    private int published;

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
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

    /**
     * @return the published
     */
    public String getPublishedInfo() {
        if(published == 1){
            return "Published";
        }
        else if(published == 0){
            return "Unpublished";
        }
        else if(published == -1){
            return "Deleted";
        }
        else{
            return "";
        }
    }
    
    public int getPublished(){
        return published;
    }

    /**
     * @param published the pubished to set
     */
    public void setPublished(int published) {
        this.published = published;
    }
}
