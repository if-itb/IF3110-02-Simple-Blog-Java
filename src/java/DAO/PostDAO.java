/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import java.util.List;
import model.Post;

/**
 *
 * @author pc asus
 */
public interface PostDAO {
    
    /**
     * Returns the user from the database matching the given username, otherwise null.
     * @param username username of the user
     * @return The user from the database matching the given username, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    public Post find(int PID) throws DAOException;
    /**
     *
     * @return all user in database
     * @throws DAOException if something fails at database level
     */
    public List<Post> listPublish() throws DAOException;
    
    public List<Post> listUnPublish() throws DAOException;
    
    public void create(Post post) throws IllegalArgumentException, DAOException;

    /**
     * Update the given user in the database. The username, otherwise it will throw
     * IllegalArgumentException.
     * @param user The user to be updated in the database.
     * @throws IllegalArgumentException If the username is null.
     * @throws DAOException If something fails at database level.
     */
    public void update(Post post) throws IllegalArgumentException, DAOException;

    /**
     * Delete the given user from the database. After deleting, the DAO will set the user to null
     * 
     * @param user The user to be deleted from the database.
     * @throws DAOException If something fails at database level.
     */
    public void delete(Post post) throws DAOException;
}
