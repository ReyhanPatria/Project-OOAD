package example.session;

import java.rmi.NoSuchObjectException;

import example.model.User;

public class Session {
	private static Session instance;
	
	private User currentUser;
	
	public Session(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public static Session createSession(User currentUser) {
		instance = new Session(currentUser);
		return instance;
	}

	public static Session getInstance() throws NoSuchObjectException {
		if(instance == null) {
			throw new NoSuchObjectException("No session is in progress");
		}
		
		return instance;
	}
	
	public static Session endSession() {
		instance = null;
		return instance;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
}
