package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import example.model.TaskRequest;
import example.model.User;
import example.session.Session;

public class TaskRequestHandler {
	// STATIC ATTRIBUTES
	// Instance of task request handler
	private static TaskRequestHandler instance;
	
	
	
	
	
	// STATIC FUNCTIONS
	// Gets instance of task request handler
	public static TaskRequestHandler getInstance() {
		if(instance == null) {
			instance = new TaskRequestHandler();
		}
		return instance;
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public TaskRequestHandler() {}
	
	// Creates a new TaskRequest object and saves it to database
	public TaskRequest createTaskRequest(String title, UUID supervisorID, UUID workerID, String note) 
			throws IllegalArgumentException, SQLException {
		// Validate parameters
		// Validate title length, 5-20 characters
		if(TaskHandler.validateTitleLength(title) == false) {
			throw new IllegalArgumentException("Title's length must be 5-20 characters");
		}
		// Validate if the user that have workerID has worker role
		else if(TaskHandler.validateWorkerID(workerID) == false) {
			throw new IllegalArgumentException("Worker does not exist in database");
		}
		// Validate if the user that have supervisorID has supervisor role
		else if(TaskHandler.validateSupervisorID(supervisorID) == false) {
			throw new IllegalArgumentException("Supervisor does not exist in database");
		}
		// Validate note length, 0-50 characters
		else if(TaskHandler.validateNoteLength(note) == false) {
			throw new IllegalArgumentException("Note's length must be 0-50 characters");
		}
		
		// Generate random uuid for task request
		UUID id = UUID.randomUUID();
		// Create new task request object
		TaskRequest newTaskRequest = new TaskRequest(id, workerID, supervisorID, title, note);
		// Saves task request object into database
		newTaskRequest.save();
		
		return newTaskRequest;
	}
	
	// Gets a specific TaskReuqest object from database based on its id
	public TaskRequest getTaskRequest(UUID taskRequestID) throws SQLException {
		TaskRequest taskRequest = TaskRequest.get(taskRequestID);
		
		return taskRequest;
	}
	
	// Gets all task request that current user has
	public List<TaskRequest> getAllTaskRequest() throws NoSuchObjectException, SQLException {
		// Gets current user
		User currentUser = Session.getInstance().getCurrentUser();
		// Gets task request list that have current user as supervisor or worker
		List<TaskRequest> taskRequestList = TaskRequest.getAll(currentUser.getId());
		
		return taskRequestList;
	}
	
	// Accepts a task request
	// Deletes TaskRequest from database then creates a task from it, and gives a notification to the requester
	public TaskRequest acceptTaskRequest(UUID taskRequestID) throws NoSuchObjectException, SQLException {
		// Get task request data
		TaskRequest taskRequest = TaskRequest.get(taskRequestID);
		String title = taskRequest.getTitle();
		UUID supervisorID = taskRequest.getSupervisorID();
		UUID workerID = taskRequest.getWorkerID();
		String note = taskRequest.getNote();
		
		// Create task based on task request data and saves it into database
		TaskHandler.getInstance().createTask(title, workerID, supervisorID, note);
		
		// Create notification for worker
		// Gets supervisor name
		String supervisorName = UserController.getInstance().getUser(supervisorID).getUsername();
		// Create notification message: [Supervisor] has accepted your task request "[Task Title]"
		String notificationMessage = String.format("%s has accepted your task request \"%s\"", supervisorName, title);
		// Create notification object and saves it into database
		NotificationController.getInstance().createNotification(workerID, notificationMessage);
		
		// Delete task request object from database
		taskRequest.delete();
		
		return taskRequest;
	}
	
	// Rejects a task request
	// Deletes TaskRequest from database, and gives a notification to the requester
	public TaskRequest rejectTaskRequest(UUID taskRequestID) throws SQLException {
		// Get task request data
		TaskRequest taskRequest = TaskRequest.get(taskRequestID);
		String title = taskRequest.getTitle();
		UUID supervisorID = taskRequest.getSupervisorID();
		UUID workerID = taskRequest.getWorkerID();
		
		// Delete task request object from database
		taskRequest.delete();
		
		// Create notification for worker
		// Gets supervisor name
		String supervisorName = UserController.getInstance().getUser(supervisorID).getUsername();
		// Create notification message: [Supervisor] has rejected your task request "[Task Title]"
		String notificationMessage = String.format("%s has rejected your task request \"%s\"", supervisorName, title);
		// Create notification object and saves it into database
		NotificationController.getInstance().createNotification(workerID, notificationMessage);
		
		return taskRequest;
	}
}
