package Database;

import Model.Comment;
import Model.CommentofComment;
import Model.Post;
import Model.User;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Representation of Data Post
 * @author Riva Syafri Rachmatullah
 * @modified Luthfi Hamid Masykuri
 */
@ManagedBean(name="PostData")
@RequestScoped
public class PostData implements Serializable {
    private MySQL db;
    private String table;
    
    /**
     * Create an instance of PostData
     */
    public PostData() {
        table = "post";
        db = new MySQL();
    }
    
    /**
     * Get post by its id from database
     * @param id_post the id of post
     * @return an instance of post from database
     */
    public Post getPost(int id_post) {
        try {
            this.db.Where("id=", "" + id_post);
            ResultSet Data = this.db.Select(table);
            if (Data.first()) {
                int id = Data.getInt("id");
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                return new Post(id, title, date, content, author, published, deleted);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get all post from database
     * @return list of post
     */
    public List<Post> getAllPost() {
        try {
            this.db.Where("published=", "1");
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int id = Data.getInt("id");
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(id, title, date, content, author, published, deleted);
                ListPost.add(post);
                isExist = Data.next();
            }
            return ListPost;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get all post based on username
     * @param user username
     * @return All post by username
     */
    public List<Post> getPostbyAuthor(User user) {
        try {
            this.db.Where("published=", "1");
            this.db.Where("username=", user.getUsername());
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, date, content, author, published, deleted);
                ListPost.add(post);
                isExist = Data.next();
            }
            return ListPost;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get all draft from database
     * @return list of draft
     */
    public List<Post> getAllDraft() {
        try {
            this.db.Where("published=", "0");
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, date, content, author, published, deleted);
                ListPost.add(post);
                isExist = Data.next();
            }
            return ListPost;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get all draft based on username
     * @param user username
     * @return All draft by username
     */
    public List<Post> getDraftbyAuthor(User user) {
        try {
            this.db.Where("published=", "0");
            this.db.Where("username=", user.getUsername());
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, date, content, author, published, deleted);
                ListPost.add(post);
                isExist = Data.next();
            }
            return ListPost;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Add post to database
     * @param post the new post
     * @param author the author of new post
     * @param published published status of post
     * @return String status to pass
     */
    public String addPost(Post post, User author, boolean published) {
        String col[] = {"title", "date", "content", "author", "published", "deleted"};
        String val[] = new String[6];
        val[0] = post.getTitle();
        Date date = new Date(post.getDate().getTime());
        val[1] = date.toString();
        val[2] = post.getContent();
        val[3] = author.getUsername();
        if (published) {
            val[4] = "1";
        } else {
            val[4] = "0";
        }
        val[5] = "0";
        int query = this.db.Insert(table, col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
    }
    
    /**
     * Edit post on database
     * @param post updated post
     * @return String status
     */
    public String editPost(Post post) {
        this.db.Where("id=", ""+post.getID());
        String col[] = {"title", "date", "content", "author", "published", "deleted"};
        String val[] = new String[6];
        val[0] = post.getTitle();
        Date date = new Date(post.getDate().getTime());
        val[1] = date.toString();
        val[2] = post.getContent();
        val[3] = post.getAuthor().getUsername();
        if (post.IsPublished()) {
            val[4] = "1";
        } else {
            val[4] = "0";
        }
        val[5] = "0";
        int query = this.db.Update(table, col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
    }
    
    /**
     * Delete post from database 
     * @param post deleted post
     */
    public void hardDelPost(Post post) {
        this.db.Where("id=", ""+post.getID());
        this.db.Delete(table);
    }
    
    /**
     * Check deleted status on database but not delete post in database
     * @param post deleted post to recycle bin
     * @return string status
     */
    public String softDelPost(Post post) {
        this.db.Where("id=", ""+post.getID());
        String col[] = {"title", "date", "content", "author", "published", "deleted"};
        String val[] = new String[6];
        val[0] = post.getTitle();
        Date date = new Date(post.getDate().getTime());
        val[1] = date.toString();
        val[2] = post.getContent();
        val[3] = post.getAuthor().getUsername();
        val[4] = "0";
        val[5] = "1";
        int query = this.db.Update(table, col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
    }
    
    /**
     * Restore soft deleted post in database
     * @param post restored post
     * @return string status
     */
    public String restorePost(Post post) {
        this.db.Where("id=", "" + post.getID());
        String col[] = {"title", "date", "content", "author", "published", "deleted"};
        String val[] = new String[6];
        val[0] = post.getTitle();
        Date date = new Date(post.getDate().getTime());
        val[1] = date.toString();
        val[2] = post.getContent();
        val[3] = post.getAuthor().getUsername();
        val[4] = "0";
        val[5] = "0";
        int query = this.db.Update(table, col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
    }
    
    public static void main(String args[]) {
        PostData pd = new PostData();
        List<Post> a = pd.getAllPost();
    }
}
