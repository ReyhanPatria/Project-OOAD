package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import example.model.Task;
import example.model.User;
import example.session.Session;

public class TaskHandler {
	// STATIC ATTRIBUTES
	// Instance of task handler
	private static TaskHandler instance;
	
	
	
	
	
	// STATIC FUNCTION
	// Gets instance of task handler
	public static TaskHandler getInstance() {
		if(instance == null) {
			instance = new TaskHandler();
		}
		return instance;
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public TaskHandler() {}
	
	// Get all task that current user have
	public List<Task> getAllTask() throws NoSuchObjectException, SQLException {
		User currentUser = Session.getInstance().getCurrentUser();
		List<Task> taskList = Task.getAll(currentUser.getId());
		return taskList;
	}
	
	// Get a specific Task from database based on its id
	public Task getTask(UUID taskID) throws SQLException {
		Task task = Task.get(taskID);
		
		return task;
	}
	
	// Creates a new Task object then inserts it into database
	public Task createTask(String title, UUID workerID, UUID supervisorID, String note) 
			throws NoSuchObjectException, IllegalArgumentException, SQLException {
		// Validate parameters
		if(validateTitleLength(title) == false) {
			throw new IllegalArgumentException("Title's length must be 5-20 characters");
		}
		else if(validateWorkerID(workerID) == false) {
			throw new IllegalArgumentException("Worker does not exist in database");
		}
		else if(validateSupervisorID(supervisorID) == false) {
			throw new IllegalArgumentException("Supervisor does not exist in database");
		}
		else if(validateNoteLength(note) == false) {
			throw new IllegalArgumentException("Note's length must be 0-50 characters");
		}
		
		User currentUser = Session.getInstance().getCurrentUser();
		Task newTask = null;
		
		if(currentUser.getRole().equalsIgnoreCase("SUPERVISOR")) {
			UUID id = UUID.randomUUID();
			Integer revisionCount = 0;
			Integer score = 0;
			Boolean isSubmitted = false;
			Timestamp approvedAt = null;
			
			newTask = new Task(id, workerID, supervisorID, title, revisionCount, score, isSubmitted, approvedAt, note);
			newTask.save();
			
			String supervisorName = UserController.getUser(supervisorID).getUsername();
			String notificationMessage = String.format("%s has assigned you a new task \"%s\"", supervisorName, title);
			NotificationController.getInstance().createNotification(workerID, notificationMessage);
		}
		else if(currentUser.getRole().equalsIgnoreCase("WORKER")) {
			TaskRequestHandler.getInstance().createTaskRequest(title, supervisorID, workerID, note);
			
			String workerName = UserController.getUser(workerID).getUsername();
			String notificationMessage = String.format("%s has requested you to supervise a new task \"%s\"", workerName, title);
			NotificationController.getInstance().createNotification(supervisorID, notificationMessage);
		}
		
		return newTask;
	}
	
	// Update an existing Task object in database
	public Task updateTask(UUID taskID, String title, UUID workerID, UUID supervisorID, Integer score, String note) 
			throws IllegalArgumentException, SQLException {
		// Validate parameters
		if(validateTitleLength(title) == false) {
			throw new IllegalArgumentException("Title's length must be 5-20 characters");
		}
		else if(validateWorkerID(workerID) == false) {
			throw new IllegalArgumentException("Worker does not exist in database");
		}
		else if(validateSupervisorID(supervisorID) == false) {
			throw new IllegalArgumentException("Supervisor does not exist in database");
		}
		else if(validateScore(score) == false) {
			throw new IllegalArgumentException("Score must be between 0-100");
		}
		else if(validateNoteLength(note) == false) {
			throw new IllegalArgumentException("Note's length must be 0-50 characters");
		}
		
		// Get task
		Task task = Task.get(taskID);
		
		// Update task data
		task.setTitle(title);
		task.setWorkerID(workerID);
		task.setSupervisorID(supervisorID);
		task.setScore(score);
		task.setNote(note);
		
		// Update task in database
		task.update();
		
		// Sends notification for supervisor and worker
		String supervisorName = UserController.getUser(supervisorID).getUsername();
		NotificationController.getInstance().createNotification(
				supervisorID, String.format("%s has updated information on task \"%s\"", supervisorName, title));
		NotificationController.getInstance().createNotification(
				workerID, String.format("%s has updated information on task \"%s\"", supervisorName, title));
		
		return task;
	}
	
	// Delete a specific Task from database
	public void deleteTask(UUID taskID) throws SQLException {
		// Gets task
		Task task = Task.get(taskID);
		// Delete task from database
		task.delete();
		
		// Sends notification for supervisor and worker
		// Gets task title
		String title = task.getTitle();
		// Gets worker and supervisor id
		UUID supervisorID = task.getSupervisorID();
		UUID workerID = task.getWorkerID();
		// Gets supervisor name
		String supervisorName = UserController.getUser(supervisorID).getUsername();
		// Sends notification for supervisor and worker
		NotificationController.getInstance().createNotification(
				supervisorID, String.format("%s has deleted on task \"%s\"", supervisorName, title));
		NotificationController.getInstance().createNotification(
				workerID, String.format("%s has deleted task \"%s\"", supervisorName, title));
	}
	
	// Approve of a Task
	// Sets Task's score and approved timestamp then updates the database, gives a notification
	public Task approveTask(UUID taskID, Integer score) throws IllegalArgumentException, SQLException {
		// Validate parameters
		if(validateScore(score) == false) {
			throw new IllegalArgumentException("Score must be between 0-100");
		}
		
		Task task = getTask(taskID);
		String title = task.getTitle();
		UUID supervisorID = task.getSupervisorID();
		UUID workerID = task.getSupervisorID();
		Boolean isSubmitted = task.getIsSubmitted();
		
		// Check if task have been submitted
		if(isSubmitted == false) {
			throw new IllegalArgumentException("Task hasn't been submitted");
		}
		
		task.setScore(score);
		task.setApprovedAt(Timestamp.from(Instant.now()));
		
		task.update();
		
		String supervisorName = UserController.getUser(supervisorID).getUsername();
		String notificationMessage = String.format("%s has approved your task \"%s\"", supervisorName, title);
		NotificationController.getInstance().createNotification(workerID, notificationMessage);
		
		return task;
	}
	
	// Request a Task revision
	// Adds 1 to Task's revision count and set submitted status to false, gives a notification
	public Task requestTaskRevision(UUID taskID) throws SQLException {
		Task task = getTask(taskID);
		String title = task.getTitle();
		UUID supervisorID = task.getSupervisorID();
		UUID workerID = task.getSupervisorID();
		Integer revisionCount = task.getRevisionCount();
		Boolean isSubmitted = task.getIsSubmitted();
		
		// Check if task have been submitted
		if(isSubmitted == false) {
			throw new IllegalArgumentException("Task hasn't been submitted");
		}
		
		task.setRevisionCount(revisionCount + 1);
		task.setIsSubmitted(false);
		
		task.update();
		
		String supervisorName = UserController.getUser(supervisorID).getUsername();
		String notificationMessage = String.format("%s has requested you a revision on task \"%s\"", supervisorName, title);
		NotificationController.getInstance().createNotification(workerID, notificationMessage);
		
		return task;
	}
	
	// Submit a Task
	// Sets Task's submitted status to true then updates the database, and gives a notification
	public Task submitTask(UUID taskID) throws SQLException {
		Task task = getTask(taskID);
		String title = task.getTitle();
		UUID supervisorID = task.getSupervisorID();
		UUID workerID = task.getSupervisorID();
		
		task.setIsSubmitted(true);
		
		task.update();
		
		String workerName = UserController.getUser(workerID).getUsername();
		String notificationMessage = String.format("%s has submitted \"%s\"", workerName, title);
		NotificationController.getInstance().createNotification(supervisorID, notificationMessage);
		
		return task;
	}
	
	// Search for a task based on title name or supervisor/worker username
	// Returns a list of task that best fits the query
	public List<Task> searchTask(String query) throws NoSuchObjectException, SQLException {
		List<Task> taskList = Task.search(query);
		
		return taskList;
	}
	
	// Sort the task list based on a column and direction
	public List<Task> sortTask(Task.SortBy sortBy, Task.SortDirection direction) throws NoSuchObjectException, SQLException {
		List<Task> taskList = Task.sort(sortBy, direction);
		
		return taskList;
	}
	
	
	
	
	
	// VALIDATORS
	// Validate worker's id
	public static Boolean validateWorkerID(UUID workerID) throws IllegalArgumentException, SQLException {
		User worker = UserController.getUser(workerID);
		
		if(worker != null && worker.getRole().equalsIgnoreCase("WORKER") == true) {
			return true;
		}
		return false;
	}
	
	// Validate supervisor's id
	public static Boolean validateSupervisorID(UUID supervisorID) throws IllegalArgumentException, SQLException {
		User supervisor = UserController.getUser(supervisorID);
		
		if(supervisor != null && supervisor.getRole().equalsIgnoreCase("SUPERVISOR") == true) {
			return true;
		}
		return false;
	}
	
	// Validate title's length. Must be 5-20 characters
	public static Boolean validateTitleLength(String title) {
		title = title.replace(" ", "");
		Integer length = title.length();
		
		if(length < 5 || length > 20) {
			return false;
		}
		return true;
	}
	
	// Validate score range. Must be 0-100
	public static Boolean validateScore(Integer score) {
		if(score < 0 || score > 100) {
			return false;
		}
		return true;
	}
	
	// Validate note's length. Must be 0-50 characters
	public static Boolean validateNoteLength(String note) {
		note = note.replace(" ", "");
		Integer length = note.length();
		
		if(length < 0 || length > 50) {
			return false;
		}
		return true;
	}
}
