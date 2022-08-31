import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DbConnection {
	Connection con = null;
	public static Connection dbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/storedb", "root", "passwordincorrect");
			return con;
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
			return null;
		}
	}

}
