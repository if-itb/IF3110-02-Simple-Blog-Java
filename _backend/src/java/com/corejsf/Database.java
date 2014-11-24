/*
sepertinya gak bakal kepake
 */

package com.corejsf;

import java.sql.*;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Arina Listyarini DA
 */
@ManagedBean(name = "database")
@RequestScoped
public class Database {
    public Database() {
    }
    
    public Connection getConnection() throws SQLException{
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/simpleblog";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
        }
        return con;
    }
    
    public void addMember(Member1 member) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "INSERT INTO member (Email,Name,Password,Role) VALUES ('" + member.getEmail() + "','" + member.getName() + "','" + member.getPassword() + "','" + member.getRole() + "')";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){} 
    }
    
    public void deleteMember(Member1 member) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "DELETE FROM member WHERE id=" + member.getId();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){}
    }
    
    public void editMember(Member1 member) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "UPDATE member SET Email='" + member.getEmail() + "',Name='" + member.getName() + "',Password='" + member.getPassword() + "',Role='" + member.getRole() + "' WHERE id=" + member.getId();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){} 
    }
    
    public ArrayList<Member1> selectAllMember() throws SQLException, ClassNotFoundException{
        try{
            ArrayList<Member1> members = new ArrayList<>();
            Connection con = getConnection();
            String sql = "SELECT * FROM member";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Member1 member = new Member1();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("Name"));
                member.setEmail(rs.getString("Email"));
                member.setPassword(rs.getString("Password"));
                member.setRole(rs.getString("Role"));
                members.add(member);
            }
            return members;
        } catch(SQLException e){}
        return null;
    }
    
    public void addComment(Comment comment) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "INSERT INTO comment (id_post,Nama,Email,Tanggal,Komentar) VALUES ('" + comment.getIdPost() + "','" + comment.getNama() + "','" + comment.getEmail() + "','" + comment.getTanggal() + "','" + comment.getKomentar() + "')";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){}
    }
    
    public void deleteComment(Comment comment) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "DELETE FROM comment WHERE id=" + comment.getId();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){}
    }
    
    public ArrayList<Comment> selectAllComment() throws SQLException, ClassNotFoundException{
        ArrayList<Comment> comments = new ArrayList<>();
        return comments;
    }
    
    public void addPost(Post post) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "INSERT INTO post (id_member, Status,Judul,Tanggal,Konten) VALUES (1,'" + post.getStatus() + "','" + post.getJudul() + "','" + post.getTanggal() + "','" + post.getKonten() + "')";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){}
    }
    
    public void deletePost(Post post) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "DELETE post, comment FROM post JOIN comment ON post.id=comment.id_post WHERE post.id=" + post.getId();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){}
    }
    
    public void editPost(Post post) throws SQLException, ClassNotFoundException{
        try{
            PreparedStatement ps; 
            Connection con = getConnection();
            String sql = "UPDATE post SET Status='"+post.getStatus()+"', Judul='"+post.getJudul()+"', Tanggal='"+post.getTanggal()+"', Konten='"+post.getKonten()+"'"
                    + "     WHERE id="+post.getId();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){}
    }
    
    public ArrayList<Post> selectAllPost() throws SQLException, ClassNotFoundException{
        ArrayList<Post> posts = new ArrayList<>();
        return posts;
    }
}
