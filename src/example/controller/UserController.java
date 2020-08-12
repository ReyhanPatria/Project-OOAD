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
	
	private static String[] allRoleList = {"admin", "supervisor", "worker"};
	public static String[] selectableRoleList = {"supervisor", "worker"};

	public UserController(UUID currentUserId) {
		this.currentUserId = currentUserId;
	}
	
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
	
	public static UserController getInstance() throws NoSuchObjectException {
		if(instance == null) {
			throw new NoSuchObjectException("User is not logged in!");
		}
		
		return instance;
	}
	
	public List<User> getAllUser() {
		return User.getAll();
	}
	
	public List<User> getUserByRole(String role) throws IllegalArgumentException {
		for(String r: allRoleList) {
			if(role.equalsIgnoreCase(r)) {
				return User.getUserByRole(role);
			}
		}
		
		throw new IllegalArgumentException("Role invalid. Valid roles are Admin, Supervisor, Worker");
	}
	
	public User getUser(UUID userID) throws IllegalArgumentException {
		User u = User.get(userID);
		
		if(u == null) {
			throw new IllegalArgumentException("userID is not valid");
		}
		
		return u;
	}
	
	public User createUser(String username, String password, String role, 
			Date DOB, String address, String telp) throws IllegalArgumentException {
		if(username.length() < 5 || username.length() > 15) {
			throw new IllegalArgumentException("Invalid username length. Must be 5-15 characters");
		}
		else if(password.length() <= 0) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		else if(new java.util.Date().compareTo(DOB) >= 0) {
			throw new IllegalArgumentException("Invalid Date of Birth");
		}
		else if(address.length() < 10 || address.length() > 100) {
			throw new IllegalArgumentException("Invalid address length. Must be 10-100 characters");
		}
		else {
			Boolean validRole = false;
			for(String r: allRoleList) {
				if(role.equalsIgnoreCase(r)) {
					validRole = true;
					break;
				}
			}
			if(validRole == false) {
				throw new IllegalArgumentException("Role invalid. Valid roles are Admin, Supervisor, Worker");
			}
			
			for(int i = 0; i < telp.length(); i++) {
				if(Character.isDigit(telp.charAt(i)) == false) {
					throw new IllegalArgumentException("Invalid telp. Must be numeric");
				}
			}
		}
		
		User newUser = new User(username, password, role, address, DOB, telp);
		newUser.save();
		
		return newUser;
	}
	
	public User updateProfile(String username, Date DOB, String address,
			String telp) throws IllegalArgumentException {
		User currentUser = getUser(currentUserId);
		
		if(username.length() < 5 || username.length() > 15) {
			throw new IllegalArgumentException("Invalid username length. Must be 5-15 characters");
		}
		else if(new java.util.Date().compareTo(DOB) >= 0) {
			throw new IllegalArgumentException("Invalid Date of Birth");
		}
		else if(address.length() < 10 || address.length() > 100) {
			throw new IllegalArgumentException("Invalid address length. Must be 10-100 characters");
		}
		else {
			for(int i = 0; i < telp.length(); i++) {
				if(Character.isDigit(telp.charAt(i)) == false) {
					throw new IllegalArgumentException("Invalid telp. Must be numeric");
				}
			}
		}
		
		currentUser.setUsername(username);
		currentUser.setDOB(DOB);
		currentUser.setAddress(address);
		currentUser.setTelp(telp);
		currentUser.update();
		
		return currentUser;
	}
	
	public void deleteUser(UUID userID) throws IllegalArgumentException {
		User u = getUser(userID);
		u.delete();
	}
	
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
	
	public User resetPassword(UUID userID) throws IllegalArgumentException {
		User u = getUser(userID);
		
		u.setPassword(u.getDOB().toString());
		u.update();
		
		return u;
	}
	
	public UUID getCurrentUserId() {
		return currentUserId;
	}
}
