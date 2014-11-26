/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Database.DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevinyu
 */
public class Comments {
    private static Comments instance;
    
    private Comments() {
        
    }
    
    public static Comments getInstance() {
        if (instance==null){
            instance = new Comments();
        }
        return instance;
    }
    
    public void addComment(Comment comment) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String idUser = "" + comment.getCreatorId() +"";
        String idPost = "" + comment.getPostId() +"";
        String isGuest = comment.isGuest() ? "TRUE" : "FALSE" ;
        ArrayList<String> columns = null;
        ArrayList<String> values = null;
        if (comment.isGuest()) {
            columns = new ArrayList<String>(Arrays.asList("Nama","Tanggal","Komentar","Id_Post","Id_User","Email","Guest"));
            values = new ArrayList<String>(Arrays.asList("'"+comment.getName()+"'",
                "'"+comment.getTimeString()+"'","'"+comment.getText()+"'",idPost,idUser,"'"+
                        comment.getEmail()+"'",isGuest));
        }
        else {
            columns = new ArrayList<String>(Arrays.asList("Nama","Tanggal","Komentar","Id_Post","Id_User","Email","Guest"));
            values = new ArrayList<String>(Arrays.asList("''","'"+comment.getTimeString()+"'","'"+comment.getText()+"'",
                    idPost,idUser,"''",isGuest));
        }
        
        try {
            databaseAccess.insertRecords("komentar", columns, values);
        } catch (Exception ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Comment> getCommentByPostId(int postId) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance();
        String condition = "Id_Post="+postId; 
        ResultSet resultSet = null;
        
        try {
            resultSet = databaseAccess.selectAllRecords("komentar", condition);
            }
            catch (SQLException e1) {
			System.out.println(e1);
            }
        if (resultSet!=null){
            try 
            {
                while (resultSet.next()){
                    Comment comment;
                    boolean isGuest = resultSet.getBoolean("Guest");
                    int commentId = resultSet.getInt("Id_Komentar");
                    Timestamp date = resultSet.getTimestamp("Tanggal");
                    String text = resultSet.getString("Komentar");
                    int postingId = resultSet.getInt("Id_Post");
                    int userId = resultSet.getInt("Id_User");
                    String nama,email;
                    nama = resultSet.getString("Nama");
                    email = resultSet.getString("Email");
                    System.out.println("Nama :"+nama);
                    System.out.println("Email :"+email);
                    comments.add(new Comment(commentId,postingId,userId,email,text,date,nama,isGuest));
                }
            }

            catch (SQLException e1) {
                    e1.printStackTrace();
            }
        }
        for (Comment com:comments) {
                   
            if (!com.isGuest()) {
                User user = Users.getInstance().findUser(com.getCreatorId());
                String nama = user.getUsername();
                String email = user.getEmail();
                com.setName(nama);
                com.setEmail(email);
            }
        }
        return comments;
    }
    
    public void deleteCommentByPostId(int postId) {
        DatabaseAccess dbManager = DatabaseAccess.getInstance();
        String tableName = "komentar";
        String condition = "Id_Post="+postId;
        try {
            dbManager.deleteRecords("komentar", condition);
        } catch (SQLException ex) {
            Logger.getLogger(Comments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
