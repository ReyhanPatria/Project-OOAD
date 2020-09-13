package example.database;

import java.sql.DriverManager;

public class Connection {
	// STATIC ATTRIBUTES
	// Instance of connection
	private static java.sql.Connection con;
	
	
	
	
	
	// STATIC FUNCTIONS
	// Get instance of connection
	public static java.sql.Connection getConnection() {
		if(con == null) {
			new Connection();
		}
		
		return con;
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public Connection() {
		try {
			con = DriverManager.getConnection(String.format("jdbc:%s://localhost/%s", Environment.db_product, Environment.db_name), 
					Environment.auth_username, Environment.auth_password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
