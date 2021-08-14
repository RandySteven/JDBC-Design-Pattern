import java.sql.Connection;
import java.sql.DriverManager;

public class sqlConnector {

	private static final String url = "jdbc:mysql://localhost:3306/review_jdbc?useTimezone=true&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "";
	
	public static Connection connector() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
}
