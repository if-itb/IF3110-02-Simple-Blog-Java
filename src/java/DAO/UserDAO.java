/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import java.util.List;

//Isi sama JavaBeans kalian di package Model
import model.UserBean;

/**
 *
 * @author pc asus
 */
public interface UserDAO {
    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns the user from the database matching the given username, otherwise null.
     * @param username username of the user
     * @return The user from the database matching the given username, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    public UserBean find(String username) throws DAOException;

    /**
     *
     * @return all user in database
     * @throws DAOException if something fails at database level
     */
    public List<UserBean> list() throws DAOException;

    /**
     * Create the given user in the database. The username must not be null, otherwise it will throw
     * IllegalArgumentException. After creating, the DAO will set the obtained username in the given user.
     * @param user The user to be created in the database.
     * @throws IllegalArgumentException If the username is null.
     * @throws DAOException If something fails at database level.
     */
    public void create(UserBean user) throws IllegalArgumentException, DAOException;

    /**
     * Update the given user in the database. The username, otherwise it will throw
     * IllegalArgumentException.
     * @param user The user to be updated in the database.
     * @throws IllegalArgumentException If the username is null.
     * @throws DAOException If something fails at database level.
     */
    public void update(UserBean user) throws IllegalArgumentException, DAOException;

    /**
     * Delete the given user from the database. After deleting, the DAO will set the user to null
     * 
     * @param user The user to be deleted from the database.
     * @throws DAOException If something fails at database level.
     */
    public void delete(UserBean user) throws DAOException;


}
