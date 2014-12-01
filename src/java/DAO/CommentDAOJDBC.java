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

import model.Komentar;
/**
 *
 * @author pc asus
 */
public class CommentDAOJDBC implements CommentDAO {
    
    // Constants ----------------------------------------------------------------------------------

    private static final String SQL_FIND_BY_KID =
        "SELECT * FROM komentar WHERE KID = ?";
    private static final String SQL_FIND_BY_PID =
        "SELECT * FROM komentar WHERE PID = ? ORDER BY TANGGAL DESC";
    private static final String SQL_INSERT =
        "INSERT INTO komentar (PID, NAMA, EMAIL, TANGGAL, KOMENTAR) VALUES (?, ?, ?, ?, ?)";
 
    // Vars ---------------------------------------------------------------------------------------

    private DAOFactory daoFactory;

    // Constructors -------------------------------------------------------------------------------

    CommentDAOJDBC(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // Actions ------------------------------------------------------------------------------------

    @Override
    public Komentar find(int PID) throws DAOException {
        return find(SQL_FIND_BY_KID, PID);
    }

    private Komentar find(String sql, Object... values) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Komentar komentar = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = prepareStatement(connection, sql, false, values);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                komentar = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }

        return komentar;
    }

    @Override
    public List<Komentar> list(int PID) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Komentar> komentar = new ArrayList<Komentar>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = prepareStatement(connection, SQL_FIND_BY_PID, false, PID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                komentar.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }

        return komentar;
    }

    @Override
    public void create(Komentar komentar) throws DAOException {
        
        Object[] values = {
            komentar.getPID(),
            komentar.getNama(),
            komentar.getEmail(),
            komentar.getTanggal(),
            komentar.getKomentar()
        };

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = prepareStatement(connection, SQL_INSERT, true, values);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating comment failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, generatedKeys);
        }
    }


    // Helpers ------------------------------------------------------------------------------------
    static Komentar map(ResultSet resultSet) throws SQLException {
        Komentar komentar = new Komentar();
        komentar.setPID(resultSet.getInt("PID"));
        komentar.setKID(resultSet.getInt("KID"));
        komentar.setNama(resultSet.getString("Nama"));
        komentar.setTanggal(resultSet.getString("Tanggal"));
        komentar.setEmail(resultSet.getString("Email"));
        komentar.setKomentar(resultSet.getString("Komentar"));
        return komentar;
    }
}
