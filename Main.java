import java.sql.Connection;
import java.sql.ResultSet;
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
	
	void view() {
		query = new UserFactory().view();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				System.out.println("Name : " + rs.getString(2));
				System.out.println("Email : " + rs.getString(3));
				System.out.println("Position : " + rs.getString(5));
				System.out.println("==========================================");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
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
			default:
				mainMenu();
				break;
			}
			
		} while (input!=0);		
	}
	
	void mainMenu() {
		do {
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("0. Exit");
			System.out.print(">>");input=scan.nextInt();scan.nextLine();
			
			switch (input) {
			
			case 1:
				System.out.print("Input email : ");email=scan.nextLine();
				System.out.print("Input password : ");password=scan.nextLine();
				query = new UserFactory().login(email, password);
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);
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
				break;
			
			case 2:
				System.out.print("Input name : ");name=scan.nextLine();
				System.out.print("Input email : ");email=scan.nextLine();
				System.out.print("Input password : ");password=scan.nextLine();
				System.out.print("Choose role [1. Premium | 2. Gold] : ");choose=scan.nextInt();scan.nextLine();
				
				if(choose == 1) {
					user = new UserFactory().createPremium(name, email, password);
				}else {
					user = new UserFactory().createGold(name, email, password);
				}
				
				try {
					query = new UserFactory().createUser(user);
					Statement st = conn.createStatement();
					st.execute(query);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
					// TODO: handle exception
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
