package example.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	private java.sql.Connection con;
	private Statement st;
	
	public Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/taskmanager_db", "root", "");
			st = con.createStatement();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return st.executeQuery(query);
	}
	
	public void executeUpdate(String query) throws SQLException {
		st.executeUpdate(query);
	}
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return (PreparedStatement) con.prepareStatement(sql);
	}
}
