package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import example.model.Notification;

public class NotificationController {
	public static List<Notification> getAllNotification() throws NoSuchObjectException {
		UUID currentUserID = UserController.getInstance().getCurrentUserId();
		List<Notification> allNotificationList = Notification.getAll(currentUserID);
		
		return allNotificationList;
	}
	
	public static Notification createNotification(UUID userID, String message) {
		Notification newNotification = new Notification(UUID.randomUUID(), userID, message, null);
		newNotification.save();
		
		return newNotification;
	}
	
	public static void realAllNotification(UUID userID) {
		List<Notification> allNotificationList = Notification.getAllUnread(userID);
		for(Notification n: allNotificationList) {
			n.setReadAt(Timestamp.from(Instant.now()));
			n.update();
		}
	}
}
