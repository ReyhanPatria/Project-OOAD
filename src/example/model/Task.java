package example.model;

import java.rmi.NoSuchObjectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import example.database.Connection;
import example.session.Session;

public class Task {
	// NON-STATIC ATTRIBUTES
	private UUID id;
	private UUID workerID;
	private UUID supervisorID;
	private String title;
	private Integer revisionCount;
	private Integer score;
	private Boolean isSubmitted;
	private Timestamp approvedAt;
	private String note;
	
	
	
	
	
	// STATIC ENUM
	// Enum for sort column
	public static enum SORT_BY {
		TITLE_NAME		("`title`"),
		WORKER_NAME		("`worker_username`"),
		SUPERVISOR_NAME	("`supervisor_username`"),
		APPROVED_DATE	("`approved_at`");
		
		private final String SQLExpression;
		
		private SORT_BY(String SQLExpression) {
			this.SQLExpression = SQLExpression;
		}
		
		public String toSQLExpression() {
			return this.SQLExpression;
		}
	}
	
	// Enum for direction
	public static enum SORT_DIRECTION {
		ASCENDING("ASC"),
		DESCENDING("DESC");
		
		private final String SQLExpression;
		
		private SORT_DIRECTION(String SQLExpression) {
			this.SQLExpression = SQLExpression;
		}
		
		public String toSQLExpression() {
			return this.SQLExpression;
		}
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public Task(UUID id, UUID workerID, UUID supervisorID, String title, Integer revisionCount, Integer score,
			Boolean isSubmitted, Timestamp approvedAt, String note) {
		super();
		this.id = id;
		this.workerID = workerID;
		this.supervisorID = supervisorID;
		this.title = title;
		this.revisionCount = revisionCount;
		this.score = score;
		this.isSubmitted = isSubmitted;
		this.approvedAt = approvedAt;
		this.note = note;
	}
	
