/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

//Isi sama JavaBeans kalian di package Model
import java.util.List;
import model.Komentar;

/**
 *
 * @author pc asus
 */
public interface CommentDAO {
    // Actions ------------------------------------------------------------------------------------

    
    public Komentar find(int ID) throws DAOException;

   
    public List<Komentar> list(int PID) throws DAOException;

    
    public void create(Komentar komentar) throws IllegalArgumentException, DAOException;

   
}