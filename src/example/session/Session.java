package example.session;

import java.rmi.NoSuchObjectException;

import example.model.User;

public class Session {
	// STATIC ATTRIBUTES
	// Instance of session
	private static Session instance;
	
	
	
	
	
	// NON-STATIC ATTRIBUTES
	// Currently logged in user object
	private User currentUser;
	
	
	
	
	
	// STATIC FUNCTIONS
	// Create a new session
	public static Session createSession(User currentUser) {
		instance = new Session(currentUser);
		return instance;
	}

	// Get session instance
	public static Session getInstance() throws NoSuchObjectException {
		if(instance == null) {
			throw new NoSuchObjectException("No session is in progress");
		}
		
		return instance;
	}
	
	// Ends session
	public static Session endSession() {
		instance = null;
		return instance;
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public Session(User currentUser) {
		this.currentUser = currentUser;
	}
	
	// Get current user
	public User getCurrentUser() {
		return currentUser;
	}
}
