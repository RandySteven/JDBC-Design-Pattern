package Model;

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
	public String createUser(User user) {
		String username = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		String position = user.getPosition();
		String query = "INSERT INTO users (name, email, password, position) VALUES ('"+username+"', '"+email+"', '"+password+"', '"+position+"')";
		return query;
	}

	@Override
	public String deleteUser(int id) {
		String query = "DELETE FROM users WHERE id="+id+"";
		return query;
	}

	@Override
	public String login(String email, String password) {
		String query = "SELECT * FROM users WHERE email='"+email+"' AND password='"+password+"' ";
		return query;
	}

	@Override
	public String view() {
		String query = "SELECT * FROM users";
		return query;
	}
	
	

}
