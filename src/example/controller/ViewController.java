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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import example.model.Notification;
import example.model.Task;
import example.model.Task.SortBy;
import example.model.Task.SortDirection;
import example.model.TaskRequest;
import example.model.User;
import example.session.Session;
import example.view.AllTaskViewSupervisor;
import example.view.AllTaskViewWorker;
import example.view.AllUserView;
import example.view.ChangePasswordView;
import example.view.CreateTaskView;
import example.view.EditProfileView;
import example.view.FirstPage;
import example.view.LoginPanel;
import example.view.MainFrame;
import example.view.MenuAdminView;
import example.view.MenuSupervisorView;
import example.view.MenuWorkerView;
import example.view.NotificationView;
import example.view.ProfileView;
import example.view.RegisterUserPanel;
import example.view.TaskRequestView;
import example.view.UpdateTaskView;

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
		
		FrameController.getInstance().changePanel(fp);
		
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
					UserController.getInstance().login(username, password);
					
					ViewController.loadMenuView();
				}
				catch(Exception e1) {
					lp.getLbl_Invalid().setVisible(true);
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
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
		
		FrameController.getInstance().changePanel(lp);
		
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
				ViewController.loadMenuWorkerView();
			}
		}
		catch(NoSuchObjectException e) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage());
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
				UserController.getInstance().logout();
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.getInstance().changePanel(mav);
		
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
				ViewController.loadTaskRequestView();
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
				UserController.getInstance().logout();
				// Load first page
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.getInstance().changePanel(msv);
		
		return msv;
	}
	
	// Loads worker's menu view
	public static MenuWorkerView loadMenuWorkerView() {
		MenuWorkerView mwv = new MenuWorkerView();
		
		// Logic for notification button
		mwv.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for view all task button
		mwv.getViewAllTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadAllTaskView();
			}
		});
		
		// Logic for create task view
		mwv.getCreateTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadCreateTaskView();
			}
		});
		
		// Logic for profile button
		mwv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for logout button
		mwv.getLogOutButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Logout and ends session
				UserController.getInstance().logout();
				// Load first page
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.getInstance().changePanel(mwv);
		
		return mwv;
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
				workerList = UserController.getInstance().getUserByRole("WORKER").toArray();
			}
			
			// Creating list of user
			ComboBoxModel<Object> workerComboBoxModel = new DefaultComboBoxModel<Object>(workerList);
			// Adding list of user to combo box
			ctv.getWorkerComboBox().setModel(workerComboBoxModel);
			ctv.getWorkerComboBox().setRenderer(comboBoxCellRenderer);
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
		}
		
		// Creating supervisor combo box
		try {
			// Getting current user
			User currentUser = Session.getInstance().getCurrentUser();
			// Initial supervisorList as an array of currentUser
			Object[] supervisorList = {currentUser};
			// Change supervisorList to actual list of supervisors if current user is worker
			if(currentUser.getRole().equalsIgnoreCase("WORKER") == true) {
				supervisorList = UserController.getInstance().getUserByRole("SUPERVISOR").toArray();
			}
			
			// Creating list of user
			ComboBoxModel<Object> supervisorComboBoxModel = new DefaultComboBoxModel<Object>(supervisorList);
			// Adding list of user to combo box
			ctv.getSupervisorComboBox().setModel(supervisorComboBoxModel);
			ctv.getSupervisorComboBox().setRenderer(comboBoxCellRenderer);
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
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
						TaskHandler.getInstance().createTask(title, workerID, supervisorID, note);
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
		
		FrameController.getInstance().changePanel(ctv);
		
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
					UserController.getInstance().registerUser(username, role, address, DOB, telp);
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
		
		FrameController.getInstance().changePanel(rp);
		
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
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
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
		
		FrameController.getInstance().changePanel(pv);
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
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e2.getMessage());
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
					UserController.getInstance().changePassword(oldPassword, newPassword);
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
		
		FrameController.getInstance().changePanel(cpv);
		
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
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
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
					UserController.getInstance().updateProfile(username, DOB, address, telp);
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
		
		FrameController.getInstance().changePanel(epv);
		
		return epv;
	}
	
	// Loads all user view
	public static AllUserView loadAllUserView() {
		AllUserView alv = new AllUserView();
		
		// Filling the table with user data
		try {
			List<User> allUserList = UserController.getInstance().getAllUser();
			
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
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
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
							UserController.getInstance().resetPassword(selectedUUID);
							// Success message
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Password was reset");
						}
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
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
								UserController.getInstance().deleteUser(selectedUUID);
								// Success message
								JOptionPane.showMessageDialog(MainFrame.getInstance(), "User was deleted");
								
								// Reloads all user view
								ViewController.loadAllUserView();
							}
						}
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
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
		
		FrameController.getInstance().changePanel(alv);
		
		return alv;
	}

	// Loads notification view
	public static NotificationView loadNotificationView() {
		NotificationView nv = new NotificationView();
		
		// Filling the table with notification data
		try {
			// Getting all notification
			List<Notification> allNotifList = NotificationController.getInstance().getAllNotification();
			
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
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
		}
		
		// Logic for mark as read button
		nv.getMarkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Gets current user
					User currentUser = Session.getInstance().getCurrentUser();
					// Mark all notification as read
					NotificationController.getInstance().readAllNotification(currentUser.getId());
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
		
		FrameController.getInstance().changePanel(nv);
		
		return nv;
	}
	
	// Load task table model
	public static TableModel loadTaskTabelModel(String searchTerm, 
			Task.SortBy sortBy, Task.SortDirection sortDirection) {
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
			if((sortBy == null || sortDirection == null) && searchTerm == null) {
				taskList = TaskHandler.getInstance().getAllTask();
			}
			else if(searchTerm != null) {
				taskList = TaskHandler.getInstance().searchTask(searchTerm);
			}
			else {
				taskList = TaskHandler.getInstance().sortTask(sortBy, sortDirection);
			}
			
			// Inserting task data rows
			for(Task t: taskList) {
				UUID 	id				=	t.getId();
				String	title			=	t.getTitle();
				UUID	supervisorID	=	t.getSupervisorID();
				String	supervisorName	=	UserController.getInstance().getUser(supervisorID).getUsername();
				UUID	workerID		=	t.getWorkerID();
				String	workerName		=	UserController.getInstance().getUser(workerID).getUsername();
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
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage());
		}
		
		return taskTableModel;
	}
	
	public static JTable loadTaskListTable(JTable taskTable, String searchTerm, 
			Task.SortBy sortBy, Task.SortDirection sortDirection) {
		// Creating new table model
		TableModel taskTableModel = ViewController.loadTaskTabelModel(searchTerm, sortBy, sortDirection);
		
		// Updating model
		taskTable.setModel(taskTableModel);
		
		// Hiding id, supervisorId, workerId column
		TableColumnModel tcm = taskTable.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tcm.removeColumn(tcm.getColumn(1));
		tcm.removeColumn(tcm.getColumn(2));
		
		return taskTable;
	}
	
	// Loads all task view based on role
	public static void loadAllTaskView() {
		try {
			// Getting current user
			User currentUser = Session.getInstance().getCurrentUser();
			
			// Checking current user's role
			if(currentUser.getRole().equalsIgnoreCase("SUPERVISOR")) {
				// Loads all task view supervisor
				ViewController.loadAllTaskViewSupervisor();
			}
			else if(currentUser.getRole().equalsIgnoreCase("WORKER")) {
				// Loads all task view supervisor
				ViewController.loadAllTaskViewWorker();
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage());
		}
	}
	
	// Loads all task view supervisor
	public static AllTaskViewSupervisor loadAllTaskViewSupervisor() {
		AllTaskViewSupervisor atvs = new AllTaskViewSupervisor();
		
		// Loading initial task table
		ViewController.loadTaskListTable(atvs.getTaskListTable(), null, null, null);
		
		// Logic for update task button
		atvs.getUpdateTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting selected row
					Integer selectedRow = atvs.getTaskListTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= atvs.getTaskListTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task selected");
					}
					else {
						// Getting task's id for delete
						UUID selectedUUID = (UUID) atvs.getTaskListTable().getModel().getValueAt(selectedRow, idColumn);
						// Getting task to be updated
						Task taskToBeUpdated = TaskHandler.getInstance().getTask(selectedUUID);
						// Loads update task view
						ViewController.loadUpdateTaskView(taskToBeUpdated);
					}
				}
				catch(Exception e1) {
					// Error message
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for delete task button
		atvs.getDeleteTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the selected row from task table
					Integer selectedRow = atvs.getTaskListTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= atvs.getTaskListTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task selected");
					}
					else {
						// Confirming delete choice
						Integer confirmResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(), 
								"Are you sure?", "Delete Task", JOptionPane.YES_NO_OPTION);
						
						// If delete was confirmed
						if(confirmResult == JOptionPane.YES_OPTION) {
							// Getting task's id for delete
							UUID selectedUUID = (UUID) atvs.getTaskListTable().getModel().getValueAt(selectedRow, idColumn);
							
							// Deleting task
							TaskHandler.getInstance().deleteTask(selectedUUID);
							// Success message
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task was deleted");
							
							// Updating task table without reloading page
							// Getting sort categories
							Task.SortBy sortBy = (Task.SortBy) atvs.getSortByComboBox().getSelectedItem();
							Task.SortDirection sortDirection = (Task.SortDirection) atvs.getSortDirectionComboBox().getSelectedItem();
							// Loading table
							ViewController.loadTaskListTable(atvs.getTaskListTable(), null, sortBy, sortDirection);
						}
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		atvs.getApproveTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the selected row from task table
					Integer selectedRow = atvs.getTaskListTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= atvs.getTaskListTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task selected");
					}
					else {
						// Getting selected task's id
						UUID selectedID = (UUID) atvs.getTaskListTable().getModel().getValueAt(selectedRow, idColumn);
						// Getting task's score from user
						Integer score = Integer.valueOf(JOptionPane.showInputDialog("Input Task Score"));
						// Approve task
						TaskHandler.getInstance().approveTask(selectedID, score);
						// Success message
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task approved");
						
						// Updating task table without reloading page
						// Getting sort categories
						Task.SortBy sortBy = (Task.SortBy) atvs.getSortByComboBox().getSelectedItem();
						Task.SortDirection sortDirection = (Task.SortDirection) atvs.getSortDirectionComboBox().getSelectedItem();
						// Loading table
						ViewController.loadTaskListTable(atvs.getTaskListTable(), null, sortBy, sortDirection);
					}
				}
				catch(Exception e1) {
					String errorMessage = e1.getMessage();
					if(e1 instanceof NumberFormatException) {
						errorMessage = "Score must be numeric";
					}
					JOptionPane.showMessageDialog(MainFrame.getInstance(), errorMessage);
				}
			}
		});
		
		// Logic for request revision button
		atvs.getRequestRevisionButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the selected row from task table
					Integer selectedRow = atvs.getTaskListTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= atvs.getTaskListTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task selected");
					}
					else {
						// Getting selected task's id
						UUID selectedID = (UUID) atvs.getTaskListTable().getModel().getValueAt(selectedRow, idColumn);
						// Request task revision
						TaskHandler.getInstance().requestTaskRevision(selectedID);
						// Success message
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task revision requested");
						
						// Updating task table without reloading page
						// Getting sort categories
						Task.SortBy sortBy = (Task.SortBy) atvs.getSortByComboBox().getSelectedItem();
						Task.SortDirection sortDirection = (Task.SortDirection) atvs.getSortDirectionComboBox().getSelectedItem();
						// Loading table
						ViewController.loadTaskListTable(atvs.getTaskListTable(), null, sortBy, sortDirection);
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for search button
		atvs.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchTerm = atvs.getSearchTextField().getText();
				ViewController.loadTaskListTable(atvs.getTaskListTable(), searchTerm, null, null);
			}
		});
		
		// Creating sort by combo box
		// Sort by combo box model
		ComboBoxModel<Object> sortByComboBoxModel = new DefaultComboBoxModel<Object>(Task.SortBy.values());
		// Insert model into combo box
		atvs.getSortByComboBox().setModel(sortByComboBoxModel);
		
		// Creating sort direction combo box
		// Sort direction combo box model
		ComboBoxModel<Object> sortDirectionComboBoxModel = new DefaultComboBoxModel<Object>(Task.SortDirection.values());
		// Insert model into combo box
		atvs.getSortDirectionComboBox().setModel(sortDirectionComboBoxModel);
		
		// Logic for sort button
		atvs.getSortButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Updating task table without reloading page
				// Getting sort categories
				Task.SortBy sortBy = (Task.SortBy) atvs.getSortByComboBox().getSelectedItem();
				Task.SortDirection sortDirection = (Task.SortDirection) atvs.getSortDirectionComboBox().getSelectedItem();
				// Loading table
				ViewController.loadTaskListTable(atvs.getTaskListTable(), null, sortBy, sortDirection);
			}
		});
		
		
		// Logic for notification button
		atvs.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for menu button
		atvs.getMenuButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		atvs.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for back button
		atvs.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		FrameController.getInstance().changePanel(atvs);
		
		return atvs;
	}
	
	// Loads all task view worker
	public static AllTaskViewWorker loadAllTaskViewWorker() {
		AllTaskViewWorker atvw = new AllTaskViewWorker();
		
		// Loading initial task table
		ViewController.loadTaskListTable(atvw.getTaskListTable(), null, null, null);
		
		// Logic for submit task button
		atvw.getSubmitTaskButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting selected row
					Integer selectedRow = atvw.getTaskListTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checking if a row is selected
					if(selectedRow < 0 || selectedRow >= atvw.getTaskListTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task selected");
					}
					else {
						// Confirm submission
						Integer confirmResult = JOptionPane.showConfirmDialog(
								MainFrame.getInstance(), "Are you sure?", "Submit Task", JOptionPane.YES_NO_OPTION);
						
						if(confirmResult == JOptionPane.YES_OPTION) {
							// Getting selected task uuid
							UUID selectedUUID = (UUID) atvw.getTaskListTable().getModel().getValueAt(selectedRow, idColumn);
							
							// Submit task
							TaskHandler.getInstance().submitTask(selectedUUID);
							// Success message
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task Submitted");
							// Getting sort categories
							Task.SortBy sortBy = (SortBy) atvw.getSortByComboBox().getSelectedItem();
							Task.SortDirection sortDirection = (SortDirection) atvw.getSortDirectionComboBox().getSelectedItem();
							// Reloads table
							ViewController.loadTaskListTable(atvw.getTaskListTable(), null, sortBy, sortDirection);
						}
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for search button
		atvw.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchTerm = atvw.getSearchTextField().getText();
				ViewController.loadTaskListTable(atvw.getTaskListTable(), searchTerm, null, null);
			}
		});
		
		// Creating sort by combo box
		// Sort by combo box model
		ComboBoxModel<Object> sortByComboBoxModel = new DefaultComboBoxModel<Object>(Task.SortBy.values());
		// Insert model into combo box
		atvw.getSortByComboBox().setModel(sortByComboBoxModel);
		
		// Creating sort direction combo box
		// Sort direction combo box model
		ComboBoxModel<Object> sortDirectionComboBoxModel = new DefaultComboBoxModel<Object>(Task.SortDirection.values());
		// Insert model into combo box
		atvw.getSortDirectionComboBox().setModel(sortDirectionComboBoxModel);
		
		// Logic for sort button
		atvw.getSortButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Updating task table without reloading page
				// Getting sort categories
				Task.SortBy sortBy = (Task.SortBy) atvw.getSortByComboBox().getSelectedItem();
				Task.SortDirection sortDirection = (Task.SortDirection) atvw.getSortDirectionComboBox().getSelectedItem();
				// Loading table
				ViewController.loadTaskListTable(atvw.getTaskListTable(), null, sortBy, sortDirection);
			}
		});
		
		
		// Logic for notification button
		atvw.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for menu button
		atvw.getMenuButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		atvw.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		// Logic for back button
		atvw.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		FrameController.getInstance().changePanel(atvw);
		
		return atvw;
	}
	
	public static UpdateTaskView loadUpdateTaskView(Task taskToBeUpdated) {
		UpdateTaskView utv = new UpdateTaskView();
	
		// Sets update task form to use selected task view data
		// Sets title text field
		utv.getTitleTextField().setText(taskToBeUpdated.getTitle());
		// Sets note text field
		utv.getNoteField().setText(taskToBeUpdated.getNote());
		
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
				workerList = UserController.getInstance().getUserByRole("WORKER").toArray();
			}
			
			// Creating list of user
			ComboBoxModel<Object> workerComboBoxModel = new DefaultComboBoxModel<Object>(workerList);
			// Selecting worker
			workerComboBoxModel.setSelectedItem(UserController.getInstance().getUser(taskToBeUpdated.getWorkerID()));
			// Adding list of user to combo box
			utv.getWorkerComboBox().setModel(workerComboBoxModel);
			utv.getWorkerComboBox().setRenderer(comboBoxCellRenderer);
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
		}
		
		// Creating supervisor combo box
		try {
			// Getting current user
			User currentUser = Session.getInstance().getCurrentUser();
			// Initial supervisorList as an array of currentUser
			Object[] supervisorList = {currentUser};
			// Change supervisorList to actual list of supervisors if current user is worker
			if(currentUser.getRole().equalsIgnoreCase("WORKER") == true) {
				supervisorList = UserController.getInstance().getUserByRole("SUPERVISOR").toArray();
			}
			
			// Creating list of user
			ComboBoxModel<Object> supervisorComboBoxModel = new DefaultComboBoxModel<Object>(supervisorList);
			// Selecting supervisor
			supervisorComboBoxModel.setSelectedItem(UserController.getInstance().getUser(taskToBeUpdated.getSupervisorID()));
			// Adding list of user to combo box
			utv.getSupervisorComboBox().setModel(supervisorComboBoxModel);
			utv.getSupervisorComboBox().setRenderer(comboBoxCellRenderer);
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
		}
		
		// Logic for update button
		utv.getUpdateButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting updated data
					UUID taskID			=	taskToBeUpdated.getId();
					String title		=	utv.getTitleTextField().getText();
					UUID workerID		=	((User) utv.getWorkerComboBox().getSelectedItem()).getId();
					UUID supervisorID	=	((User) utv.getSupervisorComboBox().getSelectedItem()).getId();
					Integer score		=	Integer.parseInt(utv.getScoreTextField().getText());
					String note			=	utv.getNoteField().getText();
					
					// Update task
					TaskHandler.getInstance().updateTask(taskID, title, workerID, supervisorID, score, note);
				}
				catch(Exception e1) {
					// Error message
					String errorMessage = (e1 instanceof NumberFormatException) ? "Score must be numeric" : e1.getMessage();
					JOptionPane.showMessageDialog(MainFrame.getInstance(), errorMessage);
				}
			}
		});
		
		// Logic for back button
		utv.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads all task view
				ViewController.loadAllTaskView();
			}
		});
		
		// Logic for notification button
		utv.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		// Logic for menu button
		utv.getMenuButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		utv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		FrameController.getInstance().changePanel(utv);
		
		return utv;
	}
	
	// Load task request table model
	public static TableModel loadTaskRequestTabelModel() {
		// Making table model
		String[] taskRequestTableHeader = {"id", "Title", "supervisorID", "Supervisor", 
				"workerID", "Worker", "Note"};
		DefaultTableModel taskTableModel = new DefaultTableModel(taskRequestTableHeader, 0) {
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
			List<TaskRequest> taskRequestList = TaskRequestHandler.getInstance().getAllTaskRequest();
			
			// Inserting task data rows
			for(TaskRequest tr: taskRequestList) {
				UUID 	id				=	tr.getId();
				String	title			=	tr.getTitle();
				UUID	supervisorID	=	tr.getSupervisorID();
				String	supervisorName	=	UserController.getInstance().getUser(supervisorID).getUsername();
				UUID	workerID		=	tr.getWorkerID();
				String	workerName		=	UserController.getInstance().getUser(workerID).getUsername();
				String	note			=	tr.getNote();
				
				Object[] rowData = {id, title, supervisorID, supervisorName, workerID, workerName, note};
				taskTableModel.addRow(rowData);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getMessage());
		}
		
		return taskTableModel;
	}
	
	// Loads task request table
	public static JTable loadTaskRequestTable(JTable taskRequestTable) {
		// Creating new table model
		TableModel taskRequestTableModel = ViewController.loadTaskRequestTabelModel();
		
		// Updating model
		taskRequestTable.setModel(taskRequestTableModel);
		
		// Hiding id, supervisorId, workerId column
		TableColumnModel tcm = taskRequestTable.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		tcm.removeColumn(tcm.getColumn(1));
		tcm.removeColumn(tcm.getColumn(2));
		
		return taskRequestTable;
	}
	
	// Load task request view
	public static TaskRequestView loadTaskRequestView() {
		TaskRequestView trv = new TaskRequestView();
		
		// Loads task request table
		ViewController.loadTaskRequestTable(trv.getTaskRequestTable());
		
		// Logic for accept button
		trv.getAcceptButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting selected task request
					Integer selectedRow = trv.getTaskRequestTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checks if a row is selected
					if(selectedRow < 0 || selectedRow >= trv.getTaskRequestTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task request selected");
					}
					else {
						// Get confirming input
						Integer confirmResult = JOptionPane.showConfirmDialog(
								MainFrame.getInstance(), "Are you sure?", "Accept Task Request", JOptionPane.YES_NO_OPTION);
						
						// Accept task request confirmed
						if(confirmResult == JOptionPane.YES_OPTION) {
							// Getting selected task request id
							UUID taskRequestID = (UUID) trv.getTaskRequestTable().getModel().getValueAt(selectedRow, idColumn);
							
							// Accept task request
							TaskRequestHandler.getInstance().acceptTaskRequest(taskRequestID);
							// Success message
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task Request Accepted");
							// Reloads task request table
							ViewController.loadTaskRequestTable(trv.getTaskRequestTable());
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for accept button
		trv.getRejectButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting selected task request
					Integer selectedRow = trv.getTaskRequestTable().getSelectedRow();
					Integer idColumn = 0;
					
					// Checks if a row is selected
					if(selectedRow < 0 || selectedRow >= trv.getTaskRequestTable().getRowCount()) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "No task request selected");
					}
					else {
						// Get confirming input
						Integer confirmResult = JOptionPane.showConfirmDialog(
								MainFrame.getInstance(), "Are you sure?", "Reject Task Request", JOptionPane.YES_NO_OPTION);
						
						// Reject task request confirmed
						if(confirmResult == JOptionPane.YES_OPTION) {
							// Getting selected task request id
							UUID taskRequestID = (UUID) trv.getTaskRequestTable().getModel().getValueAt(selectedRow, idColumn);
							
							// Accept task request
							TaskRequestHandler.getInstance().rejectTaskRequest(taskRequestID);
							// Success message
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Task Request Rejected");
							// Reloads task request table
							ViewController.loadTaskRequestTable(trv.getTaskRequestTable());
						}
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		// Logic for menu button
		trv.getMenuButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for back button
		trv.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads menu view
				ViewController.loadMenuView();
			}
		});
		
		// Logic for profile button
		trv.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads profile view
				ViewController.loadProfileView();
			}
		});
		
		trv.getNotifButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Loads notification view
				ViewController.loadNotificationView();
			}
		});
		
		FrameController.getInstance().changePanel(trv);
		
		return trv;
	}
}
