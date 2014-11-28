package com.blog.simple.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.blog.simple.model.Komen;
import com.blog.simple.model.Posting;

/**
*
* @author ramandika
*/
@ManagedBean
@RequestScoped
public class RequestBean{
   private Connection con;  
   private Statement stmt;  
   private ResultSet result;
   private String judulToBeShow;
   private Date tanggalToBeShow;
   private String commentator;
   private String comment_content;
   private String email;
   private String ID;

   public String getID() {
       return ID;
   }

   public void setID(String ID) {
       this.ID = ID;
   }

   public String getCommentator() {
       return commentator;
   }

   public void setCommentator(String commentator) {
       this.commentator = commentator;
   }

   public String getComment_content() {
       return comment_content;
   }

   public void setComment_content(String comment_content) {
       this.comment_content = comment_content;
   }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }

   public String getJudulToBeShow() {
       return judulToBeShow;
   }
   
   public void RedirectPage(String page)
   {
       ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
       try 
       {
           context.redirect(context.getRequestContextPath() + "/" + page + ".xhtml");
       } 
       catch (IOException ex) 
       {
           System.out.println(ex.getMessage());
       }
   }
   
   public void setJudulToBeShow(String judulToBeShow) {
       this.judulToBeShow = judulToBeShow;
   }

   public Date getTanggalToBeShow() {
       return tanggalToBeShow;
   }

   public void setTanggalToBeShow(Date tanggalToBeShow) {
       this.tanggalToBeShow = tanggalToBeShow;
   }

   public String getKontenToBeShow() {
       return kontenToBeShow;
   }

   public void setKontenToBeShow(String kontenToBeShow) {
       this.kontenToBeShow = kontenToBeShow;
   }
   private String kontenToBeShow;
   
   public void addcomment() throws SQLException{
       Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String ID = params.get("id");
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog","root",""); 
        } catch (ClassNotFoundException e) { 
            System.out.println("Class Not Found"); 
        } catch (SQLException e) { 
            System.out.println("Unable to connect"); 
        }   
            try {
                System.out.println("Ready to do Query");
                String query = "INSERT INTO comment (idpost, commentator, email, comment_content, tanggal) VALUES (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
               
                Date tgl = new Date();
               DateFormat df = new SimpleDateFormat("YYYY-M-dd");
               
                Object values[] = {
                    ID,
                    commentator,
                    email,
                    comment_content,
                    df.format(tgl)
                };
                for(int i = 0; i < values.length; i++)
                    ps.setObject(i+1, values[i]);
               
                int affectedRow = ps.executeUpdate(); 
                if(affectedRow == 0)
                    throw new SQLException("Data insertion failed");
                 
            } catch (SQLException e) {
                throw e;
            }
    }
   
   public void ShowDetailPost(){
       Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
           String ID = params.get("id");
       try {  
  
           Class.forName("com.mysql.jdbc.Driver");  
           con = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog","root","");  
       } catch (ClassNotFoundException e) {  
           System.out.println("Class Not Found");  
       } catch (SQLException e) {  
           System.out.println("Unable to connect");  
       }    
           try {  
               String selectSQL = "SELECT * FROM post WHERE id="+ID;
               PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
               ResultSet res = preparedStatement.executeQuery();  
               if (res.next() && res!=null)
               {
                   ID=res.getString("id");
                   tanggalToBeShow=res.getDate("tanggal");
                   judulToBeShow=res.getString("judul");
                   kontenToBeShow=res.getString("konten");            
               }
                
           } catch (SQLException e) {
               e.printStackTrace();  
           }
           
   }
   public List<Posting> retrieveListPost() throws SQLException {
       List<Posting> kumpulanpost = new ArrayList<Posting>();
       try {  
  
           Class.forName("com.mysql.jdbc.Driver");  
           con = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog","root","");  
       } catch (ClassNotFoundException e) {  
           System.out.println("Class Not Found");  
       } catch (SQLException e) {  
           System.out.println("Unable to connect");  
       }    
           try {  
               String selectSQL = "SELECT * FROM post ORDER BY tanggal DESC";  
               PreparedStatement preparedStatement = con.prepareStatement(selectSQL);  
               ResultSet rs = preparedStatement.executeQuery();  
               
               while(rs.next()){  
                   Posting post=new Posting();
                   post.setId(rs.getInt("id"));
                   post.setTanggal(rs.getDate("tanggal"));
                   post.setJudul(rs.getString("judul"));
                   post.setKonten(rs.getString("konten")); 
                   kumpulanpost.add(post);
               }  
                 
           } catch (SQLException e) {
               e.printStackTrace();  
           }
           return kumpulanpost;
   }
   
   
   
   
   public List<Komen> retrieveListKomen() throws SQLException {
       Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       String ID = params.get("id");
           List<Komen> kumpulankomen = new ArrayList<Komen>();
           try { 

               Class.forName("com.mysql.jdbc.Driver"); 
               con = DriverManager.getConnection("jdbc:mysql://localhost/db_simpleblog","root",""); 
           } catch (ClassNotFoundException e) { 
               System.out.println("Class Not Found"); 
           } catch (SQLException e) { 
               System.out.println("Unable to connect"); 
           }   
               try { 
                   String selectSQL = "SELECT * FROM comment WHERE idpost="+ID; 
                   PreparedStatement preparedStatement = con.prepareStatement(selectSQL); 
                   ResultSet rs = preparedStatement.executeQuery(); 

                   while(rs.next()){ 
                       Komen comment=new Komen();
                       comment.setCommentator(rs.getString("commentator"));
                       comment.setEmail(rs.getString("email"));
                       comment.setComment_content(rs.getString("comment_content"));
                       comment.setTanggal(rs.getString("tanggal").toString());
                       kumpulankomen.add(comment);
                   } 

               } catch (SQLException e) {
                   e.printStackTrace(); 
               }
               return kumpulankomen;
   }

}