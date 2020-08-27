package example.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import example.database.Connection;

public class TaskRequest {
	// STATIC ATTRIBUTES
	private UUID	id;
	private UUID	workerID;
	private UUID	supervisorID;
	private String	title;
	private String	note;
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public TaskRequest(UUID id, UUID workerID, UUID supervisorID, String title, String note) {
		super();
		this.id				=	id;
		this.workerID		=	workerID;
		this.supervisorID	=	supervisorID;
		this.title			=	title;
		this.note			=	note;
	}
	
	// Inserts a new TaskRequest into database
	public TaskRequest save() {
		try {
			PreparedStatement saveStatement = Connection.getConnection().prepareStatement(
					"INSERT INTO `task_requests`(`id`, `supervisor_id`, `worker_id`, `title`, `note`) " + 
					"VALUES (?, ?, ?, ?, ?)");
			
			saveStatement.setString(1, this.id.toString());
			saveStatement.setString(2, this.supervisorID.toString());
			saveStatement.setString(3, this.workerID.toString());
			saveStatement.setString(4, this.title);
			saveStatement.setString(5, this.note);
			
			saveStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public TaskRequest update() {
		try {
			PreparedStatement updateStatement = Connection.getConnection().prepareStatement(
					"UPDATE `task_requests` SET `supervisor_id`=?, `worker_id`=?, `title`=?, `note`=? WHERE `id`=?");
			
			updateStatement.setString(1, this.supervisorID.toString());
			updateStatement.setString(2, this.workerID.toString());
			updateStatement.setString(3, this.title);
			updateStatement.setString(4, this.note);
			updateStatement.setString(5, this.id.toString());
			
			updateStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public UUID delete() {
		PreparedStatement deleteStatement;

		try {
			deleteStatement = Connection.getConnection().prepareStatement(
					"DELETE FROM `task_requests` WHERE `id`=?");
		
			deleteStatement.setString(1, this.id.toString());
			
			deleteStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this.id;
	}

	
	
	
	
	// STATIC FUNCTIONS
	// Gets all task request that a user have based on user's id
	public static List<TaskRequest> getAll(UUID userID) {
		ArrayList<TaskRequest> allTaskRequestList = new ArrayList<TaskRequest>();
		
		try {
			PreparedStatement getAllStatement = Connection.getConnection().prepareStatement(
					"SELECT * FROM `task_requests` WHERE `worker_id`=? OR `supervior_id`=?");
			
			getAllStatement.setString(1, userID.toString());
			getAllStatement.setString(2, userID.toString());
			
			ResultSet allTaskRequestTable = getAllStatement.executeQuery();
			while(allTaskRequestTable.next()) {
				UUID id				=	UUID.fromString(allTaskRequestTable.getString("id"));
				UUID workerID		=	UUID.fromString(allTaskRequestTable.getString("worker_id"));
				UUID supervisorID	=	UUID.fromString(allTaskRequestTable.getString("supervisor_id"));
				String title		=	allTaskRequestTable.getString("title");
				String note			=	allTaskRequestTable.getString("note");
				
				TaskRequest taskRequest = new TaskRequest(id, workerID, supervisorID, title, note);
				allTaskRequestList.add(taskRequest);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allTaskRequestList;
	}
	
	// Gets a specific task request based on it's id
	public static TaskRequest get(UUID id) {
		TaskRequest taskRequest = null;
		
		try {
			PreparedStatement getStatement = Connection.getConnection().prepareStatement(
					"SELECT * FROM `task_requests WHERE `id`=?");
			
			getStatement.setString(1, id.toString());
			
			ResultSet getTable = getStatement.executeQuery();
			
			if(getTable.next()) {
				UUID workerID		=	UUID.fromString(getTable.getString("worker_id"));
				UUID supervisorID	=	UUID.fromString(getTable.getString("supervisor_id"));
				String title		=	getTable.getString("title");
				String note			=	getTable.getString("note");
				
				taskRequest = new TaskRequest(id, workerID, supervisorID, title, note);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return taskRequest;
	}
	
	
	
	
	
	// SETTER | GETTERS
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getWorkerID() {
		return workerID;
	}

	public void setWorkerID(UUID workerID) {
		this.workerID = workerID;
	}

	public UUID getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(UUID supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
