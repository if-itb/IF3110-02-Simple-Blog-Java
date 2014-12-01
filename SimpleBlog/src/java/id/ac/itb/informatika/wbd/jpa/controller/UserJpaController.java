package id.ac.itb.informatika.wbd.jpa.controller;

import id.ac.itb.informatika.wbd.jpa.entities.Post;
import id.ac.itb.informatika.wbd.jsf.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

@ManagedBean
public class UserJpaController {
    @Resource
    private UserTransaction utx = null;
    
    @PersistenceUnit(unitName = "SimpleBlogPU")
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Post post) throws Exception {
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

    public List<Post> getAllPosts() {
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

    public Post findPost(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Post.class, id);
        } finally {
            em.close();
        }
    }
}
