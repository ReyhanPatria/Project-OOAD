package example.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import example.database.Connection;

public class Notification {
	// NON-STATIC ATTRIBUTES -----------------------------------------------------
	private UUID		id;
	private UUID		userID;
	private String		message;
	private Timestamp	readAt;
	
	
	
	
	
	// NON-STATIC FUNCTIONS -----------------------------------------------------
	// Constructor
	public Notification(UUID id, UUID userID, String message, Timestamp readAt) {
		super();
		this.id			=	id;
		this.userID		=	userID;
		this.message	=	message;
		this.readAt		=	readAt;
	}
	
	// Inserts new Notification object into database
	public Notification save() throws SQLException {
		PreparedStatement saveStatement = Connection.getConnection().prepareStatement(
				"INSERT INTO `notifications`(`id`, `user_id`, `message`, `read_at`)" + 
				"VALUES (?, ?, ?, ?)");
		
		saveStatement.setString		(1, this.id.toString()		);
		saveStatement.setString		(2, this.userID.toString()	);
		saveStatement.setString		(3, this.message			);
		saveStatement.setTimestamp	(4, this.readAt				);
		
		saveStatement.executeUpdate();
		
		return this;
	}
	
	// Updates Notification in database based on id
	public Notification update() throws SQLException {
		PreparedStatement updateStatement = Connection.getConnection().prepareStatement(
				"UPDATE `notifications`" + 
				"SET `user_id`=?, `message`=?, `read_at`=?" + 
				"WHERE `id`=?");
		
		updateStatement.setString		(1, this.userID.toString()	);
		updateStatement.setString		(2, this.message			);
		updateStatement.setTimestamp	(3, this.readAt				);
		updateStatement.setString		(4, this.id.toString()		);
	
		updateStatement.executeUpdate();
		
		return this;
	}
	
	
	
	
	
	// STATIC FUNCTIONS -----------------------------------------------------
	// Gets all notification of a user based on userID
	public static List<Notification> getAll(UUID userID) throws SQLException {
		ArrayList<Notification> allNotificationList = new ArrayList<Notification>();
		
		PreparedStatement getAllStatement = Connection.getConnection().prepareStatement(
				"SELECT * FROM `notifications` WHERE `user_id`=? ORDER BY (`read_at` IS NOT NULL), `read_at` DESC");
		
		getAllStatement.setString(1, userID.toString());
		
		ResultSet allNotificationTable = getAllStatement.executeQuery();
		while(allNotificationTable.next()) {
			UUID		id		=	UUID.fromString(allNotificationTable.getString("id"));
			String		message	=	allNotificationTable.getString("message");
			Timestamp	readAt	=	allNotificationTable.getTimestamp("read_at");
			
			Notification n = new Notification(id, userID, message, readAt);
			allNotificationList.add(n);
		}
		
		return allNotificationList;
	}
	
	// Gets all unread (readAt is null) notification of a user based on userID
	public static List<Notification> getAllUnread(UUID userID) throws SQLException {
		ArrayList<Notification> allUnreadNotificationList = new ArrayList<Notification>();
		
		PreparedStatement getAllUnreadStatement = Connection.getConnection().prepareStatement(
				"SELECT * FROM `notifications` WHERE `user_id`=? AND `read_at` IS NULL");
		
		getAllUnreadStatement.setString(1, userID.toString());
		
		ResultSet allUnreadNotificationTable = getAllUnreadStatement.executeQuery();
		while(allUnreadNotificationTable.next()) {
			UUID		id		=	UUID.fromString(allUnreadNotificationTable.getString("id"));
			String		message	=	allUnreadNotificationTable.getString("message");
			Timestamp	readAt	=	allUnreadNotificationTable.getTimestamp("read_at");
			
			Notification n = new Notification(id, userID, message, readAt);
			allUnreadNotificationList.add(n);
		}
		
		return allUnreadNotificationList;
	}
	
	
	
	
	
	// SETTER | GETTER -----------------------------------------------------
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
