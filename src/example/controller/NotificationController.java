package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import example.model.Notification;
import example.model.User;
import example.session.Session;

public class NotificationController {
	// STATIC ATTRIBUTES
	// Instance of notification controller
	private static NotificationController instance;
	
	
	
	
	
	// STATIC FUNCTIONS
	// Gets instance of notification controller
	public static NotificationController getInstance() {
		if(instance == null) {
			instance = new NotificationController();
		}
		return instance;
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public NotificationController() {}
	
	// Gets all notification of currently logged in user
	public List<Notification> getAllNotification() throws NoSuchObjectException, SQLException {
		// Gets current user
		User currentUser = Session.getInstance().getCurrentUser();
		// Gets list of notification of current user
		List<Notification> allNotificationList = Notification.getAll(currentUser.getId());
		
		return allNotificationList;
	}
	
	// Creates a new notification for a user based on userID and save it into database
	public Notification createNotification(UUID userID, String message) throws SQLException {
		// Create new notification object
		Notification newNotification = new Notification(UUID.randomUUID(), userID, message, null);
		// Save notification object into database
		newNotification.save();
		
		return newNotification;
	}
	
	// Updates readAt timestamp for all unread notification for a user based in userID 
	public void readAllNotification(UUID userID) throws SQLException {
		// Get all unread notification
		List<Notification> allNotificationList = Notification.getAllUnread(userID);
		// Loops throuth all unread notification
		for(Notification n: allNotificationList) {
			// Set read at timestamp to now
			n.setReadAt(Timestamp.from(Instant.now()));
			// Save notification updates to database
			n.update();
		}
	}
}
