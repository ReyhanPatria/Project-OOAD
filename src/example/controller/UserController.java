package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import example.model.User;
import example.view.UserProfilePanel;

public class UserController {
	private UUID currentUserId;
	
	private static UserController instance;
	
	public static String[] allRoleList = {"admin", "supervisor", "worker"};
	public static String[] selectableRoleList = {"supervisor", "worker"};

	public UserController(UUID currentUserId) {
		this.currentUserId = currentUserId;
	}
	
	/*
	 * Login function
	 */
	public static void login(String username, String password) throws IllegalArgumentException {
		UUID returnedId = User.validateLogin(username, password);
		if(returnedId == null) {
			throw new IllegalArgumentException("Username or Password is wrong!");
		}
		
		instance = new UserController(returnedId);
		
		/*
		 * Test Code
		 */
		MainController.getInstance().changePanel(UserProfilePanel.getInstance());
		/*
		 * 
		 */
	}
	
	/*
	 * Register user function
	 * Returns new user object
	 */
	public static User registerUser(String username, String password, String confirmPassword, String role,
			String address, Date DOB, String telp) throws IllegalArgumentException {
		if(password.equals(confirmPassword) == false) {
			throw new IllegalArgumentException("Password does not match!");
		}
		
		User newUser = new User(username, confirmPassword, role, address, DOB, telp);
		newUser.save();
		
		return newUser;
	}
	
	/*
	 * Get UserController instance
	 */
	public static UserController getInstance() throws NoSuchObjectException {
		if(instance == null) {
			throw new NoSuchObjectException("User is not logged in!");
		}
		
		return instance;
	}
	
	/*
	 * Get a list of all user
	 */
	public List<User> getAllUser() {
		return User.getAll();
	}
	
	/*
	 * Get a list of all user based on a role
	 */
	public List<User> getUserByRole(String role) throws IllegalArgumentException {
		for(String r: allRoleList) {
			if(role.equalsIgnoreCase(r)) {
				return User.getUserByRole(role);
			}
		}
		
		throw new IllegalArgumentException("Role invalid. Valid roles are Admin, Supervisor, Worker");
	}
	
	/*
	 * Get a specific user based on their ID
	 */
	public User getUser(UUID userID) throws IllegalArgumentException {
		User u = User.get(userID);
		
		if(u == null) {
			throw new IllegalArgumentException("userID is not valid");
		}
		
		return u;
	}
	
	/*
	 * Creating a new User object and stores it in the Database.
	 * Returns a newly created User object
	 */
	public User createUser(String username, String password, String role, 
			Date DOB, String address, String telp) throws IllegalArgumentException {
		User newUser = new User(username, password, role, address, DOB, telp);
		newUser.save();
		
		return newUser;
	}
	
	/*
	 * Update the attributes of an existing user
	 * Return newly updated User object
	 */
	public User updateProfile(String username, Date DOB, String address,
			String telp) throws IllegalArgumentException {
		User currentUser = getUser(currentUserId);
		
		currentUser.setUsername(username);
		currentUser.setDOB(DOB);
		currentUser.setAddress(address);
		currentUser.setTelp(telp);
		currentUser.update();
		
		return currentUser;
	}
	
	/*
	 * Delete user based on their ID
	 */
	public void deleteUser(UUID userID) throws IllegalArgumentException {
		User u = getUser(userID);
		u.delete();
	}
	
	/*
	 * Changes currently logged in User password
	 */
	public User changePassword(String oldPassword, String newPassword) throws IllegalArgumentException {
		User currentUser = getUser(currentUserId);
		
		if(oldPassword.equals(currentUser.getPassword()) == false) {
			throw new IllegalArgumentException("Incorrect old password");
		}
		else if(newPassword.length() <= 0) {
			throw new IllegalAccessError("New password cannot be empty");
		}
		
		currentUser.setPassword(newPassword);
		currentUser.update();
		
		return currentUser;
	}
	
	/*
	 * Resets of a user based on their ID
	 */
	public User resetPassword(UUID userID) throws IllegalArgumentException {
		User u = getUser(userID);
		
		u.setPassword(u.getDOB().toString());
		u.update();
		
		return u;
	}
	
	/*
	 * Gets currently logged in userID
	 */
	public UUID getCurrentUserId() {
		return currentUserId;
	}
}
