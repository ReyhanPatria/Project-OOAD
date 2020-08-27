package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import example.model.User;
import example.session.Session;
import example.view.LoginPanel;
import example.view.UserProfilePanel;

public class UserController {
	// STATIC ATTRIBUTES ----------------------------------------------------
	public static String[] allRoleList = {"admin", "supervisor", "worker"};
	public static String[] selectableRoleList = {"supervisor", "worker"};
	
	
	
	
	
	// STATIC FUNCTIONS ----------------------------------------------------
	// Save updates on currently logged in User’s attributes to database
	public static User updateProfile(String username, Date DOB, String address, String telp) 
			throws IllegalArgumentException, NoSuchObjectException {
		// Validate parameters
		if(validateUsername(username) == false) {
			throw new IllegalArgumentException("Username has been taken");
		}
		else if(validateUsernameLength(username) == false) {
			throw new IllegalArgumentException("Username length has to be 5-15 characters");
		}
		else if(validateDOB(DOB) == false) {
			throw new IllegalArgumentException("Date of Birth must be in the past");
		}
		else if(validateAddressLength(address) == false) {
			throw new IllegalArgumentException("Address length must be 10-100 characters");
		}
		else if(validateTelp(telp) == false) {
			throw new IllegalArgumentException("Telp must be all digits");
		}
		
		User currentUser = Session.getInstance().getCurrentUser();
		
		currentUser.setUsername	(username	);
		currentUser.setDOB		(DOB		);
		currentUser.setAddress	(address	);
		currentUser.setTelp		(telp		);
		currentUser.update();
		
		return currentUser;
	}
	
	// Changes currently logged in User password to a new password
	public static User changePassword(String oldPassword, String newPassword) 
			throws IllegalArgumentException, NoSuchObjectException {
		User currentUser = Session.getInstance().getCurrentUser();
		
		if(oldPassword.equals(currentUser.getPassword()) == false) {
			throw new IllegalArgumentException("Incorrect old password");
		}
		else if(newPassword.length() <= 0) {
			throw new IllegalArgumentException("New password cannot be empty");
		}
		
		currentUser.setPassword(newPassword);
		currentUser.update();
		
		return currentUser;
	}
	
	// Instantiate UserController instance
	public static void login(String username, String password) throws IllegalArgumentException {
		User returnedUser = User.validateLogin(username, password);
		if(returnedUser == null) {
			throw new IllegalArgumentException("Username or Password is wrong!");
		}
		
		Session.createSession(returnedUser);
		
		MainController.changePanel(new UserProfilePanel());
	}
	
	// Registers new User
	public static User registerUser(String username, String password, String confirmPassword, String role,
			String address, Date DOB, String telp) throws IllegalArgumentException {
		if(password.equals(confirmPassword) == false) {
			throw new IllegalArgumentException("Password does not match!");
		}
		
		User newUser = createUser(username, password, role, DOB, address, telp); 
		
		return newUser;
	}
	
	// Log out current user (make instance variable null)
	public static void logout() {
		Session.endSession();
		
		MainController.changePanel(new LoginPanel());
	}
	
	// Get a list of all user
	public static List<User> getAllUser() {
		return User.getAll();
	}
	
	// Get a list of all user based on a role
	public static List<User> getUserByRole(String role) throws IllegalArgumentException {
		if(validateRole(role)) {
			return User.getUserByRole(role);
		}
		
		throw new IllegalArgumentException("Role invalid. Valid roles are Admin, Supervisor, Worker");
	}
	
	// Get a specific user based on their ID
	public static User getUser(UUID userID) throws IllegalArgumentException {
		User u = User.get(userID);
		
		return u;
	}
	
	// Creating a new User object and stores it in the Database.
	public static User createUser(String username, String password, String role, 
			Date DOB, String address, String telp) throws IllegalArgumentException {
		if(validateUsername(username) == false) {
			throw new IllegalArgumentException("Username has been taken");
		}
		else if(validateUsernameLength(username) == false) {
			throw new IllegalArgumentException("Username length has to be 5-15 characters");
		}
		else if(validateRole(role) == false) {
			throw new IllegalArgumentException("Invalid role");
		}
		else if(validateDOB(DOB) == false) {
			throw new IllegalArgumentException("Date of Birth must be in the past");
		}
		else if(validateAddressLength(address) == false) {
			throw new IllegalArgumentException("Address length must be 10-100 characters");
		}
		else if(validateTelp(telp) == false) {
			throw new IllegalArgumentException("Telp must be all digits");
		}
		
		User newUser = new User(UUID.randomUUID(), username, password, role, address, DOB, telp);
		newUser.save();
		
		return newUser;
	}
	
	// Delete user based on their ID
	public static void deleteUser(UUID userID) throws IllegalArgumentException {
		User u = getUser(userID);
		u.delete();
	}
	
	// Resets of a user based on their ID
	public static User resetPassword(UUID userID) {
		User u = getUser(userID);
		
		u.setPassword(u.getDOB().toString());
		u.update();
		
		return u;
	}
	
	
	
	
	
	// VALIDATORS ----------------------------------------------------
	// Checks if id has been taken. TRUE if not taken, FALSE if taken
	public static Boolean validateID(UUID id) {
		User user = User.get(id);
		
		if(user != null) {
			return false;
		}
		return true;
	}
	
	// Checks if username has been taken. TRUE if not taken, FALSE if taken
	public static Boolean validateUsername(String username) {
		List<User> userList = getAllUser();
		for(User u: userList) {
			if(username.equals(u.getUsername())) {
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
		if(DOB.compareTo(new java.util.Date()) < 0) {
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
