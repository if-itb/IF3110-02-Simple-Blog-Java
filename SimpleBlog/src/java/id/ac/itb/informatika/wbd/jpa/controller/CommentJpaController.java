package id.ac.itb.informatika.wbd.jpa.controller;

import id.ac.itb.informatika.wbd.jpa.entities.Comment;
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

public class CommentJpaController extends JpaController {
    
    public void create(Comment comment) throws Exception {
        EntityManager em = null;
        
        //comment = new Comment();
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(comment);
            utx.commit();
        } catch (Exception ex) {
            utx.rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comment> getAllComments() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Comment as o order by o.date desc");
            List<Comment> entries = q.getResultList();
            if (entries == null) {
                    entries = new ArrayList<Comment>();
            }
            return entries;
        } finally {
            em.close();
        }
    }
    
    public List<Comment> getPostComments(Long id) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Comment as o where o.post = :id order by o.date asc");
            List<Comment> entries = q.setParameter("id", id).getResultList();;
            if (entries == null) {
                    entries = new ArrayList<Comment>();
            }
            return entries;
        } finally {
            em.close();
        }
    }

    public Comment findComment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comment.class, id);
        } finally {
            em.close();
        }
    }
}
