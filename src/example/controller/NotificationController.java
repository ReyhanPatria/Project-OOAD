package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import example.model.Notification;
import example.model.User;
import example.session.Session;

public class NotificationController {
	// STATIC FUNCTIONS -----------------------------------------------------
	// Gets all notification of currently logged in user
	public static List<Notification> getAllNotification() throws NoSuchObjectException {
		User currentUser = Session.getInstance().getCurrentUser();
		List<Notification> allNotificationList = Notification.getAll(currentUser.getId());
		
		return allNotificationList;
	}
	
	// Creates a new notification for a user based on userID
	public static Notification createNotification(UUID userID, String message) {
		Notification newNotification = new Notification(UUID.randomUUID(), userID, message, null);
		newNotification.save();
		
		return newNotification;
	}
	
	// Updates readAt timestamp for all unread notification for a user based in userID 
	public static void realAllNotification(UUID userID) {
		List<Notification> allNotificationList = Notification.getAllUnread(userID);
		for(Notification n: allNotificationList) {
			n.setReadAt(Timestamp.from(Instant.now()));
			n.update();
		}
	}
}
