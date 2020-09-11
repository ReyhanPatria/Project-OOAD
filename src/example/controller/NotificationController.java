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
		User currentUser = Session.getInstance().getCurrentUser();
		List<Notification> allNotificationList = Notification.getAll(currentUser.getId());
		
		return allNotificationList;
	}
	
	// Creates a new notification for a user based on userID
	public Notification createNotification(UUID userID, String message) throws SQLException {
		Notification newNotification = new Notification(UUID.randomUUID(), userID, message, null);
		newNotification.save();
		
		return newNotification;
	}
	
	// Updates readAt timestamp for all unread notification for a user based in userID 
	public void readAllNotification(UUID userID) throws SQLException {
		List<Notification> allNotificationList = Notification.getAllUnread(userID);
		for(Notification n: allNotificationList) {
			n.setReadAt(Timestamp.from(Instant.now()));
			n.update();
		}
	}
}
