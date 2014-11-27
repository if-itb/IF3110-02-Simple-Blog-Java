package Model;

/**
 * Representation of PostCategory
 * @modified Riva Syafri Rachmatullah 
 */
public class PostCategory {
    private int id;
    private String category;
    
    /**
     * Create new instance of post
     * @param id id of post
     * @param category category of post
     */
    public PostCategory(int id, String category) {
        this.id = id;
        this.category = category;
    }
    
    /**
     * Get the id of post
     * @return id of post
     */
    public int getID() {
        return id;
    }
    
    /**
     * Get the category of post
     * @return category of post
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Set the new id of post
     * @param id new id of post
     */
    public void setID(int id) {
        this.id = id;
    }
    
    /**
     * Set the new category of post
     * @param category new category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
