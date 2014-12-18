package Database;

import Model.Comment;
import Model.Post;
import Model.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

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
    private Part part;
    private String upload;
    
    /**
     * Create an instance of PostData
     */
    public PostData() {
        table = "post";
        upload = "";
        db = new MySQL();
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
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
                String image = Data.getString("image");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                return new Post(id, title,image, date, content, author, published, deleted);
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
            this.db.Where("deleted=", "0");
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int id = Data.getInt("id");
                String title = Data.getString("title");
                String image = Data.getString("image");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(id, title,image, date, content, author, published, deleted);
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
            this.db.Where("deleted=", "0");
            this.db.Where("author=", user.getUsername());
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                String image = Data.getString("image");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, image, date, content, author, published, deleted);
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
            this.db.Where("deleted=", "0");
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                String image = Data.getString("image");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, image, date, content, author, published, deleted);
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
            this.db.Where("deleted=", "0");
            this.db.Where("author=", user.getUsername());
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                String image = Data.getString("image");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, image, date, content, author, published, deleted);
                ListPost.add(post);
                isExist = Data.next();
            }
            return ListPost;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Post> getAllDeleted() {
        try {
            this.db.Where("deleted=", "1");
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                String image = Data.getString("image");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, image, date, content, author, published, deleted);
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
    public List<Post> getDeletedbyAuthor(User user) {
        try {
            this.db.Where("deleted=", "1");
            this.db.Where("author=", user.getUsername());
            ResultSet Data = this.db.Select(table);
            boolean isExist = Data.first();
            List<Post> ListPost = new LinkedList();
            while (isExist) {
                int pid = Data.getInt("id");
                String title = Data.getString("title");
                String image = Data.getString("image");
                Date date = Data.getDate("date");
                String content = Data.getString("content");
                User author = new UserData().getUser(Data.getString("author"));
                boolean published = Data.getBoolean("published");
                boolean deleted = Data.getBoolean("deleted");
                Post post = new Post(pid, title, image, date, content, author, published, deleted);
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
    public String addPost(Post post, User author, boolean published) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        String basePath = facesContext.getExternalContext().getRealPath("");
        String image = uploadFile(author.getUsername());
        String col[] = {"title", "image", "date", "content", "author", "published", "deleted"};
        String val[] = new String[7];
        val[0] = post.getTitle();
        val[1] = image;
        Date date = new Date(post.getDate().getTime());
        val[2] = date.toString();
        val[3] = post.getContent();
        val[4] = author.getUsername();
        if (published) {
            val[5] = "1";
        } else {
            val[5] = "0";
        }
        val[6] = "0";
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
    public String editPost(Post post,boolean published) throws IOException {
        if (upload.equals("oke")){
            String image = uploadFile(getPost(post.getId()).getAuthor().getUsername());
            this.db.Where("id=", ""+post.getId());
            String col[] = {"title", "image", "date", "content", "published", "deleted"};
            String val[] = new String[6];
            val[0] = post.getTitle();
            val[1] = image;
            Date date = new Date(post.getDate().getTime());
            val[2] = date.toString();
            val[3] = post.getContent();
            if (published) {
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
        }else {
            this.db.Where("id=", ""+post.getId());
            String col[] = {"title", "date", "content", "published", "deleted"};
            String val[] = new String[5];
            val[0] = post.getTitle();
            Date date = new Date(post.getDate().getTime());
            val[1] = date.toString();
            val[2] = post.getContent();
            if (published) {
                val[3] = "1";
            } else {
                val[3] = "0";
            }
            val[4] = "0";
            int query = this.db.Update(table, col, val);
            if (query > 0) {
                return "success";
            } else {
                return "failed";
            }
        }
    }
    
    /**
     * Delete post from database 
     * @param post deleted post
     */
    public String hardDelPost(Post post) {
        this.db.Where("id=", ""+post.getId());
        this.db.Delete(table);
        return "deleted";
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
    
    public String publishPost(Post post) {
        this.db.Where("id=", "" + post.getId());
        String col[] = {"title", "date", "content", "author", "published", "deleted"};
        String val[] = new String[6];
        val[0] = post.getTitle();
        Date date = new Date(post.getDate().getTime());
        val[1] = date.toString();
        val[2] = post.getContent();
        val[3] = post.getAuthor().getUsername();
        val[4] = "1";
        val[5] = "0";
        int query = this.db.Update(table, col, val);
        if (query > 0) {
            return "published";
        } else {
            return "failed";
        }
    }
    
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String filename = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                int extensionIndex = filename.lastIndexOf(".");
                String fileExtension = filename.substring(extensionIndex + 1);
                System.out.println(filename);
                return fileExtension;
            }
        }
        return null;
    }
    
    public String uploadFile(String username) throws IOException {

        // Extract file name from content-disposition header of file part
        java.util.Date date = new java.util.Date();
        Timestamp time = new Timestamp(date.getTime());
        String fileName = username + "_" + time.toString().replace(' ', '_').replace(':', '-') + "." + getFileName(part);
        System.out.println("***** fileName: " + fileName);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        String basePath = facesContext.getExternalContext().getRealPath("") + File.separator + "upload" + File.separator;
        File outputFilePath = new File(basePath + fileName);

        // Copy uploaded file to destination path
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = part.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);

            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            //statusMessage = "File upload successfull !!";
        } catch (IOException e) {
            e.printStackTrace();
            //statusMessage = "File upload failed !!";
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return fileName;    // return to same page
    }
}
