package source;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDatabase {
    private static Connection koneksi;
    private static String user;
    private static String password;
    
    public static void setUser(String _user)
    {
        user = _user;
    }
    
    public static void setPassword(String _password)
    {
        password = _password;
    }
    
    /**
     * Fungsi untuk mendapatkan koneksi ke database. User dan password dari database harus diset terlebih dahulu.
     * @return Mengembalikan koneksi ke database.
     */
    public static Connection getKoneksi(){
        if(koneksi == null && user != null && password != null) {
            try {
                String url = "jdbc:mysql://localhost:3306/blog";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Berhasil terkoneksi");
            }
            catch(SQLException t){
                System.out.println("eror database");
            }
        }
        return koneksi;
    }
}
