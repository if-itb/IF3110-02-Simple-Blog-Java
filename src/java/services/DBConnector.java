/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yafithekid
 */
public class DBConnector {

    /**
     * Variabel untuk koneksi ke database
     */
    public static java.sql.Connection con;

    private static String URL = "jdbc:mysql://localhost:3306/tubes_2_wbd";
    private static String user = "root";
    private static String password = "akuganteng";

    public DBConnector() {
        try {
            initConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(con == null);

    }

    /**
     * memulai koneksi ke MySQL. MySQL sudah harus aktif pada port 3306
     *
     * @throws SQLException
     */
    public void initConnection() throws SQLException {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(DBConnector.URL, DBConnector.user, DBConnector.password);
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
