package example.database;

import java.sql.DriverManager;

public class Connection {
	private static java.sql.Connection con;
	
	public Connection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/taskmanager_db", "root", "");
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
	
//	public ResultSet executeQuery(String query) throws SQLException {
//		return st.executeQuery(query);
//	}
//	
//	public void executeUpdate(String query) throws SQLException {
//		st.executeUpdate(query);
//	}
//	
//	public PreparedStatement prepareStatement(String sql) throws SQLException {
//		return (PreparedStatement) con.prepareStatement(sql);
//	}
}
