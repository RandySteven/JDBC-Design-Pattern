import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Model.User;
import Model.UserFactory;

public class Main {

	int input;
	Scanner scan = new Scanner(System.in);
	String name, email, password;
	int choose;
	User user;
	Connection conn;
	String query;
	String userName, userPosition;
	int id;
	UserFactory uf = new UserFactory();
	
	/**
	 * View procedure in main method
	 * This procedure will call method view() of UserFactory()
	 */
	void view() {
		uf.view(conn);
	}
	
	/**
	 * Delete procedure in main method
	 * This procedure will call delete method of UserFactory()
	 */
	void delete() {
		view();
		System.out.print("Select user that you want to delete by id : ");input=scan.nextInt();scan.nextLine();
		try {
			boolean destroy = uf.deleteUser(input, conn);
			if(destroy) {
				System.out.println("Success to delete ");
			}else {
				System.out.println("Record not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Main menu after user login
	 */
	void menu() {
		do {
			System.out.println("Welcome : " + userName);
			System.out.println("Position : " + userPosition);
			
			System.out.println("1. See other users");
			System.out.println("2. Edit user");
			System.out.println("3. Delete your account");
			System.out.println("0. Logout");
			System.out.print(">>");input=scan.nextInt();scan.nextLine();
			
			switch (input) {
			case 1:
				view();
				break;
			case 2:
		
				break;
			case 3:
				delete();
				break;
			default:
				mainMenu();
				break;
			}
			
		} while (input!=0);		
	}
	
	/*
	 * Login
	 * */
	
	void login() {
		System.out.print("Input email : ");email=scan.nextLine();
		System.out.print("Input password : ");password=scan.nextLine();
		try {
			ResultSet rs = uf.login(email, password, conn);
			if(rs.next()) {
				id = rs.getInt(1);
				userName = rs.getString(2);
				userPosition = rs.getString(5);
				menu();
			}else {
				System.out.println("User not found");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/* Register*/
	void register() {
		System.out.print("Input name : ");name=scan.nextLine();
		System.out.print("Input email : ");email=scan.nextLine();
		System.out.print("Input password : ");password=scan.nextLine();
		System.out.print("Choose role [1. Premium | 2. Gold] : ");choose=scan.nextInt();scan.nextLine();
		
		if(choose == 1) {
			user = uf.createPremium(name, email, password);
		}else {
			user = uf.createGold(name, email, password);
		}
		
		try {
			boolean create = uf.createUser(user, conn);
			if(create == true) {
				System.out.println("Success create user");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Register and Login menu
	 * */
	void mainMenu() {
		do {
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("0. Exit");
			System.out.print(">>");input=scan.nextInt();scan.nextLine();
			
			switch (input) {
			
			case 1:
				login();
				break;
				
			case 2:
				register();
				break;
				
			default:
				break;
			}
			
		} while (input!=0);
	}
	
	public Main() {
	
		conn = sqlConnector.connector();
		
		mainMenu();
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