	// Inserts new Task to database
	public Task save() {
		try {
			PreparedStatement saveStatement = Connection.getConnection().prepareStatement(
					"INSERT INTO `tasks`(`id`, `supervisor_id`, `worker_id`, `title`, " + 
					"`revision_count`, `score`, `is_submitted`, `approved_at`, `note`) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			saveStatement.setString(1, this.id.toString());
			saveStatement.setString(2, this.supervisorID.toString());
			saveStatement.setString(3, this.workerID.toString());
			saveStatement.setString(4, this.title);
			saveStatement.setInt(5, this.revisionCount);
			saveStatement.setInt(6, this.score);
			saveStatement.setBoolean(7, this.isSubmitted);
			saveStatement.setTimestamp(8, this.approvedAt);
			saveStatement.setString(9, this.note);
			
			saveStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	// Update task's attributes in database
	public Task update() {
		try {
			PreparedStatement updateStatement = Connection.getConnection().prepareStatement(
					"UPDATE `tasks` SET `supervisor_id`=?, `worker_id`=?, `title`=?, " + 
					"`revision_count`=?, `score`=?, `is_submitted`=?, `approved_at`=?, " + 
					"`note`=? WHERE `id`=?");
			
			updateStatement.setString(1, this.supervisorID.toString());
			updateStatement.setString(2, this.workerID.toString());
			updateStatement.setString(3, this.title);
			updateStatement.setInt(4, this.revisionCount);
			updateStatement.setInt(5, this.score);
			updateStatement.setBoolean(6, this.isSubmitted);
			updateStatement.setTimestamp(7, this.approvedAt);
			updateStatement.setString(8, this.note);
			updateStatement.setString(9, this.id.toString());
			
			updateStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	// Delete task from database
	public UUID delete() {
		try {
			PreparedStatement deleteStatement = Connection.getConnection().prepareStatement(
					"DELETE FROM `tasks` WHERE `id`=?");
			
			deleteStatement.setString(1, this.id.toString());
			
			deleteStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this.id;
	}
	
	
	
	
	
	// STATIC FUNCTIONS
	// Get all task that user have
	public static List<Task> getAll(UUID userID) {
		ArrayList<Task> allTaskList = new ArrayList<Task>();
		
		try {
			PreparedStatement getAllStatement = Connection.getConnection().prepareStatement(
					"SELECT * FROM `tasks` WHERE `worker_id`=? OR `supervisor_id`=? " + 
					"ORDER BY `is_submitted` ASC");
			
			getAllStatement.setString(1, userID.toString());
			getAllStatement.setString(2, userID.toString());
			
			ResultSet taskTable = getAllStatement.executeQuery();
			
			while(taskTable.next()) {
				UUID id = UUID.fromString(taskTable.getString("id"));
				UUID workerID = UUID.fromString(taskTable.getString("worker_id"));
				UUID supervisorID = UUID.fromString(taskTable.getString("supervisor_id"));
				String title = taskTable.getString("title");
				Integer revisionCount = taskTable.getInt("revision_count");
				Integer score = taskTable.getInt("score");
				Boolean isSubmitted = taskTable.getBoolean("is_submitted");
				Timestamp approvedAt = taskTable.getTimestamp("approved_at");
				String note = taskTable.getString("note");
				
				Task t = new Task(id, workerID, supervisorID, title, revisionCount, score, isSubmitted, approvedAt, note);
				
				allTaskList.add(t);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allTaskList;
	}
	
	// Gets a specific task based on task's id
	public static Task get(UUID id) {
		Task task = null;
		
		try {
			PreparedStatement getStatement = Connection.getConnection().prepareStatement(
					"SELECT * FROM `tasks` WHERE `id`=? ORDER BY `is_submitted` ASC");
			
			getStatement.setString(1, id.toString());
			
			ResultSet taskTable = getStatement.executeQuery();
			
			if(taskTable.next()) {
				UUID workerID = UUID.fromString(taskTable.getString("worker_id"));
				UUID supervisorID = UUID.fromString(taskTable.getString("supervisor_id"));
				String title = taskTable.getString("title");
				Integer revisionCount = taskTable.getInt("revision_count");
				Integer score = taskTable.getInt("score");
				Boolean isSubmitted = taskTable.getBoolean("is_submitted");
				Timestamp approvedAt = taskTable.getTimestamp("approved_at");
				String note = taskTable.getString("note");
				
				task = new Task(id, workerID, supervisorID, title, revisionCount, score, isSubmitted, approvedAt, note);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return task;
	}
	
	// Search for Tasks based on task's title or worker/supervisor name
	public static List<Task> search(String query) throws NoSuchObjectException {
		ArrayList<Task> searchedTaskList = new ArrayList<Task>();
		query = "%" + query + "%";
		
		try {
			PreparedStatement searchStatement = Connection.getConnection().prepareStatement(
				"SELECT `tasks`.`id`, " +
					"`tasks`.`supervisor_id`, " +
					"( " +
						"SELECT `username` " +
						"FROM `users` " +
						"WHERE `tasks`.`supervisor_id` = `users`.`id` " +
					") AS `supervisor_username`, " +
					"`tasks`.`worker_id`, " +
					"( " +
						"SELECT `username` " +
						"FROM `users` " +
						"WHERE `tasks`.`worker_id` = `users`.`id` " +
					") AS `worker_username`, " +
					"`tasks`.`title`, " +
					"`tasks`.`revision_count`, " +
					"`tasks`.`score`, " +
					"`tasks`.`is_submitted`, " +
					"`tasks`.`approved_at`, " +
					"`tasks`.`note` " +
				"FROM `tasks` " +
				"WHERE 	( " +
						"`title` = ? OR " +
						"( " +
							"SELECT `username` " +
							"FROM `users` " +
							"WHERE `tasks`.`supervisor_id` = `users`.`id` " +
						") LIKE ? OR " +
						"( " +
							"SELECT `username` " +
							"FROM `users` " +
							"WHERE `tasks`.`worker_id` = `users`.`id` " +
						") LIKE ? " +
					") " +
					"AND " + 
					"( " +
						"`supervisor_id` = ? OR " +
						"`worker_id` = ? " +
					") " +
				"ORDER BY `is_submitted` ASC"
			);
			
			UUID currentUserID = Session.getInstance().getCurrentUser().getId();
			
			searchStatement.setString(1, query);
			searchStatement.setString(2, query);
			searchStatement.setString(3, query);
			searchStatement.setString(4, currentUserID.toString());
			searchStatement.setString(5, currentUserID.toString());
			
			System.out.println(searchStatement.toString());
			
			ResultSet searchTable = searchStatement.executeQuery();
			
			while(searchTable.next()) {
				UUID id = UUID.fromString(searchTable.getString("id"));
				UUID workerID = UUID.fromString(searchTable.getString("worker_id"));
				UUID supervisorID = UUID.fromString(searchTable.getString("supervisor_id"));
				String title = searchTable.getString("title");
				Integer revisionCount = searchTable.getInt("revision_count");
				Integer score = searchTable.getInt("score");
				Boolean isSubmitted = searchTable.getBoolean("is_submitted");
				Timestamp approvedAt = searchTable.getTimestamp("approved_at");
				String note = searchTable.getString("note");
				
				Task t = new Task(id, workerID, supervisorID, title, revisionCount, score, isSubmitted, approvedAt, note);
				
				searchedTaskList.add(t);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return searchedTaskList;
	}
	
	public static List<Task> sort(SORT_BY sortBy, SORT_DIRECTION direction) throws NoSuchObjectException {
		ArrayList<Task> sortList = new ArrayList<Task>();
		
		try {
			PreparedStatement sortStatement = Connection.getConnection().prepareStatement(
				"( " +
					"SELECT `tasks`.`id`, " +
						"`tasks`.`supervisor_id`, " +
						"( " +
							"SELECT `username` " + 
							"FROM `users` " +
							"WHERE `tasks`.`supervisor_id` = `users`.`id` " +
						") AS `supervisor_username`, " +
						"`tasks`.`worker_id`, " +
						"( " +
							"SELECT `username` " + 
							"FROM `users` " + 
							"WHERE `tasks`.`worker_id` = `users`.`id` " +
						") AS `worker_username`, " +
						"`tasks`.`title`, " +
						"`tasks`.`revision_count`, " +
						"`tasks`.`score`, " +
						"`tasks`.`is_submitted`, " +
						"`tasks`.`approved_at`, " +
						"`tasks`.`note` " +
					"FROM `tasks` " +
					"WHERE `supervisor_id` = ? " +
						"OR `worker_id` = ? " +
				") " +
				"ORDER BY " + sortBy.toSQLExpression() + " " + direction.toSQLExpression()
			);
			
			sortStatement.setString(1, Session.getInstance().getCurrentUser().getId().toString());
			sortStatement.setString(2, Session.getInstance().getCurrentUser().getId().toString());
			
			System.out.println(sortStatement.toString());
			
			ResultSet sortTable = sortStatement.executeQuery();
			
			while(sortTable.next()) {
				UUID id = UUID.fromString(sortTable.getString("id"));
				UUID workerID = UUID.fromString(sortTable.getString("worker_id"));
				UUID supervisorID = UUID.fromString(sortTable.getString("supervisor_id"));
				String title = sortTable.getString("title");
				Integer revisionCount = sortTable.getInt("revision_count");
				Integer score = sortTable.getInt("score");
				Boolean isSubmitted = sortTable.getBoolean("is_submitted");
				Timestamp approvedAt = sortTable.getTimestamp("approved_at");
				String note = sortTable.getString("note");
				
				Task t = new Task(id, workerID, supervisorID, title, revisionCount, score, isSubmitted, approvedAt, note);
				
				sortList.add(t);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return sortList;
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

	public Integer getRevisionCount() {
		return revisionCount;
	}

	public void setRevisionCount(Integer revisionCount) {
		this.revisionCount = revisionCount;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getIsSubmitted() {
		return isSubmitted;
	}

	public void setIsSubmitted(Boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public Timestamp getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(Timestamp approvedAt) {
		this.approvedAt = approvedAt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
