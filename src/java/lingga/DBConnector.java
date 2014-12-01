/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lingga;

/**
 *
 * @author Jelink
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dbconnector",eager=true)
@SessionScoped
public class DBConnector {
    String url = "jdbc:mysql://localhost:3306/simple_blog_db";
    String user = "root";
    String password = "";
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String driver = "com.mysql.jdbc.Driver";
    
    public DBConnector(){
	try{
	    Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
	} catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ClassNotFoundException e){
	    e.printStackTrace();
        }
    }
    
    //Verifikasi Login
    public String verify(User pengguna, CookieHandler ch){
	try {
	    st = con.createStatement();
	    String username = pengguna.getName();
	    String userpass = pengguna.getPassword();
	    String query = "SELECT * FROM `sb_users` WHERE username='" + username + "' && password='" + userpass + "';";
	    System.out.println(query);
	    rs = st.executeQuery(query);
	    if (rs.next()) {
		// Handle user
		pengguna.setUserid(rs.getInt("user_id"));
		pengguna.setLoggedon(true);
		pengguna.setName(username);
		pengguna.setType(rs.getInt("type"));
		pengguna.setEmail(rs.getString("email"));
		//System.out.println("masuk");
		// Handle cookie

		ch.setCookie("if3110_sb_uid", Integer.toString(pengguna.getUserid()), 3600);
		return "";
	    }
	    else if(ch.getCookie("if3110_sb_uid")!=null){
		int sb_uid = Integer.parseInt(ch.getCookie("if3110_sb_uid").getValue());
		//System.out.println("uid : " + sb_uid);
		pengguna.copy(selectUser(sb_uid));
		pengguna.setLoggedon(true);
		return "";
	    }
	    else {
		pengguna.setLoggedon(true);
		pengguna.setName("Guest");
		pengguna.setEmail("");
		pengguna.setType(4);
		return "";
	    }

	} catch (SQLException ex) {
	    Logger lgr = Logger.getLogger(DBConnector.class.getName());
	    lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    return "Gagal Masuk SQLException";

	} catch (Throwable e){
	    return "Gagal Masuk Driver";
	}
    }
    
    /* Mengambil salah satu isi post */
    public Post selectPost(int pid){
        try {
            st = con.createStatement();
            String query = "SELECT * FROM `sb_posts` WHERE `id_post`=" + pid;
            rs = st.executeQuery(query);
            rs.next();
	    String judul = rs.getString("judul");
	    String tanggal = rs.getString("tanggal");
	    String konten = rs.getString("konten");
	    boolean published = (rs.getInt("published")==1);
	    return new Post(pid,judul,tanggal,konten,published);

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    lgr.log(Level.SEVERE, "nana", "lala");
            return null;

        } catch (Throwable e){
            return null;
        }
    }
    
    /* Mengambil salah satu user */
    public User selectUser(int uid){
        try {
            st = con.createStatement();
            String query = "SELECT * FROM `sb_users` WHERE `user_id`=" + uid;
	    System.out.println(query);
            rs = st.executeQuery(query);
            if(rs.next()){
		String username = rs.getString("username");
		String userpass = rs.getString("password");
		String email = rs.getString("email");
		int type = rs.getInt("type");
		System.out.println("select "+username+" "+userpass+" "+email+" "+type);
		return new User(uid,username,userpass,email,type);
	    }
	    else return null;

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    lgr.log(Level.SEVERE, "nana", "lala");
            return null;

        } catch (Throwable e){
            return null;
        }
    }
    
    /* Menambahkan Post ke Database */
    public String insertPost(User pengguna, Post p){
	if(pengguna.isLoggedon() && (pengguna.getType()==1 || pengguna.getType()==3)){
	    try {
		String query = "INSERT INTO `sb_posts`(`judul`, `tanggal`, `konten`, `published`) VALUES ('" + p.getJudul() + "','" + p.getTanggal() + "','" + p.getKonten() + "','0')";
		int userrole = pengguna.getType();
		System.out.println(query);
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "Unpublished_Posts.xhtml?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* Menambahkan User ke Database */
    public String insertUser(User pengguna, User p){
	if(pengguna.isLoggedon() && pengguna.getType()==3){
	    try {
		st = con.createStatement();
		String query = "SELECT COUNT(username) AS c FROM `sb_users` WHERE `username`='" + p.getName() + "'";
		//System.out.println(query);
		rs = st.executeQuery(query);
		rs.next();
		System.out.println(" >>" + rs.getString("c") + "<<");
		if(rs.getString("c").equals("0")){
		    query = "INSERT INTO `sb_users`(`username`, `password`, `email`, `type`) VALUES ('" + p.getName() + "','" + p.getPassword() + "','" + p.getEmail() + "','" + p.getType() + "')";

		    System.out.println(query);
		    pst = con.prepareStatement(query);
		    pst.executeUpdate();
		    return "User_Management.xhtml?faces-redirect=true";
		}
		else return "?faces-redirect=true&success=no";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	    System.out.println("bocor");
	}
	return null;
    }
    
    /* Menambahkan Komentar ke Database */
    public void insertComment(User pengguna, Comment c){
	try {
	    String query = "INSERT INTO `sb_comments`(`nama`, `email`, `komentar`, `id_post`) VALUES ('" + c.getNama() + "','" + c.getEmail() + "','" + c.getKomentar() + "','" + c.getPost_id() + "')";
	    System.out.println(query);
	    pst = con.prepareStatement(query);
	    pst.executeUpdate();
	    //return "Post.xhtml?pid=" + c.getPost_id() + "&faces-redirect=true";
	} catch (SQLException ex) {
	    Logger lgr = Logger.getLogger(DBConnector.class.getName());
	    lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    System.out.println("sqlex");
	} catch (Throwable e){
	    System.out.println("throw");
	}
	c.init(pengguna);
	//return null;
    }
    
    /* Mengupdate Post di Database */
    public String updatePost(User pengguna, Post p){
	if(pengguna.isLoggedon() && pengguna.getType()<=3){
	    try {
		String query = "UPDATE `sb_posts` SET `judul`='" + p.judul +"', `tanggal`='" + p.tanggal + "', `konten`='" + p.konten + "' WHERE `id_post`=" + p.id_post + ";";
		System.out.println(query);
		int userrole = pengguna.getType();
		System.out.println(query);
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "Published_Posts?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* Mengupdate User di Database */
    public String updateUser(User admin, User p){
	if(admin.isLoggedon() && admin.getType()==3){
	    try {
		String query = "UPDATE `sb_users` SET `username`='" + p.getName() +"', `password`='" + p.getPassword() +"', `email`='" + p.getEmail() + "', `type`='" + p.getType() + "' WHERE `user_id`=" + p.getUserid() + ";";
		//System.out.println(query);
		//System.out.println(query);
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "faces/User_Management.xhtml?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* Mendelete User di Database */
    public String deleteUser(User admin, User p){
	if(admin.isLoggedon() && admin.getType()==3){
	    try {
		String query = "DELETE FROM `sb_users` where `user_id`='" + p.getUserid() + "';";
		System.out.println(query);
		System.out.println(query);
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "faces/User_Management.xhtml?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* Mendelete Post di Database */
    public String deletePost(User pengguna, int pid){
	if(pengguna.isLoggedon() && pengguna.getType()<=3){
	    try {
		String query = "DELETE FROM `sb_posts` where `id_post`='" + pid + "';";
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "faces/Published_Posts.xhtml?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* Mempublish Post */
    public String publishPost(User pengguna, Post p){
	if(pengguna.isLoggedon() && (pengguna.getType()==3 || pengguna.getType()==2)){
	    try {
		String query = "UPDATE `sb_posts` SET `published`=1 WHERE `id_post`=" + p.id_post + ";";
		System.out.println(query);
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "Published_Posts?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* SoftDelete Post */
    public String softDeletePost(User pengguna, int pid){
	if(pengguna.getType()!=4){
	    try {
		String query = "UPDATE `sb_posts` SET `published`=2 WHERE `id_post`=" + pid + ";";
		System.out.println(query);
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "Published_Posts?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* Restore Post */
    public String restorePost(User pengguna, int pid){
	if(pengguna.getType()==3){
	    try {
		String query = "UPDATE `sb_posts` SET `published`=0 WHERE `id_post`=" + pid + ";";
		System.out.println(query);
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		return "Published_Posts?faces-redirect=true";
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(DBConnector.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    } catch (Throwable e){
	    }
	}
	return null;
    }
    
    /* Mengambil seluruh isi post */
    public ArrayList<Post> listPost(String pub){
        try {
            st = con.createStatement();
	    int pubs=0;
	    if(pub=="true") pubs=1;
	    else if(pub=="false") pubs=0;
	    else if(pub=="deleted") pubs=2;
            String query = "SELECT * FROM `sb_posts` WHERE `published`=" + pubs + " ORDER BY `tanggal` DESC";
	    System.out.println(query);
            rs = st.executeQuery(query);
            ArrayList<Post> retval = new ArrayList<Post>();
            while (rs.next()) {
                int id_post = rs.getInt("id_post");
                String judul = rs.getString("judul");
                String tanggal = rs.getString("tanggal");
                String konten = rs.getString("konten");
                boolean published = (rs.getInt("published")==1);
                retval.add(new Post(id_post, judul, tanggal, konten, published));
            }
            return retval;

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    lgr.log(Level.SEVERE, "nana", "lala");
            return null;

        } catch (Throwable e){
            return null;
        }
    }
    
    /* Mengambil seluruh isi komentar pos ini */
    public ArrayList<Comment> listComment(int pid){
        try {
            st = con.createStatement();
            String query = "SELECT * FROM `sb_comments` WHERE `id_post`=" + pid + " ORDER BY `timestamp` DESC";
	    //System.out.println(query);
            rs = st.executeQuery(query);
            ArrayList<Comment> retval = new ArrayList<Comment>();
            while (rs.next()) {
                int id_komentar = rs.getInt("id_komentar");
                String nama = rs.getString("nama");
                String email = rs.getString("email");
                String komentar = rs.getString("komentar");
		String timestamp = rs.getString("timestamp");
                retval.add(new Comment(id_komentar, nama, email, komentar, timestamp, pid));
            }
            return retval;

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    lgr.log(Level.SEVERE, "nana", "lala");
            return null;

        } catch (Throwable e){
            return null;
        }
    }
    
    /* Crud */
    public ArrayList<User> listUser(User pengguna){
	if(pengguna.isLoggedon() && pengguna.getType()==3){
	    if(pengguna.getType()==3){
		try {
		    st = con.createStatement();
		    String query = "SELECT * FROM `sb_users`";
		    System.out.println(query);
		    rs = st.executeQuery(query);
		    ArrayList<User> retval = new ArrayList<User>();
		    while (rs.next()) {
			int userid = rs.getInt("user_id");
			String name = rs.getString("username");
			String pass = rs.getString("password");
			String email = rs.getString("email");
			int type = rs.getInt("type");
			retval.add(new User(userid, name, pass, email, type));
		    }
		    return retval;
		} catch (SQLException ex) {
		    Logger lgr = Logger.getLogger(DBConnector.class.getName());
		    lgr.log(Level.SEVERE, ex.getMessage(), ex);
		    System.out.println("sqlex");
		} catch (Throwable e){
		    System.out.println("e");
		}
	    }
	}
	return null;
    }
}