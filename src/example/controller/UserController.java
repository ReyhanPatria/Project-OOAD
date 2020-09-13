package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import example.encryption.PasswordUtils;
import example.model.User;
import example.session.Session;

public class UserController {
	// STATIC ATTRIBUTES
	// Instance of user controller
	private static UserController instance;
	
	// List of user roles
	public static String[] allRoleList = {"admin", "supervisor", "worker"};
	public static String[] selectableRoleList = {"supervisor", "worker"};
	
	
	
	
	
	// STATIC FUNCTIONS
	// Gets instance of user controller
	public static UserController getInstance() {
		if(instance == null) {
			instance = new UserController();
		}
		return instance;
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public UserController() {}
	
	// Save updates on currently logged in User’s attributes to database
	public User updateProfile(String username, Date DOB, String address, String telp) 
			throws IllegalArgumentException, NoSuchObjectException, SQLException {
		// Validate parameters
		// Validate if username have been taken or not
		if(validateUsername(username) == false) {
			throw new IllegalArgumentException("Username has been taken");
		}
		// Validate username length, 5-15 characters
		else if(validateUsernameLength(username) == false) {
			throw new IllegalArgumentException("Username length has to be 5-15 characters");
		}
		// Validate DOB, must be in the past
		else if(validateDOB(DOB) == false) {
			throw new IllegalArgumentException("Date of Birth must be in the past");
		}
		// Validate address length, 10-100 characters
		else if(validateAddressLength(address) == false) {
			throw new IllegalArgumentException("Address length must be 10-100 characters");
		}
		// Validate telp, must be all digits
		else if(validateTelp(telp) == false) {
			throw new IllegalArgumentException("Telp must be all digits");
		}
		
		// Gets current user
		User currentUser = Session.getInstance().getCurrentUser();
		// Updates current user data
		currentUser.setUsername	(username	);
		currentUser.setDOB		(DOB		);
		currentUser.setAddress	(address	);
		currentUser.setTelp		(telp		);
		// Updates data and saves it into database
		currentUser.update();
		
		return currentUser;
	}
	
	// Changes currently logged in User password to a new password
	public User changePassword(String oldPassword, String newPassword) 
			throws IllegalArgumentException, NoSuchObjectException, SQLException {
		// Check password's length
		if(validatePasswordLength(newPassword) == false) {
			throw new IllegalArgumentException("Password must be at least 5 characters");
		}
		
		// Gets current user
		User currentUser = Session.getInstance().getCurrentUser();
		
		// Encrypt passwords, with username as salt
		String securedOldPassword = PasswordUtils.generateSecurePassword(oldPassword, currentUser.getUsername());
		String securedNewPassword = PasswordUtils.generateSecurePassword(newPassword, currentUser.getUsername());
		
		// Check if old password is correct, if not throw error
		if(securedOldPassword.equals(currentUser.getPassword()) == false) {
			throw new IllegalArgumentException("Incorrect old password");
		}
		// Check if old password is teh same as new password, if yes throw error
		else if(securedOldPassword.equals(securedNewPassword)) {
			throw new IllegalArgumentException("New password cannot be the same as old password");
		}
		// Check new password's length, at least 5 characters
		else if(UserController.validatePasswordLength(newPassword) == false) {
			throw new IllegalArgumentException("New password length must be at least 5 characters");
		}
		
		// Update password attribute
		currentUser.setPassword(securedNewPassword);
		// Update current user and saves it into database
		currentUser.update();
		
		return currentUser;
	}
	
	// Instantiate session instance
	public void login(String username, String password) throws IllegalArgumentException, SQLException {
		// Encrypt password, with username as salt
		String securedPassword = PasswordUtils.generateSecurePassword(password, username);
		
		// Validate login
		User returnedUser = User.validateLogin(username, securedPassword);
		// Check returned user, if null login failed and throws error, else login successful
		if(returnedUser == null) {
			throw new IllegalArgumentException("Username or Password is wrong!");
		}
		
		// Create a new session with returned user
		Session.createSession(returnedUser);
	}
	
	// Registers new User
	public User registerUser(String username, String role, String address, Date DOB, String telp) 
			throws IllegalArgumentException, SQLException, NoSuchObjectException {
		// Validate username's length
		if(validateUsernameLength(username) == false) {
			throw new IllegalArgumentException("Username length has to be 5-15 characters");
		}
		
		// Create default password as DOB
		String password = DOB.toString();
		// Encrypt password, with username as salt
		String securedPassword = PasswordUtils.generateSecurePassword(password, username);
		// Create new user and saves it into database
		User newUser = createUser(username, securedPassword, role, DOB, address, telp); 
		
		return newUser;
	}
	
	// Log out current user (make instance variable null)
	public void logout() {
		// End current session
		Session.endSession();
	}
	
	// Get a list of all user
	public List<User> getAllUser() throws SQLException {
		return User.getAll();
	}
	
	// Get a list of all user based on a role
	public List<User> getUserByRole(String role) throws IllegalArgumentException, SQLException {
		// Validate if role is valid
		if(validateRole(role)) {
			// Return user list based on role
			return User.getUserByRole(role);
		}
		
		// Throws error if role is invalid
		throw new IllegalArgumentException("Role invalid. Valid roles are Admin, Supervisor, Worker");
	}
	
	// Get a specific user based on their ID
	public User getUser(UUID userID) throws IllegalArgumentException, SQLException {
		User u = User.get(userID);
		
		return u;
	}
	
	// Creating a new User object and stores it in the Database.
	public User createUser(String username, String password, String role, 
			Date DOB, String address, String telp) throws IllegalArgumentException, 
			SQLException, NoSuchObjectException {
		// Validate if username has been taken
		if(validateUsername(username) == false) {
			throw new IllegalArgumentException("Username has been taken");
		}
		// Validate username's length
		else if(validateUsernameLength(username) == false) {
			throw new IllegalArgumentException("Username length has to be 5-15 characters");
		}
		// Validate if role is valid
		else if(validateRole(role) == false) {
			throw new IllegalArgumentException("Invalid role");
		}
		// Validate if DOB is in the past
		else if(validateDOB(DOB) == false) {
			throw new IllegalArgumentException("Date of Birth must be in the past");
		}
		// Validates address length
		else if(validateAddressLength(address) == false) {
			throw new IllegalArgumentException("Address length must be 10-100 characters");
		}
		// Validate telp, must be all digits
		else if(validateTelp(telp) == false) {
			throw new IllegalArgumentException("Telp must be all digits");
		}
		
		// Create new user object
		User newUser = new User(UUID.randomUUID(), username, password, role, address, DOB, telp);
		// Saves new user object into database
		newUser.save();
		
		return newUser;
	}
	
	// Delete user based on their ID
	public void deleteUser(UUID userID) throws IllegalArgumentException, SQLException {
		// Gets user object
		User u = getUser(userID);
		// Delete user from database
		u.delete();
	}
	
	// Resets of a user based on their ID
	public User resetPassword(UUID userID) throws IllegalArgumentException, SQLException {
		// Gets user
		User u = getUser(userID);
		
		// Gets user's username
		String username = u.getUsername();
		// Create new password from DOB
		String newPassword = u.getDOB().toString();
		// Encrypt password, with username as salt
		String securedPassword = PasswordUtils.generateSecurePassword(newPassword, username);
		
		// Set new password
		u.setPassword(securedPassword);
		// Update user and saves it into database
		u.update();
		
		return u;
	}
	
	
	
	
	
	// VALIDATORS ----------------------------------------------------
	// Checks if id has been taken. TRUE if not taken, FALSE if taken
	public static Boolean validateID(UUID id) throws SQLException {
		User user = User.get(id);
		
		if(user != null) {
			return false;
		}
		return true;
	}
	
	// Checks if username has been taken. TRUE if not taken, FALSE if taken
	public static Boolean validateUsername(String username) throws SQLException, NoSuchObjectException {
		User currentUser = Session.getInstance().getCurrentUser();
		
		List<User> userList = UserController.getInstance().getAllUser();
		for(User u: userList) {
			if(username.equals(u.getUsername()) && u.getId().equals(currentUser.getId()) == false) {
				return false;
			}
		}
		return true;
	}
	
	// Checks if username's length is correct. 5-15 characters
	public static Boolean validateUsernameLength(String username) {
		username = username.replace(" ", "");
		if(username.length() < 5 || username.length() > 15) {
			return false;
		}
		return true;
	}
	
	// Validate password length
	public static Boolean validatePasswordLength(String password) {
		if(password.length() < 5) {
			return false;
		}
		return true;
	}
	
	// Checks if role is a valid role
	public static Boolean validateRole(String role) {
		for(String r: allRoleList) {
			if(role.equalsIgnoreCase(r)) {
				return true;
			}
		}
		return false;
	}
	
	// Checks if address length is valid. 10-100 characters
	public static Boolean validateAddressLength(String address) {
		address = address.replace(" ", "");
		if(address.length() < 10 || address.length() > 100) {
			return false;
		}
		return true;
	}
	
	// Checks if DOB date is in the past
	public static Boolean validateDOB(Date DOB) {
		java.sql.Date currentDate = java.sql.Date.valueOf(LocalDate.now());
		if(DOB.before(currentDate) == true) {
			return true;
		}
		return false;
	}
	
	// Checks if telp is all numbers
	public static Boolean validateTelp(String telp) {
		if(telp.length() <= 0) {
			return false;
		}
		for(Integer i = 0; i < telp.length(); i++) {
			if(Character.isDigit(telp.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
}
