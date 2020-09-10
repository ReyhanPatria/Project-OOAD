package example.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import example.model.Notification;
import example.model.Task;
import example.model.User;
import example.session.Session;
import example.view.AllTaskView;
import example.view.AllUserView;
import example.view.ChangePasswordView;
import example.view.CreateTaskView;
import example.view.EditProfileView;
import example.view.FirstPage;
import example.view.LoginPanel;
import example.view.MainFrame;
import example.view.MenuAdminView;
import example.view.MenuSupervisorView;
import example.view.NotificationView;
import example.view.ProfileView;
import example.view.RegisterUserPanel;

public class ViewController {
	
	// STATIC FUNCTIONS
	// Loads start up page
	public static FirstPage loadFirstPage() {
		FirstPage fp = new FirstPage();
		
		// Logic for login button
		fp.getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadLoginPanel();
			}
		});
		
		FrameController.changePanel(fp);
		
		return fp;
	}
	
	// Loads login page
	public static LoginPanel loadLoginPanel() {
		LoginPanel lp = new LoginPanel();
		
		// Logic for clicking login button
		lp.getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = lp.getUsernameTextField().getText();
				String password = String.valueOf(lp.getPasswordTextField().getPassword());
				
				try {
					UserController.login(username, password);
					
					ViewController.loadMenuView();
				}
				catch(IllegalArgumentException e1) {
					lp.getLbl_Invalid().setVisible(true);
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// Logic for clicking back button
		lp.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.changePanel(lp);
		
		return lp;
	}
	
	// Function to decide which menu to load after login
	public static void loadMenuView() {
		try {
			String userRole = Session.getInstance().getCurrentUser().getRole();
			
			if(userRole.equalsIgnoreCase("admin")) {
				ViewController.loadMenuAdminView();
			}
			else if(userRole.equalsIgnoreCase("supervisor")) {
				ViewController.loadMenuSupervisorView();
			}
			else if(userRole.equalsIgnoreCase("worker")) {
				/*
				 * TODO: Create loadMenuWorkerView()
				 */
			}
		}
		catch(NoSuchObjectException e) {
			e.printStackTrace();
		}
	}
	
	// Loads menu for admin
	public static MenuAdminView loadMenuAdminView() {
		MenuAdminView mav = new MenuAdminView();
		
		// Logic for profile button
		mav.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadProfileView();
			}
		});
		
		// Logic for view all user button
		mav.getViewButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadAllUserView();
			}
		});
		
		// Logic for logout button
		mav.getLogOutButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.logout();
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.changePanel(mav);
		
		return mav;
	}
	
	// Loads menu for supervisor
	public static MenuSupervisorView loadMenuSupervisorView() {
		MenuSupervisorView msv = new MenuSupervisorView();
		
		// Logic for notification button
		msv.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for view all task button
		msv.getViewAllTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadAllTaskView();
			}
		});
		
		// Logic for view task request button
		msv.getViewTaskRequestButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * TODO: Create loadTaskRequestView()
				 * 
				 */
			}
		});
		
		// Logic for create task view
		msv.getCreateTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadCreateTaskView();
			}
		});
		
		// Logic for profile button
		msv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for logout button
		msv.getLogOutButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Logout and ends session
				UserController.logout();
				// Load first page
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.changePanel(msv);
		
		return msv;
	}
	
	// Loads create task view
	public static CreateTaskView loadCreateTaskView() {
		CreateTaskView ctv = new CreateTaskView();
		
		// Custom combo box cell renderer
		DefaultListCellRenderer comboBoxCellRenderer = new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index,
					boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				// Make combo box show user's username
				if(value instanceof User) {
					User u = (User) value;
	                setText(u.getUsername());
				}
				return this;
			}
		};
		
		// Creating worker combo box
		try {
			// Getting current user
			User currentUser = Session.getInstance().getCurrentUser();
			// Initial workerList as an array of currentUser
			Object[] workerList = {currentUser};
			// Change workerList to actual list of workers if current user is supervisor
			if(currentUser.getRole().equalsIgnoreCase("SUPERVISOR") == true) {
				workerList = UserController.getUserByRole("WORKER").toArray();
			}
			
			// Creating list of user
			ComboBoxModel<Object> workerComboBoxModel = new DefaultComboBoxModel<Object>(workerList);
			// Adding list of user to combo box
			ctv.getWorkerComboBox().setModel(workerComboBoxModel);
			ctv.getWorkerComboBox().setRenderer(comboBoxCellRenderer);
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		
		// Creating supervisor combo box
		try {
			// Getting current user
			User currentUser = Session.getInstance().getCurrentUser();
			// Initial supervisorList as an array of currentUser
			Object[] supervisorList = {currentUser};
			// Change supervisorList to actual list of supervisors if current user is worker
			if(currentUser.getRole().equalsIgnoreCase("WORKER") == true) {
				supervisorList = UserController.getUserByRole("SUPERVISOR").toArray();
			}
			
			// Creating list of user
			ComboBoxModel<Object> supervisorComboBoxModel = new DefaultComboBoxModel<Object>(supervisorList);
			// Adding list of user to combo box
			ctv.getSupervisorComboBox().setModel(supervisorComboBoxModel);
			ctv.getSupervisorComboBox().setRenderer(comboBoxCellRenderer);
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		
		// Logic for back button
		ctv.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for notification button
		ctv.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for menu button
		ctv.getMenuButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		ctv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for create button
		ctv.getCreateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create task / task request depends on role
				try {
					// Check confirm
					Integer confirmResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(), 
							"Are you sure?", "Create Task", JOptionPane.YES_NO_OPTION);
					
					// If create task was confirmed
					if(confirmResult == JOptionPane.YES_OPTION) {
						String title = ctv.getTitleTextField().getText();
						UUID workerID = ((User) ctv.getWorkerComboBox().getSelectedItem()).getId();
						UUID supervisorID = ((User) ctv.getSupervisorComboBox().getSelectedItem()).getId();
						String note = ctv.getNoteField().getText();
						
						// Create task/task request
						TaskHandler.createTask(title, workerID, supervisorID, note);
						// Success message
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task was created");
						// Loads menu view
						ViewController.loadMenuView();
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		FrameController.changePanel(ctv);
		
		return ctv;
	}
	
	// Loads register user panel
	public static RegisterUserPanel loadRegisterUserPanel() {
		RegisterUserPanel rp = new RegisterUserPanel();
		
		rp.getRegisterButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String 	username 		= 	rp.getUsernameTextField().getText();
				String 	role 			= 	rp.getRoleComboBox().getSelectedItem().toString();
				String 	address 		= 	rp.getAddressTextField().getText();
				Date 	DOB 			= 	new Date(rp.getDOBDateChooser().getDateEditor().getDate().getTime());
				String 	telp 			= 	rp.getTelpTextField().getText();
				
				try {
					UserController.registerUser(username, role, address, DOB, telp);
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "User registered successfully");
					ViewController.loadAllUserView();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for profile button
		rp.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for home button
		rp.getHomeButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads main menu view
				ViewController.loadMenuView();
			}
		});	
		
		FrameController.changePanel(rp);
		
		return rp;
	}
	
	public static void loadProfileView() {
		ProfileView pv = new ProfileView();
		
		// Setting profile info
		try {
			User currentUser = Session.getInstance().getCurrentUser();
			
			pv.getUsernameField().setText(currentUser.getUsername());
			pv.getDOBField().setText(currentUser.getDOB().toString());
			pv.getPhoneNumberField().setText(currentUser.getTelp());
			pv.getAddressField().setText(currentUser.getAddress());
		}
		catch(NoSuchObjectException e1) {
			e1.printStackTrace();
		}
		
		// Logic for home button
		pv.getHomeButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads main menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for change password button
		pv.getChangePasswordButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads change password view
				ViewController.loadChangePasswordView();
			}
		});
		
		// Logic for edit profile button
		pv.getEditProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 ViewController.loadEditProfileView();
			}
		});
		
		FrameController.changePanel(pv);
	}
	
	public static ChangePasswordView loadChangePasswordView() {
		ChangePasswordView cpv = new ChangePasswordView();
		
		// Check currently logged in user's role
		try {
			User currentUser = Session.getInstance().getCurrentUser();
			
			// Remove notification button if admin is logged in
			if(currentUser.getRole().equalsIgnoreCase("ADMIN")) {
				cpv.remove(cpv.getNotifButton());
			}
		}
		catch(NoSuchObjectException e2) {
			e2.printStackTrace();
		}
		
		// Logic for update button
		cpv.getUpdateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Gets old and new password
				String oldPassword = cpv.getOldPassField().getText();
				String newPassword = cpv.getNewPassField().getText();
				
				try {
					// Change password
					UserController.changePassword(oldPassword, newPassword);
					// Success message
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Password changed successfully!");
					// Loads profile view
					ViewController.loadProfileView();
				}
				catch(Exception e1) {
					// Shows error message in a message box
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for notification button
		cpv.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for back button
		cpv.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for menu button
		cpv.getMenuButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		cpv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Load profile view
				ViewController.loadProfileView();
			}
		});
		
		FrameController.changePanel(cpv);
		
		return cpv;
	}
	
	public static EditProfileView loadEditProfileView() {
		EditProfileView epv = new EditProfileView();

		// Sets default text for text fields
		try {
			User currentUser = Session.getInstance().getCurrentUser();
			
			epv.getUsernameField().setText(currentUser.getUsername());
			epv.getAddressField().setText(currentUser.getAddress());
			epv.getDobDateChooser().setDate(currentUser.getDOB());
			epv.getPhoneNumberField().setText(currentUser.getTelp());
		}
		catch(NoSuchObjectException e1) {
			e1.printStackTrace();
		}
		
		// Logic for update button
		epv.getUpdateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Gets inputed data
					String username = epv.getUsernameField().getText();
					Date DOB = new Date(epv.getDobDateChooser().getDateEditor().getDate().getTime());
					String address = epv.getAddressField().getText();
					String telp = epv.getPhoneNumberField().getText();
					
					// Updates profile
					UserController.updateProfile(username, DOB, address, telp);
					// Success message
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Profile updated successfully");
					// Load profile view
					ViewController.loadProfileView();
				}
				catch(Exception e1) {
					// Error message
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for back button
		epv.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for profile button
		epv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for home button
		epv.getHomeButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		FrameController.changePanel(epv);
		
		return epv;
	}
	
	// Loads all user view
	public static AllUserView loadAllUserView() {
		AllUserView alv = new AllUserView();
		
		// Filling the table with user data
		try {
			List<User> allUserList = UserController.getAllUser();
			
			// Creating table content
			String[] tableHeader = {"id","Username", "Role", "Address", "DOB", "Telp"};
			DefaultTableModel userDataModel = new DefaultTableModel(tableHeader, 0) {
				private static final long serialVersionUID = 1L;

				// Making the cells not editable
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// Inserting user data rows
			for(User u: allUserList) {
				Object[] rowData = {u.getId().toString(), 
									u.getUsername(), 
									u.getRole(), 
									u.getAddress(), 
									u.getDOB().toString(), 
									u.getTelp()};
				userDataModel.addRow(rowData);
			}
			
			// Setting table content
			alv.getUserDataTable().setModel(userDataModel);
			
			// Hiding id column
			TableColumnModel tcm = alv.getUserDataTable().getColumnModel();
			tcm.removeColumn(tcm.getColumn(0));
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		alv.getCreateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadRegisterUserPanel();
			}
		});
		
		// Logic for reset password button
		alv.getResetPasswordButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the selected row
					Integer selectedRow = alv.getUserDataTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= alv.getUserDataTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No user selected");
					}
					else {
						// Confirming reset choice
						Integer confirmResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(), 
								"Are you sure?", "Reset Password", JOptionPane.YES_NO_OPTION);
						
						// If reset was confirmed
						if(confirmResult == JOptionPane.YES_OPTION) {
							// Getting user's id for reset
							String selectedId = (String) alv.getUserDataTable().getModel().getValueAt(selectedRow, idColumn);
							UUID selectedUUID = UUID.fromString(selectedId);
							
							// Resetting user's password
							UserController.resetPassword(selectedUUID);
							// Success message
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Password was reset");
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// Logic for delete user button
		alv.getDeleteUserButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the selected row
					Integer selectedRow = alv.getUserDataTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= alv.getUserDataTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No user selected");
					}
					else {
						// Confirming delete choice
						Integer confirmResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(), 
								"Are you sure?", "Delete User", JOptionPane.YES_NO_OPTION);
						
						// If reset was confirmed
						if(confirmResult == JOptionPane.YES_OPTION) {
							// Getting user's id for reset
							String selectedId = (String) alv.getUserDataTable().getModel().getValueAt(selectedRow, idColumn);
							UUID selectedUUID = UUID.fromString(selectedId);
							
							// Check if selected user is currently logged in user
							User currentUser = Session.getInstance().getCurrentUser();
							if(currentUser.getId().equals(selectedUUID)) {
								JOptionPane.showMessageDialog(MainFrame.getInstance(), "You cannot delete your own account!");
							}
							else {
								// Resetting user's password
								UserController.deleteUser(selectedUUID);
								// Success message
								JOptionPane.showMessageDialog(MainFrame.getInstance(), "User was deleted");
								
								// Reloads all user view
								ViewController.loadAllUserView();
							}
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// Logic for home button
		alv.getHomeButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads home menu
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		alv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Load profile menu
				ViewController.loadProfileView();
			}
		});
		
		FrameController.changePanel(alv);
		
		return alv;
	}

	// Loads notification view
	public static NotificationView loadNotificationView() {
		NotificationView nv = new NotificationView();
		
		// Filling the table with notification data
		try {
			// Getting all notification
			List<Notification> allNotifList = NotificationController.getAllNotification();
			
			// Creating table content
			String[] tableHeader = {"id","Message", "Status"};
			DefaultTableModel notifDataModel = new DefaultTableModel(tableHeader, 0) {
				private static final long serialVersionUID = 1L;

				// Making the cells not editable
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// Inserting notification data rows
			for(Notification n: allNotifList) {
				String id = n.getId().toString();
				String message = n.getMessage();
				String status = (n.getReadAt() == null) ? "Unread" : "Read at " + n.getReadAt().toString();
				
				Object[] rowData = {id, message, status};
				notifDataModel.addRow(rowData);
			}
			
			// Setting table content
			nv.getNotifDataTable().setModel(notifDataModel);
			
			// Hiding id column
			TableColumnModel tcm = nv.getNotifDataTable().getColumnModel();
			tcm.removeColumn(tcm.getColumn(0));
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		
		// Logic for mark as read button
		nv.getMarkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Gets current user
					User currentUser = Session.getInstance().getCurrentUser();
					// Mark all notification as read
					NotificationController.readAllNotification(currentUser.getId());
					// Reloads notification view
					ViewController.loadNotificationView();
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for home button
		nv.getHomeButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads home menu
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		nv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Load profile menu
				ViewController.loadProfileView();
			}
		});
		
		FrameController.changePanel(nv);
		
		return nv;
	}
	
	// Load task table model
	public static TableModel loadTaskTabelModel(Task.SortBy sortBy, Task.SortDirection sortDirection) {
		String[] taskTableHeader = {"id", "Title", "supervisorID", "Supervisor", 
				"workerID", "Worker", "Note", "Revision Count", "Submitted", "Approved Date", 
				"Score"};
		DefaultTableModel taskTableModel = new DefaultTableModel(taskTableHeader, 0) {
			private static final long serialVersionUID = 1L;
			// Making the cells not editable
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// Adding data to table model
		try {
			// Getting task list based on sort categories
			List<Task> taskList = null;
			if(sortBy == null || sortDirection == null) {
				taskList = TaskHandler.getAllTask();
			}
			else {
				taskList = TaskHandler.sortTask(sortBy, sortDirection);
			}
			
			// Inserting task data rows
			for(Task t: taskList) {
				UUID 	id				=	t.getId();
				String	title			=	t.getTitle();
				UUID	supervisorID	=	t.getSupervisorID();
				String	supervisorName	=	UserController.getUser(supervisorID).getUsername();
				UUID	workerID		=	t.getWorkerID();
				String	workerName		=	UserController.getUser(workerID).getUsername();
				String	note			=	t.getNote();
				Integer	revisionCount	=	t.getRevisionCount();
				String	isSubmitted		=	(t.getIsSubmitted()) ? "Yes" : "No";
				String	approvedDate	=	(t.getApprovedAt() == null) ? "Not approved" : t.getApprovedAt().toString();
				
				Object[] rowData = {id, title, supervisorID, supervisorName, workerID, workerName, note, 
						revisionCount, isSubmitted, approvedDate};
				taskTableModel.addRow(rowData);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return taskTableModel;
	}
	
	// Loads all task view
	public static AllTaskView loadAllTaskView() {
		AllTaskView atv = new AllTaskView();
		
		// TODO: Create button, combo box, search bar logic
		// Creating table model
		TableModel taskTableModel = ViewController.loadTaskTabelModel(null, null);
		atv.getTaskListTable().setModel(taskTableModel);
		// Hiding id, supervisorId, workerId column
		TableColumnModel tcm = atv.getTaskListTable().getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tcm.removeColumn(tcm.getColumn(1));
		tcm.removeColumn(tcm.getColumn(2));
		
		// TODO: Logic for update task, approve task, request revision button
		// Logic for update task button
		atv.getUpdateTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Create loadUpdateTaskView()
			}
		});
		
		// Logic for delete task button
		atv.getDeleteTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the selected row from task table
					Integer selectedRow = atv.getTaskListTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= atv.getTaskListTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task selected");
					}
					else {
						// Confirming delete choice
						Integer confirmResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(), 
								"Are you sure?", "Delete Task", JOptionPane.YES_NO_OPTION);
						
						// If delete was confirmed
						if(confirmResult == JOptionPane.YES_OPTION) {
							// Getting task's id for delete
							UUID selectedUUID = (UUID) atv.getTaskListTable().getModel().getValueAt(selectedRow, idColumn);
							
							// Deleting task
							TaskHandler.deleteTask(selectedUUID);
							// Success message
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task was deleted");
							
							// Updating task table without reloading page
							// Getting sort categories
							Task.SortBy sortBy = (Task.SortBy) atv.getSortByComboBox().getSelectedItem();
							Task.SortDirection sortDirection = (Task.SortDirection) atv.getSortDirectionComboBox().getSelectedItem();
							
							// Creating new table model
							TableModel taskTableModel = ViewController.loadTaskTabelModel(sortBy, sortDirection);
							// Updating model
							atv.getTaskListTable().setModel(taskTableModel);
							
							// Hiding id, supervisorId, workerId column
							TableColumnModel tcm = atv.getTaskListTable().getColumnModel();
							tcm.removeColumn(tcm.getColumn(0));
							tcm.removeColumn(tcm.getColumn(1));
							tcm.removeColumn(tcm.getColumn(2));
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// Creating sort by combo box
		// Sort by combo box model
		ComboBoxModel<Object> sortByComboBoxModel = new DefaultComboBoxModel<Object>(Task.SortBy.values());
		// Insert model into combo box
		atv.getSortByComboBox().setModel(sortByComboBoxModel);
		
		// Creating sort direction combo box
		// Sort direction combo box model
		ComboBoxModel<Object> sortDirectionComboBoxModel = new DefaultComboBoxModel<Object>(Task.SortDirection.values());
		// Insert model into combo box
		atv.getSortDirectionComboBox().setModel(sortDirectionComboBoxModel);
		
		// Logic for sort button
		atv.getSortButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Getting sort categories
				Task.SortBy sortBy = (Task.SortBy) atv.getSortByComboBox().getSelectedItem();
				Task.SortDirection sortDirection = (Task.SortDirection) atv.getSortDirectionComboBox().getSelectedItem();
				
				// Creating new table model
				TableModel taskTableModel = ViewController.loadTaskTabelModel(sortBy, sortDirection);
				// Updating model
				atv.getTaskListTable().setModel(taskTableModel);
				
				// Hiding id, supervisorId, workerId column
				TableColumnModel tcm = atv.getTaskListTable().getColumnModel();
				tcm.removeColumn(tcm.getColumn(0));
				tcm.removeColumn(tcm.getColumn(1));
				tcm.removeColumn(tcm.getColumn(2));
			}
		});
		
		
		// Logic for notification button
		atv.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for menu button
		atv.getMenuButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		atv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for back button
		atv.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		FrameController.changePanel(atv);
		
		return atv;
	}
}
