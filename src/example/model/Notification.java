package example.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import example.database.Connection;

public class Notification {
	private UUID id;
	private UUID userID;
	private String message;
	private Timestamp readAt;
	
	/*
	 * Constructor
	 */
	public Notification(UUID id, UUID userID, String message, Timestamp readAt) {
		super();
		this.id = id;
		this.userID = userID;
		this.message = message;
		this.readAt = readAt;
	}
	
	/*
	 * Gets all notifications
	 */
	public static List<Notification> getAll(UUID userID) {
		ArrayList<Notification> notificationList = new ArrayList<Notification>();
		
		try {
			PreparedStatement getAllStatement = Connection.getConnection().prepareStatement(
					"SELECT * FROM `notifications` WHERE `user_id`=?");
			
			getAllStatement.setString(1, userID.toString());
			ResultSet notificationsTable = getAllStatement.executeQuery();
			
			while(notificationsTable.next()) {
				UUID id = UUID.fromString(notificationsTable.getString("id"));
				String message = notificationsTable.getString("message");
				Timestamp readAt = null;
				if(notificationsTable.getDate("read_at") != null) {
					readAt = new Timestamp(notificationsTable.getDate("read_at").getTime());
				}
				
				Notification newNotification = new Notification(id, userID, message, readAt);
				notificationList.add(newNotification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notificationList;
	}
	
	/*
	 * Gets all unread notifications / notifications with null timestamp
	 */
	public static List<Notification> getAllUnread(UUID userID) {
		ArrayList<Notification> unreadNotificationList = new ArrayList<Notification>();
		
		try {
			PreparedStatement getAllUnreadStatement = Connection.getConnection().prepareStatement(
					"SELECT * FROM `notifications` WHERE `user_id`=? AND `read_at` IS NULL");
			
			getAllUnreadStatement.setString(1, userID.toString());
			ResultSet unreadNotificationsTable = getAllUnreadStatement.executeQuery();
			
			while(unreadNotificationsTable.next()) {
				UUID id = UUID.fromString(unreadNotificationsTable.getString("id"));
				String message = unreadNotificationsTable.getString("message");
				Timestamp readAt = null;
				
				Notification newNotification = new Notification(id, userID, message, readAt);
				unreadNotificationList.add(newNotification);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return unreadNotificationList;
	}
	
	/*
	 * Insert new notification into database
	 */
	public Notification save() {
		try {
			PreparedStatement saveStatement = Connection.getConnection().prepareStatement(
					"INSERT INTO `notifications`(`id`, `user_id`, `message`, `read_at`)" + 
					"VALUES(?, ?, ?, ?)");
			
			saveStatement.setString(1, this.id.toString());
			saveStatement.setString(2, this.userID.toString());
			saveStatement.setString(3, this.message);
			if(this.readAt != null) {
				saveStatement.setDate(4, new Date(readAt.getTime()));
			}
			else {
				saveStatement.setDate(4, null);
			}
			
			saveStatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	/*
	 * Update existing notification in database
	 */
	public Notification update() {
		try {
			PreparedStatement updateStatement = Connection.getConnection().prepareStatement(
					"UPDATE `notifications` " + 
					"SET `user_id`=?, `message`=?, `read_at`=? " + 
					"WHERE `id`=?");
			
			updateStatement.setString(1, this.userID.toString());
			updateStatement.setString(2, this.message);
			if(this.readAt != null) {
				updateStatement.setDate(3, new Date(this.readAt.getTime()));
			}
			else {
				updateStatement.setDate(3, null);
			}
			updateStatement.setString(4, this.id.toString());
			
			updateStatement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}

	/*
	 * Setter and Getters
	 */
	public UUID getID() {
		return id;
	}

	public void setID(UUID id) {
		this.id = id;
	}
	
	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getReadAt() {
		return readAt;
	}

	public void setReadAt(Timestamp readAt) {
		this.readAt = readAt;
	}
}
