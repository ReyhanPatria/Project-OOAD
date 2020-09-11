package example.controller;

import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import example.model.TaskRequest;
import example.model.User;
import example.session.Session;

public class TaskRequestHandler {
	// STATIC FUNCTIONS
	// Creates a new TaskRequest object and saves it to database
	public static TaskRequest createTaskRequest(String title, UUID supervisorID, UUID workerID, String note) 
			throws IllegalArgumentException, SQLException {
		// Validate parameters
		if(TaskHandler.validateTitleLength(title) == false) {
			throw new IllegalArgumentException("Title's length must be 5-20 characters");
		}
		else if(TaskHandler.validateWorkerID(workerID) == false) {
			throw new IllegalArgumentException("Worker does not exist in database");
		}
		else if(TaskHandler.validateSupervisorID(supervisorID) == false) {
			throw new IllegalArgumentException("Supervisor does not exist in database");
		}
		else if(TaskHandler.validateNoteLength(note) == false) {
			throw new IllegalArgumentException("Note's length must be 0-50 characters");
		}
		
		UUID id = UUID.randomUUID();
		
		TaskRequest newTaskRequest = new TaskRequest(id, workerID, supervisorID, title, note);
		newTaskRequest.save();
		
		return newTaskRequest;
	}
	
	// Gets a specific TaskReuqest object from database based on its id
	public TaskRequest getTaskRequest(UUID taskRequestID) throws SQLException {
		TaskRequest taskRequest = TaskRequest.get(taskRequestID);
		
		return taskRequest;
	}
	
	// Gets all task request that current user has
	public static List<TaskRequest> getAllTaskRequest() throws NoSuchObjectException, SQLException {
		User currentUser = Session.getInstance().getCurrentUser();
		
		List<TaskRequest> taskRequestList = TaskRequest.getAll(currentUser.getId());
		
		return taskRequestList;
	}
	
	// Accepts a task request
	// Deletes TaskRequest from database then creates a task from it, and gives a notification to the requester
	public static TaskRequest acceptTaskRequest(UUID taskRequestID) throws NoSuchObjectException, SQLException {
		TaskRequest taskRequest = TaskRequest.get(taskRequestID);
		
		String title = taskRequest.getTitle();
		UUID supervisorID = taskRequest.getSupervisorID();
		UUID workerID = taskRequest.getWorkerID();
		String note = taskRequest.getNote();
		
		TaskHandler.createTask(title, workerID, supervisorID, note);
		
		String supervisorName = UserController.getUser(supervisorID).getUsername();
		String notificationMessage = String.format("%s has accepted your task request \"%s\"", supervisorName, title);
		NotificationController.createNotification(workerID, notificationMessage);
		
		taskRequest.delete();
		
		return taskRequest;
	}
	
	// Rejects a task request
	// Deletes TaskRequest from database, and gives a notification to the requester
	public static TaskRequest rejectTaskRequest(UUID taskRequestID) throws SQLException {
		TaskRequest taskRequest = TaskRequest.get(taskRequestID);
		
		String title = taskRequest.getTitle();
		UUID supervisorID = taskRequest.getSupervisorID();
		UUID workerID = taskRequest.getWorkerID();
		
		taskRequest.delete();
		
		String supervisorName = UserController.getUser(supervisorID).getUsername();
		String notificationMessage = String.format("%s has rejected your task request \"%s\"", supervisorName, title);
		NotificationController.createNotification(workerID, notificationMessage);
		
		return taskRequest;
	}
}
