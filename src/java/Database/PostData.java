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
        table = table;
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
                User author = new UserData().getUser(Data.getString("username"));
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
                User author = new UserData().getUser(Data.getString("username"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(id, title, date, content, author, published, deleted);
                ListPost.add(post);
                isExist = Data.next();
            }
            return ListPost;
        } catch (Exception e) {
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
                User author = new UserData().getUser(Data.getString("username"));
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
                User author = new UserData().getUser(Data.getString("username"));
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
                User author = new UserData().getUser(Data.getString("username"));
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
     * @param pid id of post
     * @param post updated post
     * @return String status
     */
    public String editPost(int pid, Post post) {
        this.db.Where("id=", String.valueOf(pid));
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
     * @param pid 
     */
    public void hardDelPost(int pid) {
        this.db.Where("id=", String.valueOf(pid));
        this.db.Delete(table);
    }
    
    /**
     * Check deleted status on database but not delete post in database
     * @param pid id of post
     */
    public void softDelPost(int pid) {
        Post post = getPost(pid);
        this.db.Where("id=", String.valueOf(pid));
        String col[] = {"id", "username", "category", "title", "date", "content", "published", "deleted"};
        String val[] = new String[8];
        val[0] = String.valueOf(post.getID());
        val[1] = post.getAuthor().getUsername();
        val[2] = String.valueOf(post.getCategory().getID());
        val[3] = post.getTitle();
        val[4] = post.getDate().toString();
        val[5] = post.getContent();
        val[6] = "0";
        val[7] = "1";
        this.db.Update(table, col, val);
    }
    
    /**
     * Restore soft deleted post in database
     * @param pid id of post
     */
    public void restorePost(int pid) {
        Post post = getPost(pid);
        this.db.Where("id=", String.valueOf(pid));
        String col[] = {"id", "username", "category", "title", "date", "content", "published", "deleted"};
        String val[] = new String[8];
        val[0] = String.valueOf(post.getID());
        val[1] = post.getAuthor().getUsername();
        val[2] = String.valueOf(post.getCategory().getID());
        val[3] = post.getTitle();
        val[4] = post.getDate().toString();
        val[5] = post.getContent();
        val[6] = "0";
        val[7] = "0";
        this.db.Update(table, col, val);
    }
    
    /**
     * Add comment to database
     * @param comment new comment
     */
    public void addComment(Comment comment) {
        String col[] = {"id", "pid", "name", "email", "content", "time"};
        String val[] = new String[6];
        val[0] = String.valueOf(comment.getID());
        val[1] = String.valueOf(comment.getPID());
        val[2] = comment.getName();
        val[3] = comment.getEmail();
        val[4] = comment.getContent();
        val[5] = comment.getTime().toString();
        this.db.Insert("comment", col, val);
    }
    
    public void addCommentofComment(CommentofComment comment) {
        String col[] = {"id", "cid", "pid", "name", "email", "content", "time"};
        String val[] = new String[7];
        val[0] = String.valueOf(comment.getID());
        val[1] = String.valueOf(comment.getCID());
        val[2] = String.valueOf(comment.getPID());
        val[3] = comment.getName();
        val[4] = comment.getEmail();
        val[5] = comment.getContent();
        val[6] = comment.getTime().toString();
        this.db.Insert("ccomment", col, val);
    }
    
    /**
     * Delete comment from database
     * @param id id of comment
     */
    public void DelComment(int id) {
        this.db.Where("id=", String.valueOf(id));
        this.db.Delete("comment");
    }
    
    public void DelCommentofComment (int id) {
        this.db.Where("id=", String.valueOf(id));
        this.db.Delete("ccomment");
    }
}
