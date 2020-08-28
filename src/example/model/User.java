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
	// NON-STATIC ATTRIBUTES ----------------------------------------------------
	private UUID 	id;
	private String 	username;
	private String 	password;
	private String	role;
	private String 	address;
	private Date 	DOB;
	private String 	telp;
	
	
	
	
	
	// NON-STATIC FUNCTIONS ----------------------------------------------------
	// Constructor
	public User(UUID id, String username, String password, String role, String address, Date DOB, String telp) {
		super();
		this.id 		= id;
		this.username 	= username;
		this.password 	= password;
		this.role 		= role;
		this.address 	= address;
		this.DOB 		= DOB;
		this.telp 		= telp;
	}
	
	// Updates User object attributes in database
	public User update() throws SQLException {
		PreparedStatement updateStatement = Connection.getConnection().prepareStatement(
				"UPDATE `users` " + 
				"SET `username`=?, `password`=?, `address`=?, `DOB`=?, `telp`=? " + 
				"WHERE `id`=?");
		
		updateStatement.setString	(1, this.username		);
		updateStatement.setString	(2, this.password		);
		updateStatement.setString	(3, this.address		);
		updateStatement.setDate		(4, this.DOB			);
		updateStatement.setString	(5, this.telp			);
		updateStatement.setString	(6, this.id.toString()	);
		
		updateStatement.executeUpdate();
			
		return this;
	}
	
	// Inserts new User object into database
	public User save() throws SQLException {
		PreparedStatement saveStatement = Connection.getConnection().prepareStatement(
				"INSERT INTO `users` " + 
				"(`id`, `username`, `password`, `role`, `address`, `DOB`, `telp`) " + 
				"VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		saveStatement.setString	(1, id.toString()	);
		saveStatement.setString	(2, username)		;
		saveStatement.setString	(3, password		);
		saveStatement.setString	(4, role			);
		saveStatement.setString	(5, address			);
		saveStatement.setDate	(6, DOB				);
		saveStatement.setString	(7, telp			);
		
		saveStatement.executeUpdate();
		
		return this;
	}
	
	// Deletes User object from database
	public UUID delete() throws SQLException {
		PreparedStatement deleteStatement = Connection.getConnection().prepareStatement(
				"DELETE FROM `users` WHERE `id`=?");
		
		deleteStatement.setString(1, id.toString());
		
		deleteStatement.executeUpdate();
		
		return id;
	}
	
	
	
	
	
	// STATIC FUNCTIONS ----------------------------------------------------
	// Validate a login attempt
	// Searches for a matching username and password in database to validate a login
	public static User validateLogin(String username, String password) throws SQLException {
		User user = null;
		ResultSet userIDTable;
		
		PreparedStatement validateLoginStatement = Connection.getConnection().prepareStatement(
				"SELECT id from `users`" + 
				"WHERE `username`=? AND `password`=?");
		
		validateLoginStatement.setString(1, username);
		validateLoginStatement.setString(2, password);
		
		userIDTable = validateLoginStatement.executeQuery();
		
		if(userIDTable.next()) {
			UUID id = UUID.fromString(userIDTable.getString("id"));
			user = get(id);
		}
		
		return user;
	}
	
	// Gets a list of all User from the database
	public static List<User> getAll() throws SQLException {
		ResultSet userTable;
		List<User> userList = new ArrayList<User>();
		
		PreparedStatement getAllStatement = Connection.getConnection().prepareStatement("SELECT * FROM `users`");
		userTable = getAllStatement.executeQuery();
		
		while(userTable.next()) {
			UUID id 			= 	UUID.fromString(userTable.getString("id"));
			String username 	= 	userTable.getString	("username"	);
			String password 	= 	userTable.getString	("password"	);
			String role 		= 	userTable.getString	("role"		);
			String address 		= 	userTable.getString	("address"	);
			Date DOB 			= 	userTable.getDate	("DOB"		);
			String telp 		= 	userTable.getString	("telp"		);
			
			User u = new User(id, username, password, role, address, DOB, telp);
			userList.add(u);
		}
		
		return userList;
	}
	
	// Gets a list of User filtered by role from the database
	public static List<User> getUserByRole(String role) throws SQLException {
		ResultSet userByRoleTable;
		List<User> userByRoleList = new ArrayList<User>();
		
		PreparedStatement getUserByRoleStatement = Connection.getConnection().prepareStatement("SELECT * FROM `users` WHERE `role`=?");
		
		getUserByRoleStatement.setString(1, role);
		userByRoleTable = getUserByRoleStatement.executeQuery();
		
		while(userByRoleTable.next()) {
			UUID id 			= 	UUID.fromString(userByRoleTable.getString("id"));
			String username 	= 	userByRoleTable.getString	("username"	);
			String password 	= 	userByRoleTable.getString	("password"	);
			String address 		= 	userByRoleTable.getString	("address"	);
			Date DOB 			= 	userByRoleTable.getDate	("DOB"		);
			String telp 		= 	userByRoleTable.getString	("telp"		);
			
			User u = new User(id, username, password, role, address, DOB, telp);
			userByRoleList.add(u);
		}
		
		return userByRoleList;
	}
	
	// Gets a User from database
	public static User get(UUID id) throws SQLException {
		ResultSet getTable;
		User u = null;
		
		PreparedStatement getStatement = Connection.getConnection().prepareStatement("SELECT * FROM `users` WHERE `id`=?");
		
		getStatement.setString(1, id.toString());
		getTable = getStatement.executeQuery();
		
		if(getTable.next()) {
			String username 	= 	getTable.getString	("username"	);
			String password 	= 	getTable.getString	("password"	);
			String role			= 	getTable.getString	("role"		);
			String address 		= 	getTable.getString	("address"	);
			Date DOB 			= 	getTable.getDate	("DOB"		);
			String telp 		= 	getTable.getString	("telp"		);
			
			u = new User(id, username, password, role, address, DOB, telp);
		}
		
		return u;
	}

	
	
	
	
	// SETTER | GETTERS ----------------------------------------------------
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
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password) throws IllegalArgumentException {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) throws IllegalArgumentException {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws IllegalArgumentException {
		this.address = address;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date DOB) throws IllegalArgumentException {
		this.DOB = DOB;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) throws IllegalArgumentException {
		this.telp = telp;
	}
}
