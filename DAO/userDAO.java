package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.User;

public interface userDAO {

	public boolean createUser(User user, Connection conn) throws SQLException;
	public boolean deleteUser(int id, Connection conn) throws SQLException;
	public ResultSet login(String email, String password, Connection conn) throws SQLException;
	public void view(Connection conn);
}
