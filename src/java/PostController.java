/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import java.lang.Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Bagaskara Pramudita
 */
@ManagedBean(name="postController")
@RequestScoped
public class PostController {
    private ArrayList<Post> posts;
    private ArrayList<Post> unpubPosts;
    private ArrayList<Post> deletedPosts;
    private ArrayList<Comment> comments;
    private int curpid;
    private Connection connect;
    private ResultSet resultset;
    private Statement statement;
    private boolean isEdit;

    public int getCurpid() {
        return curpid;
    }

    public void setCurpid(int curpid) {
        this.curpid = curpid;
    }
    
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    private Post specifiedPost;

    public Post getSpecifiedPost() {
        return specifiedPost;
    }

    public void setSpecifiedPost(Post specifiedPost) {
        this.specifiedPost = specifiedPost;
    }
    
    public Post getSpecifiedPost(int targetid) {
        System.out.println("fetching post id="+targetid);
        Post outpost;
        String njudul, nkonten, ntanggal;
        int nid, nstatus;
        outpost = new Post(-1, "","","");
        curpid = targetid;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            statement = connect.createStatement();
            resultset=statement.executeQuery("select * from post where pid="+targetid);
            if(resultset.next()){
                    njudul = resultset.getString("judul");
                    nkonten = resultset.getString("konten");
                    ntanggal = resultset.getString("tanggal");
                    nid = resultset.getInt("pid");
                    nstatus = resultset.getInt("status");
                    outpost = new Post(nid, njudul,ntanggal,nkonten,nstatus);
            }
            connect.close();
        }
        catch(Exception e){
        }
        if(outpost.getId()==-1){
            isEdit=false;
            System.out.println("isEdit="+isEdit);
        }
        else{
            isEdit=true;
            System.out.println("isEdit="+isEdit);
        }
        return outpost;
    }
    
    public String UnpubCheck(int stat){
        if (stat==0){
            return "block;";
        }
        else{
            return "none;";
        }
    }
    
    public String isLoggedOn(){
        boolean loggedon = false;
        if (loggedon){
            return "#UserNav";
        }
        else{
            return "#LogIn";
        }
    }
    
    public String NewPost(Post P){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver passed");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            System.out.println("connection established");
            System.out.println("judul="+P.getJudul()+", konten="+P.getKonten());
            //PreparedStatement pst = connect.prepareStatement("INSERT INTO `post` (`judul`, `tanggal`, `konten`, `status`) VALUES ('"+P.getJudul()+"','"+P.getTanggal()+ "','"+P.getKonten()+"','0')");
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `post` (`judul`, `tanggal`, `konten`, `status`) VALUES (?,?,?,?)");
            pst.setString(1, P.getJudul());
            pst.setString(2, P.getTanggal());
            pst.setString(3, P.getKonten());
            pst.setInt(4, 0);
            System.out.println("statement prepared");
            pst.executeUpdate();
            System.out.println("update executed");
            connect.close();
       }
        catch(Exception e){
            System.out.println("masuk exception");
        }
        return"unpublished.xhtml?redirect=true";
    }

    /**
     * Creates a new instance of PostList
     */
    public PostController() {
    }
    
    public ArrayList<Post> getPosts(){
        String njudul, nkonten, ntanggal;
        int nid;
        ArrayList<Post> outposts = new ArrayList<>();
        Post outpost;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            statement = connect.createStatement();
            resultset=statement.executeQuery("select * from post where status=1 order by pid DESC");
            while (resultset.next()){
                njudul = resultset.getString("judul");
                ntanggal = resultset.getString("tanggal");
                nkonten = resultset.getString("konten");
                nid = resultset.getInt("pid");
                outpost = new Post(nid, njudul,ntanggal,nkonten);
                outposts.add(outpost);
            }
            AbandonedConnectionCleanupThread.shutdown();
            connect.close();
        }
        catch(Exception e){
            outpost = new Post(-1,"exception alert","","exception encountered");
            outposts.add(outpost);
        }
        return outposts;
    }
    
    public ArrayList<Post> getUnpubPosts(){
        String njudul, nkonten, ntanggal;
        int nid;
        ArrayList<Post> outposts = new ArrayList<>();
        Post outpost;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            statement = connect.createStatement();
            resultset=statement.executeQuery("select * from post where status=0 order by pid DESC");
            while (resultset.next()){
                njudul = resultset.getString("judul");
                ntanggal = resultset.getString("tanggal");
                nkonten = resultset.getString("konten");
                nid = resultset.getInt("pid");
                outpost = new Post(nid, njudul,ntanggal,nkonten);
                outposts.add(outpost);
            }
            AbandonedConnectionCleanupThread.shutdown();
            connect.close();
        }
        catch(Exception e){
            outpost = new Post(-1,"exception alert","","exception encountered");
            outposts.add(outpost);
        }
        return outposts;
    }
    
    public ArrayList<Post> getDeletedPosts(){
        String njudul, nkonten, ntanggal;
        int nid;
        ArrayList<Post> outposts = new ArrayList<>();
        Post outpost;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            statement = connect.createStatement();
            resultset=statement.executeQuery("select * from post where status=2 order by pid DESC");
            while (resultset.next()){
                njudul = resultset.getString("judul");
                ntanggal = resultset.getString("tanggal");
                nkonten = resultset.getString("konten");
                nid = resultset.getInt("pid");
                outpost = new Post(nid, njudul,ntanggal,nkonten);
                outposts.add(outpost);
            }
            AbandonedConnectionCleanupThread.shutdown();
            connect.close();
        }
        catch(Exception e){
            outpost = new Post(-1,"exception alert","","exception encountered");
            outposts.add(outpost);
        }
        return outposts;
    }
    
    public ArrayList<Comment> getComments(int pid){
        System.out.println("Fetching comments for post pid="+pid);
        String nnama, nemail, nkomen, ntanggal;
        Comment outcomment;
        ArrayList<Comment> outcomments = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            statement = connect.createStatement();
            resultset=statement.executeQuery("select * from comment where pid="+pid+" order by tglkomen desc");
            while (resultset.next()){
                nnama = resultset.getString("nama");
                nkomen = resultset.getString("komen");
                nemail = resultset.getString("email");
                ntanggal = resultset.getString("tglkomen");
                outcomment = new Comment(nnama, ntanggal, nemail,nkomen);
                outcomments.add(outcomment);
            }
            AbandonedConnectionCleanupThread.shutdown();
            connect.close();
        }
        catch(Exception e){
            System.out.println("exception thrown");
        }
        return outcomments;
    }
    
    public void RemovePost(int pid){
        System.out.println("prepare to delete post pid="+pid);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver passed");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            System.out.println("connection established");
            PreparedStatement pst = connect.prepareStatement("DELETE FROM `post` WHERE `pid` = "+pid);            
            System.out.println("statement prepared");
            pst.executeUpdate();
            System.out.println("update executed");
            connect.close();
       }
        catch(Exception e){
            System.out.println("exception encountered");
        }
    }
    
    public void PostComment(Comment C, int pid){
        try{
            System.out.println("Posting comment on pid="+pid);
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver passed");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            System.out.println("connection established");
            System.out.println("judul="+C.getNama()+", konten="+C.getKomen());
            //PreparedStatement pst = connect.prepareStatement("INSERT INTO `comment` (`pid`, `nama`, `email`, `komen`) VALUES ('"+pid+"','"+C.getNama()+"','"+C.getEmail()+ "','"+C.getKomen()+"')");            
            PreparedStatement pst = connect.prepareStatement("INSERT INTO `comment` (`pid`, `nama`, `email`, `komen`) VALUES ('"+pid+"',?,?,?)");            
            pst.setString(1, C.getNama());
            pst.setString(2, C.getEmail());
            pst.setString(3, C.getKomen());
            System.out.println("statement prepared");
            pst.executeUpdate();
            System.out.println("update executed");
            connect.close();
       }
        catch(Exception e){
            System.out.println("masuk exception");
        }
    }
    
    public String EditPost(Post P){
        try{
            System.out.println("preparing to edit record, pid="+P.getId());
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver passed");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            System.out.println("connection established");
            System.out.println("pid="+P.getId()+"judul="+P.getJudul()+", konten="+P.getKonten());
            //PreparedStatement pst = connect.prepareStatement("UPDATE `post` SET `judul`='"+P.getJudul()+"', `tanggal`='"+P.getTanggal()+"', `konten`='"+P.getKonten()+"' WHERE `pid`="+P.getId()+";");
            PreparedStatement pst = connect.prepareStatement("UPDATE `post` SET `judul`=?, `tanggal`=?, `konten`=? WHERE `pid`="+P.getId()+";");
            pst.setString(1, P.getJudul());
            pst.setString(2, P.getTanggal());
            pst.setString(3, P.getKonten());            
            System.out.println("statement prepared");
            pst.executeUpdate();
            System.out.println("update executed");
            connect.close();
       }
        catch(Exception e){
            System.out.println("exception thrown");
           System.out.println(e.getStackTrace()); 
        }
        if(P.getStatus()==0){
            return "unpublishedpost.xhtml?pid="+P.getId()+"&faces-redirect=true";
        }
        else{
            return "post.xhtml?pid="+P.getId()+"&faces-redirect=true";
        }
        //return "index";
    }

    public void SoftDeletePost(int pid){
        System.out.println("preparing to edit record, pid="+pid);
        try{    
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver passed");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            System.out.println("connection established");
            System.out.println("deleting pid="+pid);
            PreparedStatement pst = connect.prepareStatement("UPDATE `post` SET `status`='"+2+"' WHERE `pid`="+pid+";");
            System.out.println("statement prepared");
            pst.executeUpdate();
            System.out.println("update executed");
            connect.close();
            //ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            //ext.redirect("index.xhtml");
        }
        catch(Exception e){
        }
    }
    
    public String PublishPost(int pid, boolean restore){
        System.out.println("preparing to publish post, pid="+pid);
        try{    
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver passed");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/wbdsimpleblog", "root","");
            System.out.println("connection established");
            System.out.println("publishing pid="+pid);
            PreparedStatement pst;
            if(restore){
                pst = connect.prepareStatement("UPDATE `post` SET `status`='"+0+"' WHERE `pid`="+pid+";");
            }
            else{
                pst = connect.prepareStatement("UPDATE `post` SET `status`='"+1+"' WHERE `pid`="+pid+";");
            }
            System.out.println("statement prepared");
            pst.executeUpdate();
            System.out.println("update executed");
            connect.close();
        }
        catch(Exception e){
        }
        if (restore){
            return "unpublished.xhtml?faces-redirect=true?";
        }
        else{
            return "post.xhtml?pid="+pid+"&faces-redirect=true";
        }
    }
       
    public String DelEditpublishedpost(String usertype){
        System.out.println("checking user type, user type:"+usertype);
        if(usertype.equals("Owner") || usertype.equals("Admin")){
            return "block";
        }
        else{
            return "none";
        }
    }
}
