package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.userDAO;

public class UserFactory implements userDAO{

	User user;
	public User createPremium(String name, String email, String password) {
		user = new User(name, email, password, "Premium");
		return user;
	}

	public User createGold(String name, String email, String password) {
		user = new User(name, email, password, "Gold");
		return user;
	}

	@Override
	public boolean createUser(User user, Connection conn) throws SQLException{
		String username = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		String position = user.getPosition();
		String query = "INSERT INTO users (name, email, password, position) VALUES ('"+username+"', '"+email+"', '"+password+"', '"+position+"')";
		Statement st = conn.createStatement();
		return st.execute(query);
	}

	@Override
	public boolean deleteUser(int id, Connection conn) throws SQLException{
		String query = "DELETE FROM users WHERE id="+id+"";
		Statement st = conn.createStatement();
		return st.execute(query);
	}

	@Override
	public ResultSet login(String email, String password, Connection conn) throws SQLException{
		String query = "SELECT * FROM users WHERE email='"+email+"' AND password='"+password+"' ";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	@Override
	public void view(Connection conn) {
		String query = "SELECT * FROM users";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("================Orang Orang================");
			while(rs.next()) {
				System.out.println("Id : " + rs.getInt(1));
				System.out.println("Name : " + rs.getString(2));
				System.out.println("Email : " + rs.getString(3));
				System.out.println("Position : " + rs.getString(5));
				System.out.println("==========================================");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
