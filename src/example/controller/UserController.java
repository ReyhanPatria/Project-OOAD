package example.controller;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import example.model.User;

public class UserController {
	private User userModel;
	private UUID currentUserId;
	
	private String[] roleList = {"admin", "supervisor", "worker"};

	public UserController() {
		this.userModel = new User();
	}
	
	public void login(String username, String password) {
		UUID returnedID = userModel.validateLogin(username, password);
		if(returnedID == null) {
			throw new IllegalArgumentException("Username or Password is wrong!");
		}
		
		this.currentUserId = returnedID;
	}
	
	public List<User> getAllUser() {
		return userModel.getAll();
	}
	
	public List<User> getUserByRole(String role) throws IllegalArgumentException {
		for(String r: roleList) {
			if(role.equalsIgnoreCase(r)) {
				return userModel.getUserByRole(role);
			}
		}
		
		throw new IllegalArgumentException("Role invalid. Valid roles are Admin, Supervisor, Worker");
	}
	
	public User getUser(UUID userID) throws IllegalArgumentException {
		User u = userModel.get(userID);
		
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
			for(String r: roleList) {
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
}
