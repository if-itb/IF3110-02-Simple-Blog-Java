package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DBConnectionDemo {

	public static void main(String args[]) {
		// Mengakses instans
		DatabaseAccess databaseAccess = DatabaseAccess.getInstance();

		// Set konfigurasi
		databaseAccess.setDatabase("EMP");
		databaseAccess.setUsername("root"); // secara default root
		databaseAccess.setPassword(""); // secara default string kosong

		// Membuka koneksi
		try {
			databaseAccess.openConnection();
			System.out.println("Berhasil membuka koneksi");
		} catch (SQLException e) {
			System.out.println("Gagal membuka koneksi");
			System.out.println(e);
		}

		// -------------------INSERT----------------------
		// Melakukan INSERT INTO Employee (id,age,first,last) VALUES
		// (200,21,'Kevin','Yudi
		// Utama');
		ArrayList<String> columns = new ArrayList(Arrays.asList("id", "age",
				"first", "last"));
		ArrayList<String> values = new ArrayList(Arrays.asList("200", "21",
				"'Kevin'", "'Yudi'"));
		try {
			databaseAccess.insertRecords("Employee", columns, values);
			System.out.println("Berhasil memasukkan records");
		} catch (SQLException e1) {
			System.out.println("Gagal memasukkan records");
			System.out.println("Error : " + e1);
		}
		// Alternatif databaseAccess.executeUpdateQuery("INSERT INTO Employee
		// (age,first,last) VALUES (21,'Kevin','Yudi Utama');");"

		// -----------------DELETE------------------------
		// Melakukan DELETE FROM Employee WHERE first='Kevin' AND last='Yudi';

		// null jika tidak ada kondisi (menghapus seluruh records pada tabel);
		String condition = "first='Kevin' AND last='Yudi'";

		try {
			databaseAccess.deleteRecords("Employee", condition);
			System.out.println("Berhasil menghapus records");
		} catch (SQLException e1) {
			System.out.println("Gagal menghapus records");
			e1.printStackTrace();
		}
		// Alternatif databaseAccess.executeUpdateQuery("DELETE FROM Employee
		// WHERE
		// first='Kevin' AND last='Yudi';");

		// -------------------UPDATE----------------------------
		// melakukan UPDATE Employee SET first='Wira',last='Gotama' WHERE Id =
		// 103;
		columns = new ArrayList(Arrays.asList("first", "last"));
		values = new ArrayList(Arrays.asList("'Wira'", "'Gotama'"));
		condition = "Id = 103";
		try {
			databaseAccess
					.updateRecords("Employee", columns, values, condition);
			System.out.println("Berhasil melakukan update terhadap database");
		} catch (SQLException e2) {
			System.out.println("Gagal melakukan update terhadap database");
			System.out.println(e2);
		}

		// -----------------SELECT--------------------------
		// melakukan SELECT id,first,last FROM Employee;
		columns = new ArrayList(Arrays.asList("id", "first", "last"));
		condition = null; // jika ada kondisi dapat dimasukkan seperti pada
		// INSERT dan DELETE;
		ResultSet result = null;
		try {
			result = databaseAccess.selectRecords("Employee", columns,
					condition);
			System.out.println("Berhasil melakukan seleksi pada database");
		} catch (SQLException e1) {
			System.out.println("Gagal melkaukan seleksi pada database");
			System.out.println(e1);
		}

		// Mengakses hasil seleksi
		try {
			while (result.next()) {
				System.out.println("Id = " + result.getInt("id"));
				System.out.println("First Name = " + result.getString("first"));
				System.out.println("Last Name = " + result.getString("last"));
				System.out.println("---------------------------------------");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// Menutup koneksi
		try {
			databaseAccess.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
