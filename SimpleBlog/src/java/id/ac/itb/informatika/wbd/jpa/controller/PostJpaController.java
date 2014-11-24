/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.informatika.wbd.jpa.controller;

import id.ac.itb.informatika.wbd.jpa.entities.Post;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

public class PostJpaController {
    @Resource
    private UserTransaction utx = null;
    
    @PersistenceUnit(unitName = "SimpleBlogPU")
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Post post) {
        
    }
    
    public void edit(Post post) {
        
    }
    
    public void destroy(Long id) {
        
    }

    public List<Post> getPostList() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Post as o");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
