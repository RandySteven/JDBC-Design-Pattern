package DAO;

import Model.User;

public interface userDAO {

	public String createUser(User user);
	public String deleteUser(int id);
	public String login(String email, String password);
	public String view();
}
