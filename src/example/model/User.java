package example.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import example.controller.UserController;
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
		
		this.setId(UUID.randomUUID());
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
		this.setAddress(address);
		this.setDOB(DOB);
		this.setTelp(telp);
		
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
	 * Validates username's length and availability
	 */
	public static boolean validateUsername(String username) throws IllegalArgumentException {
		if(username.length() < 5 || username.length() > 15) {
			throw new IllegalArgumentException("Username length must be 5-15 characters");
		}
		
		try {
			PreparedStatement getUsernameStatement = Connection.getConnection().prepareStatement(
					"SELECT `username` FROM `users` WHERE username=?");
			getUsernameStatement.setString(1, username);
			
			ResultSet result = getUsernameStatement.executeQuery();
			if(result.next()) {
				if(result.getString("username").length() > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * Validates password's length
	 */
	public static boolean validatePassword(String password) {
		if(password.length() < 8 || password.length() > 15) {
			return false;
		}
		return true;
	}
	
	/*
	 * Validate that Date of Birth is less than current date
	 */
	public static boolean validateDOB(Date DOB) {
		if(new java.util.Date().compareTo(DOB) <= 0) {
			return false;
		}
		return true;
	}
	
	/*
	 * Validates address length
	 */
	public static boolean validateAddress(String address) {
		if(address.length() < 10 || address.length() > 100) {
			return false;
		}
		return true;
	}
	
	/*
	 * Validates role to all available role
	 */
	public static boolean validateRole(String role) {
		for(String r: UserController.allRoleList) {
			if(role.equalsIgnoreCase(r)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Validate that telp is all digits
	 */
	public static boolean validateTelp(String telp) {
		for(int i = 0; i < telp.length(); i++) {
			if(Character.isDigit(telp.charAt(i)) == false) {
				return false;
			}
		}
		return true;
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

	public void setUsername(String username) throws IllegalArgumentException {
		if(validateUsername(username) == false) {
			throw new IllegalArgumentException("Username has been taken");
		}
		
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password) throws IllegalArgumentException {
		if(validatePassword(password) == false) {
			throw new IllegalArgumentException("Password length must be 8-15 characters");
		}
		
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) throws IllegalArgumentException {
		if(validateRole(role) == false) {
			throw new IllegalArgumentException("Invalid role");
		}
		
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws IllegalArgumentException {
		if(validateAddress(address) == false) {
			throw new IllegalArgumentException("Address length must be between 10-100 characters");
		}
		
		this.address = address;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date DOB) throws IllegalArgumentException {
		if(validateDOB(DOB) == false) {
			throw new IllegalArgumentException("Invalid Date of Birth");
		}
		
		this.DOB = DOB;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) throws IllegalArgumentException {
		if(validateTelp(telp) == false) {
			throw new IllegalArgumentException("Invalid telp");
		}
		
		this.telp = telp;
	}
}
