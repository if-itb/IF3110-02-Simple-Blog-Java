package id.ac.itb.informatika.wbd.jpa.controller;

import id.ac.itb.informatika.wbd.jpa.entities.Post;
import id.ac.itb.informatika.wbd.jsf.JsfUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

@ManagedBean
public class PostJpaController extends JpaController {
    
    public void create(Post post){
        try{
            Connection con = getConnection();
            String query = "INSERT INTO Post (title,content,date,published,deleted,featured) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, post.getTitle());            
            ps.setString(2, post.getContent());
            ps.setDate(3, post.getDate());
            ps.setBoolean(4, false);
            ps.setBoolean(5, false);  
            ps.setBoolean(6, false);
            ps.executeUpdate();
            //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void create(Post post, Boolean alternative) throws Exception  {
        
        EntityManager em = null;        
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(post);
            utx.commit();
        } catch (Exception ex) {
            utx.rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Post post) throws Exception {
        EntityManager em = getEntityManager();
        
        try {
            utx.begin();
            //Post persistentPost = em.find(Post.class, post.getId());
            em.joinTransaction();
            post = em.merge(post);
            utx.commit();
        } catch (Exception ex) {
            utx.rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void destroy(Long id) throws Exception  {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            //em.joinTransaction();
             Post post = null;
            try {
                post = em.getReference(Post.class, id);
                post.getId();
            } catch (EntityNotFoundException e) {
                JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            }
            
            //post = em.find(Post.class, id);
            em.remove(post);
            //Query query = em.createQuery("DELETE FROM posts c WHERE c.id = :p");
            //int deletedCount = query.setParameter("p", id).executeUpdate();
            utx.commit();
        } catch (Exception ex) {
            utx.rollback();
            //throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Post> getAllPosts(Boolean alternative) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Post as o order by o.date desc");
            List<Post> entries = q.getResultList();
            if (entries == null) {
                    entries = new ArrayList<Post>();
            }
            return entries;
        } finally {
            em.close();
        }
    }
    
    public List<Post> getAllPosts(){
        List<Post> list = new ArrayList<Post>();
        
         try{
            con = getConnection();
                  
            Statement sm = con.createStatement();
            ResultSet res = sm.executeQuery("SELECT object(o) FROM Post as o ORDER BY o.date DESC");
            
            while(res.next()){
                Post pos = new Post();
                pos.setId(res.getLong("id"));
                pos.setTitle(res.getString("title"));
                pos.setContent(res.getString("contenct"));
                pos.setDate(res.getDate("date"));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        return list;
    }

    public Post findPost(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Post.class, id);
        } finally {
            em.close();
        }
    }
}
