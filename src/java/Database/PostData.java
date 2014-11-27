package Database;

import Model.Comment;
import Model.CommentofComment;
import Model.Post;
import Model.PostCategory;
import Model.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
/**
 * Representation of Data Post
 * @author Riva Syafri Rachmatullah
 */
public class PostData {
    private MySQL db;
    
    /**
     * Create an instance of PostData
     */
    public PostData() {
        db = new MySQL();
    }
    
    /**
     * Get post by its id from database
     * @param id the id of post
     * @return an instance of post from database
     */
    public Post getPost(int id) {
        try {
            this.db.openConnection();
            this.db.Where("id=", String.valueOf(id));
            ResultSet Data = this.db.Select("post");
            this.db.closeConnection();
            if (Data.first()) {
                int pid = Data.getInt("id");
                String username = Data.getString("username");
                Integer category_id = Data.getInt("category_id");
                PostCategory category = getCategory(category_id);
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                boolean ispublished = Data.getBoolean("ispublished");
                boolean isdeleted = Data.getBoolean("isdeleted");
                User author = new UserData().getUser(username);
                return new Post(pid, title, category, date, content, author, ispublished, isdeleted);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public PostCategory getCategory(Integer category_id) {
        try {
            this.db.openConnection();
            this.db.Where("id=", category_id.toString());
            ResultSet Data = this.db.Select("post_category");
            this.db.closeConnection();
            if (Data.first()) {
                int categoryid = Data.getInt("id");
                String categoryname = Data.getString("category");
                return new PostCategory(categoryid, categoryname);
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
            this.db.openConnection();
            ResultSet Data = this.db.Select("post");
            this.db.closeConnection();
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String username = Data.getString("username");
                Integer category_id = Data.getInt("category_id");
                PostCategory category = getCategory(category_id);
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                boolean ispublished = Data.getBoolean("ispublished");
                boolean isdeleted = Data.getBoolean("isdeleted");
                User author = new UserData().getUser(username);
                Post post = new Post(pid, title, category, date, content, author, ispublished, isdeleted);
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
    public List<Post> getAllPostonUser(String user) {
        try {
            this.db.openConnection();
            this.db.Where("username=", user);
            ResultSet Data = this.db.Select("post");
            this.db.closeConnection();
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String username = Data.getString("username");
                Integer category_id = Data.getInt("category_id");
                PostCategory category = getCategory(category_id);
                String title = Data.getString("title");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                boolean ispublished = Data.getBoolean("ispublished");
                boolean isdeleted = Data.getBoolean("isdeleted");
                User author = new UserData().getUser(username);
                Post post = new Post(pid, title, category, date, content, author, ispublished, isdeleted);
                ListPost.add(post);
                isExist = Data.next();
            }
            return ListPost;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get all comment in a post
     * @param post_id id of post
     * @return All comment in a post
     */
    public List<Comment> getAllCommentonPost(int post_id) {
        try {
            this.db.openConnection();
            this.db.Where("pid=", String.valueOf(post_id));
            ResultSet Data = this.db.Select("comment");
            this.db.closeConnection();
            boolean isExist = Data.first();
            List<Comment> ListComment = new LinkedList();
            while (isExist) {
                int cid = Data.getInt("id");
                int pid = Data.getInt("pid");
                String name = Data.getString("name");
                String email = Data.getString("email");
                String content = Data.getString("content");
                Timestamp time = Data.getTimestamp("time");
                Comment comment = new Comment(cid, pid, name, email, content, time);
                ListComment.add(comment);
                isExist = Data.next();
            }
            return ListComment;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<PostCategory> getAllCategory() {
        try {
            this.db.openConnection();
            ResultSet Data = this.db.Select("post_category");
            this.db.closeConnection();
            boolean isExist = Data.first();
            List<PostCategory> ListCategory = new LinkedList();
            while (isExist) {
                int id = Data.getInt("id");
                String categoryname = Data.getString("category");
                PostCategory category = new PostCategory(id, categoryname);
                ListCategory.add(category);
                isExist = Data.next();
            }
            return ListCategory;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get all comment in a post
     * @param comment_id id of post
     * @return All comment in a post
     */
    public List<CommentofComment> getAllCommentofComment(int comment_id) {
        try {
            this.db.openConnection();
            this.db.Where("cid=", String.valueOf(comment_id));
            ResultSet Data = this.db.Select("ccomment");
            this.db.closeConnection();
            boolean isExist = Data.first();
            List<CommentofComment> ListCommentofComment = new LinkedList();
            while (isExist) {
                int id = Data.getInt("id");
                int cid = Data.getInt("cid");
                int pid = Data.getInt("pid");
                String name = Data.getString("name");
                String email = Data.getString("email");
                String content = Data.getString("content");
                Timestamp time = Data.getTimestamp("time");
                CommentofComment comment = new CommentofComment(id, cid, pid, name, email, content, time);
                ListCommentofComment.add(comment);
                isExist = Data.next();
            }
            return ListCommentofComment;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Add post to database
     * @param post the new post
     */
    public void addPost(Post post) {
        String col[] = {"id", "username", "category", "title", "date", "content", "ispublished", "isdeleted"};
        String val[] = new String[8];
        val[0] = String.valueOf(post.getID());
        val[1] = post.getAuthor().getUsername();
        val[2] = String.valueOf(post.getCategory().getID());
        val[3] = post.getTitle();
        val[4] = post.getDate().toString();
        val[5] = post.getContent();
        if (post.IsPublished()) {
            val[6] = "1";
        } else {
            val[6] = "0";
        }
        if (post.IsDeleted()) {
            val[7] = "1";
        } else {
            val[7] = "0";
        }
        this.db.Insert("post", col, val);
    }
    
    /**
     * Update post on database
     * @param pid id of post
     * @param post updated post
     */
    public void updatePost(int pid, Post post) {
        this.db.Where("id=", String.valueOf(pid));
        String col[] = {"id", "username", "category", "title", "date", "content", "ispublished", "isdeleted"};
        String val[] = new String[8];
        val[0] = String.valueOf(post.getID());
        val[1] = post.getAuthor().getUsername();
        val[2] = String.valueOf(post.getCategory().getID());
        val[3] = post.getTitle();
        val[4] = post.getDate().toString();
        val[5] = post.getContent();
        if (post.IsPublished()) {
            val[6] = "1";
        } else {
            val[6] = "0";
        }
        if (post.IsDeleted()) {
            val[7] = "1";
        } else {
            val[7] = "0";
        }
        this.db.Update("post", col, val);
    }
    
    /**
     * Delete post from database
     * @param pid 
     */
    public void hardDelPost(int pid) {
        this.db.Where("id=", String.valueOf(pid));
        this.db.Delete("post");
    }
    
    /**
     * Check deleted status on database but not delete post in database
     * @param pid id of post
     */
    public void softDelPost(int pid) {
        Post post = getPost(pid);
        this.db.Where("id=", String.valueOf(pid));
        String col[] = {"id", "username", "category", "title", "date", "content", "ispublished", "isdeleted"};
        String val[] = new String[8];
        val[0] = String.valueOf(post.getID());
        val[1] = post.getAuthor().getUsername();
        val[2] = String.valueOf(post.getCategory().getID());
        val[3] = post.getTitle();
        val[4] = post.getDate().toString();
        val[5] = post.getContent();
        val[6] = "0";
        val[7] = "1";
        this.db.Update("post", col, val);
    }
    
    /**
     * Restore soft deleted post in database
     * @param pid id of post
     */
    public void restorePost(int pid) {
        Post post = getPost(pid);
        this.db.Where("id=", String.valueOf(pid));
        String col[] = {"id", "username", "category", "title", "date", "content", "ispublished", "isdeleted"};
        String val[] = new String[8];
        val[0] = String.valueOf(post.getID());
        val[1] = post.getAuthor().getUsername();
        val[2] = String.valueOf(post.getCategory().getID());
        val[3] = post.getTitle();
        val[4] = post.getDate().toString();
        val[5] = post.getContent();
        val[6] = "0";
        val[7] = "0";
        this.db.Update("post", col, val);
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
