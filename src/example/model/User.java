package example.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import example.database.Connection;

public class User {
	private UUID id;
	private String username;
	private String password;
	private String role;
	private String address;
	private Date DOB;
	private String telp;
	
	private PreparedStatement updateStatement;
	private PreparedStatement saveStatement;
	private PreparedStatement deleteStatement;

	public User(String username, String password, String role, String address, Date DOB, String telp) {
		super();
		this.id = UUID.randomUUID();
		this.username = username;
		this.password = password;
		this.role = role;
		this.address = address;
		this.DOB = DOB;
		this.telp = telp;
		
		try {
			this.updateStatement = Connection.getConnection().prepareStatement("UPDATE `users` SET " + 
					"`username`=?, `password`=?, `address`=?, `DOB`=?, `telp`=?" + 
					"WHERE `id`=?");
			this.saveStatement = Connection.getConnection().prepareStatement("INSERT INTO `users`" + 
					"(`id`, `username`, `password`, `role`, `address`, `DOB`, `telp`)" + 
					"VALUES (?, ?, ?, ?, ?, ?, ?)");
			this.deleteStatement = Connection.getConnection().prepareStatement("DELETE FROM `users` WHERE `id`=?");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static UUID validateLogin(String username, String password) {
		ResultSet userIDTable;
		
		try {
			PreparedStatement validateLoginStatement = Connection.getConnection().prepareStatement("SELECT id from `users`" + 
					"WHERE `username`=? AND `password`=?");
			
			validateLoginStatement.setString(1, username);
			validateLoginStatement.setString(2, password);
			
			userIDTable = validateLoginStatement.executeQuery();
			
			if(userIDTable.next()) {
				return UUID.fromString(userIDTable.getString("id"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<User> getAll() {
		ResultSet userTable;
		List<User> userList = new ArrayList<User>();
		
		try {
			PreparedStatement getAllStatement = Connection.getConnection().prepareStatement("SELECT * FROM `users`");
			
			userTable = getAllStatement.executeQuery();
			
			while(userTable.next()) {
				String username = userTable.getString("username");
				String password = userTable.getString("password");
				String role = userTable.getString("role");
				String address = userTable.getString("address");
				Date DOB = userTable.getDate("DOB");
				String telp = userTable.getString("telp");
				
				User u = new User(username, password, role, address, DOB, telp);
				userList.add(u);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public static List<User> getUserByRole(String role) {
		ResultSet userByRoleTable;
		List<User> userByRoleList = new ArrayList<User>();
		
		try {
			PreparedStatement getUserByRoleStatement = Connection.getConnection().prepareStatement("SELECT * FROM `users` WHERE `role`=?");
			
			getUserByRoleStatement.setString(1, role);
			userByRoleTable = getUserByRoleStatement.executeQuery();
			
			while(userByRoleTable.next()) {
				String username = userByRoleTable.getString("username");
				String password = userByRoleTable.getString("password");
				String address = userByRoleTable.getString("address");
				Date DOB = userByRoleTable.getDate("DOB");
				String telp = userByRoleTable.getString("telp");
				
				User u = new User(username, password, role, address, DOB, telp);
				userByRoleList.add(u);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userByRoleList;
	}
	
	public static User get(UUID id) {
		ResultSet getTable;
		User u = null;
		
		try {
			PreparedStatement getStatement = Connection.getConnection().prepareStatement("SELECT * FROM `users` WHERE `id`=?");
			
			getStatement.setString(1, id.toString());
			getTable = getStatement.executeQuery();
			
			while(getTable.next()) {
				String username = getTable.getString("username");
				String password = getTable.getString("password");
				String role = getTable.getString("role");
				String address = getTable.getString("address");
				Date DOB = getTable.getDate("DOB");
				String telp = getTable.getString("telp");
				
				u = new User(username, password, role, address, DOB, telp);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	public User update() {
		try {
			updateStatement.setString(1, username);
			updateStatement.setString(2, password);
			updateStatement.setString(3, address);
			updateStatement.setDate(4, DOB);
			updateStatement.setString(5, telp);
			updateStatement.setString(6, id.toString());
			
			if(updateStatement.executeUpdate() == 0) {
				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public User save() {
		try {
			saveStatement.setString(1, id.toString());
			saveStatement.setString(2, username);
			saveStatement.setString(3, password);
			saveStatement.setString(4, role);
			saveStatement.setString(5, address);
			saveStatement.setDate(6, DOB);
			saveStatement.setString(7, telp);
			
			if(saveStatement.executeUpdate() == 0) {
				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public UUID delete() {
		try {
			deleteStatement.setString(1, id.toString());
			
			if(deleteStatement.executeUpdate() == 0) {
				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	/*
	 * Setter and Getters for attributes
	 */
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date DOB) {
		this.DOB = DOB;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}
}
