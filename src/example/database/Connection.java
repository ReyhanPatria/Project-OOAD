package example.database;

import java.sql.DriverManager;

public class Connection {
	private static java.sql.Connection con;
	
	public Connection() {
		try {
			con = DriverManager.getConnection(String.format("jdbc:%s://localhost/%s", Environment.db_product, Environment.db_name), 
					Environment.auth_username, Environment.auth_password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static java.sql.Connection getConnection() {
		if(con == null) {
			new Connection();
		}
		
		return con;
	}
}
