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
            this.db.Where("author=", user.getUsername());
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
            this.db.Where("author=", user.getUsername());
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
        this.db.Where("id=", ""+post.getId());
        String col[] = {"title", "date", "content", "author", "published", "deleted"};
        String val[] = new String[6];
        val[0] = post.getTitle();
        Date date = new Date(post.getDate().getTime());
        val[1] = date.toString();
        val[2] = post.getContent();
        val[3] = post.getAuthor().getUsername();
        if (post.getPublished()) {
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
        this.db.Where("id=", ""+post.getId());
        this.db.Delete(table);
    }
    
    /**
     * Check deleted status on database but not delete post in database
     * @param post deleted post to recycle bin
     * @return string status
     */
    public String softDelPost(Post post) {
        this.db.Where("id=", ""+post.getId());
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
        this.db.Where("id=", "" + post.getId());
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
    
    /**
     * Get all comment in a post
     * @param post the selected post
     * @return All comment in a post
     */
    public List<Comment> getAllCommentonPost(Post post) {
        try {
            this.db.Where("pid=", "" + post.getId());
            ResultSet Data = this.db.Select("comment");
            boolean isExist = Data.first();
            List<Comment> ListComment = new LinkedList();
            while (isExist) {
                int id = Data.getInt("id");
                int pid = Data.getInt("pid");
                String name = Data.getString("name");
                String email = Data.getString("email");
                String content = Data.getString("content");
                Timestamp time = Data.getTimestamp("time");
                Comment comment = new Comment(id, pid, name, email, content, time);
                ListComment.add(comment);
                isExist = Data.next();
            }
            return ListComment;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get all comment in a post
     * @param comment comment in post
     * @return All comment in a post
     */
    public List<CommentofComment> getAllCommentofComment(Comment comment) {
        try {
            this.db.Where("cid=", "" + comment.getID());
            ResultSet Data = this.db.Select("ccomment");
            boolean isExist = Data.first();
            List<CommentofComment> ListCommentofComment = new LinkedList();
            while (isExist) {
                int id = Data.getInt("id");
                int cid = Data.getInt("cid");
                String name = Data.getString("name");
                String email = Data.getString("email");
                String content = Data.getString("content");
                Timestamp time = Data.getTimestamp("time");
                CommentofComment ccomment = new CommentofComment(id, cid, name, email, content, time);
                ListCommentofComment.add(ccomment);
                isExist = Data.next();
            }
            return ListCommentofComment;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Add comment to database
     * @param comment new comment
     * @return string status
     */
    public String addComment(Comment comment) {
        String col[] = {"id", "pid", "name", "email", "content", "time"};
        String val[] = new String[6];
        val[0] = String.valueOf(comment.getID());
        val[1] = String.valueOf(comment.getPID());
        val[2] = comment.getName();
        val[3] = comment.getEmail();
        val[4] = comment.getContent();
        val[5] = comment.getTime().toString();
        int query = this.db.Insert("comment", col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
    }
    
    /**
     * Add comment of comment to database
     * @param comment new comment of comment
     * @return string status
     */
    public String addCommentofComment(CommentofComment comment) {
        String col[] = {"id", "cid", "name", "email", "content", "time"};
        String val[] = new String[6];
        val[0] = String.valueOf(comment.getID());
        val[1] = String.valueOf(comment.getCID());
        val[2] = comment.getName();
        val[3] = comment.getEmail();
        val[4] = comment.getContent();
        val[5] = comment.getTime().toString();
        int query = this.db.Insert("comment", col, val);
        if (query > 0) {
            return "success";
        } else {
            return "failed";
        }
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
    
    public static void main(String args[]) {
        PostData pd = new PostData();
        List<Post> a = pd.getAllPost();
        for (Post p : a)
        {
            System.out.println(p.getAuthor().getUsername().compareTo("luthfi"));
        }
    }
}
