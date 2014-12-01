package id.ac.itb.informatika.wbd.jpa.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

@ManagedBean
public class JpaController {  
    @Resource
    protected UserTransaction utx = null;
    
    @PersistenceUnit(unitName = "SimpleBlogPU")
    protected EntityManagerFactory emf = null;
    
    protected Connection con = null;
    
    public JpaController() {
    }
    
    public Connection getConnection() throws SQLException{
        
        //if (con == null) {
            String url = "jdbc:mysql://localhost:3306/simple_blog";
            String user = "root";
            String password = "";
            try {
                con = DriverManager.getConnection(url, user, password);
                //System.out.println("Connection completed.");
            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }
            finally{
            }
        //}
        return con;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
