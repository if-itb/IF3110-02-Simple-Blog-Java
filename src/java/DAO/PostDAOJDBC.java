/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static DAO.DAOUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;

/**
 *
 * @author pc asus
 */
public class PostDAOJDBC implements PostDAO{
    // Constants ----------------------------------------------------------------------------------

    private static final String SQL_FIND_BY_ID =
        "SELECT * FROM entries WHERE PID = ?";
    
    private static final String SQL_LIST_ORDER_BY_PID=
        "SELECT * FROM entries ORDER BY PID";
    
    private static final String SQL_LIST_PUBLISHED_POST=
        "SELECT * FROM entries WHERE PUBLISHED=1 ORDER BY TANGGAL DESC";
    private static final String SQL_LIST_UNPUBLISHED_POST=
        "SELECT * FROM entries WHERE PUBLISHED=0 ORDER BY TANGGAL DESC";
    
    private static final String SQL_INSERT =
        "INSERT INTO Entries (judul,konten,tanggal,author,pid,published) VALUES (?, ?, ?, ?,?,?)";
    private static final String SQL_UPDATE =
        "UPDATE Entries SET judul = ?, konten = ?, tanggal = ?, published = ?  WHERE pid = ?";
    private static final String SQL_DELETE =
        "DELETE FROM Entries WHERE pid = ?";

    // Vars ---------------------------------------------------------------------------------------

    private DAOFactory daoFactory;

    // Constructors -------------------------------------------------------------------------------

    /**
     * Construct an User DAO for the given DAOFactory. Package private so that it can be constructed
     * inside the DAO package only.
     * @param daoFactory The DAOFactory to construct this User DAO for.
     */
    PostDAOJDBC(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // Actions ------------------------------------------------------------------------------------
    @Override
    public Post find(int PID) throws DAOException {
        return find(SQL_FIND_BY_ID, PID);
    }
    /**
     * Returns the user from the database matching the given SQL query with the given values.
     * @param sql The SQL query to be executed in the database.
     * @param values The PreparedStatement values to be set.
     * @return The user from the database matching the given SQL query with the given values.
     * @throws DAOException If something fails at database level.
     */
    private Post find(String sql, Object... values) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Post user = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = prepareStatement(connection, sql, false, values);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public List<Post> listPublish() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Post> posts = new ArrayList<Post>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_LIST_PUBLISHED_POST);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }

        return posts;
    }
    
    @Override
    public List<Post> listUnPublish() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Post> posts = new ArrayList<Post>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_LIST_UNPUBLISHED_POST);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }

        return posts;
    }
    
    @Override
public void create(Post post) throws IllegalArgumentException, DAOException {
	Object[] values = {
		post.getJudul(),
		post.getKonten(),
		post.getDate(),
		post.getAuthor(),
		post.getPID(),
                0
	};
        
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet generatedKeys = null;
	try{
		connection = daoFactory.getConnection();
		preparedStatement = prepareStatement(connection, SQL_INSERT, true, values);
		int affectedRows = preparedStatement.executeUpdate();
		if (affectedRows == 0){
			throw new DAOException("Creating user failed, no rows affected.");
		}
	} catch (SQLException e) {
		throw new DAOException(e);
	} finally {
		close(connection, preparedStatement, generatedKeys);
	}
}

@Override
public void update(Post post) throws DAOException {
	Object[] values = {
		post.getJudul(),
		post.getKonten(),
		post.getDate(),
                post.isPublished(),
                post.getPID()
                
	};

	Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = prepareStatement(connection, SQL_UPDATE, false, values);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement,generatedKeys);
        }
}

 @Override
    public void delete(Post post) throws DAOException {
       Object[] values = {
		post.getPID()
		};

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = prepareStatement(connection, SQL_DELETE, false, values);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement);
        }
    }
    // Helpers ------------------------------------------------------------------------------------

    /**
     * Map the current row of the given ResultSet to an User.
     * @param resultSet The ResultSet of which the current row is to be mapped to an User.
     * @return The mapped User from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static Post map(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setJudul(resultSet.getString("judul"));
        post.setKonten(resultSet.getString("konten"));
        post.setDate(resultSet.getString("tanggal"));
        post.setAuthor(resultSet.getString("author"));
        post.setPID(resultSet.getInt("pid"));
        post.setPublished(resultSet.getBoolean("published"));
        return post;
    }
}
