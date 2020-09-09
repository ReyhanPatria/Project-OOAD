package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ViewAllTaskView extends JPanel implements ViewPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel bg;
	
	private JButton menuButton;
	private JButton approveTaskButton;
	private JButton profileButton;
	private JButton notifButton;
	private JButton backButton;
	private JButton deleteTaskButton;
	private JButton requestRevisionButton;
	private JButton updateTaskButton;
	
	private JComboBox<Object> sortBy;
	
	private JTextField txtSearch;
	
	private JTable taskListTable;
	
	public ViewAllTaskView() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		this.setLayout(null);
		
		//All Task Table
		taskListTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(taskListTable);
		tableScrollPane.setBounds(200, 250, 950, 300);
		this.add(tableScrollPane);
		
		// Sort by combo box
		sortBy = new JComboBox<Object>();
		sortBy.setBounds(243, 160, 205, 40);
		sortBy.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		this.add(sortBy);
		
		// search field
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		txtSearch.setText("Search...");
		txtSearch.setBounds(406, 15, 500, 50);
		txtSearch.setColumns(10);
		this.add(txtSearch);
		
		// Button for view notification
		notifButton = new JButton("");
		notifButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Notifications.png"));
		notifButton.setBounds(991, 15, 50, 50);
		notifButton.setBorderPainted(false);
		notifButton.setOpaque(false);
		notifButton.setContentAreaFilled(false);
		notifButton.setFocusable(false);
		notifButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(notifButton);
		
		// profile button
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1210, 15, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setFocusable(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(profileButton);
		
		// menu button
		menuButton = new JButton("");	
		menuButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Menu.png"));
		menuButton.setBounds(1060, 15, 134, 48);
		menuButton.setBorderPainted(false);
		menuButton.setOpaque(false);
		menuButton.setContentAreaFilled(false);
		menuButton.setFocusable(false);
		menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(menuButton);

		// back button
		backButton = new JButton("");	
		backButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Back.png"));
		backButton.setBounds(50, 160, 134, 48);
		backButton.setBorderPainted(false);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(backButton);
		
		// Approve Task button
		approveTaskButton = new JButton("");
		approveTaskButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ApproveTask.png"));
		approveTaskButton.setBounds(375, 636, 288, 89);
		approveTaskButton.setBorderPainted(false);
		approveTaskButton.setOpaque(false);
		approveTaskButton.setContentAreaFilled(false);
		approveTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		approveTaskButton.setFocusable(false);
		this.add(approveTaskButton);
		
		// Delete Task button
		deleteTaskButton = new JButton("");
		deleteTaskButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\DeleteTask.png"));
		deleteTaskButton.setBounds(702, 575, 288, 89);
		deleteTaskButton.setBorderPainted(false);
		deleteTaskButton.setOpaque(false);
		deleteTaskButton.setContentAreaFilled(false);
		deleteTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteTaskButton.setFocusable(false);
		this.add(deleteTaskButton);
		
		// Update Task button
		updateTaskButton = new JButton("");
		updateTaskButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\UpdateTask.png"));
		updateTaskButton.setBounds(375, 575, 288, 89);
		updateTaskButton.setBorderPainted(false);
		updateTaskButton.setOpaque(false);
		updateTaskButton.setContentAreaFilled(false);
		updateTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateTaskButton.setFocusable(false);
		this.add(updateTaskButton);
		
		// Request Revision button
		requestRevisionButton = new JButton("");
		requestRevisionButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\RequestRevision.png"));
		requestRevisionButton.setBounds(702, 636, 288, 89);
		requestRevisionButton.setBorderPainted(false);
		requestRevisionButton.setOpaque(false);
		requestRevisionButton.setContentAreaFilled(false);
		requestRevisionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		requestRevisionButton.setFocusable(false);
		this.add(requestRevisionButton);
		
		
		// Label gambar background 
		bg = new JLabel("");
		bg.setToolTipText("");
		bg.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\POLOSANTaskLists.png"));
		bg.setBounds(0, 0, 1440, 1024);
		this.add(bg);
	}

	//GETTERS
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBg() {
		return bg;
	}

	public JButton getMenuButton() {
		return menuButton;
	}

	public JButton getApproveTaskButton() {
		return approveTaskButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public JButton getNotifButton() {
		return notifButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getDeleteTaskButton() {
		return deleteTaskButton;
	}

	public JButton getRequestRevisionButton() {
		return requestRevisionButton;
	}

	public JButton getUpdateTaskButton() {
		return updateTaskButton;
	}

	public JComboBox<Object> getSortBy() {
		return sortBy;
	}

	public JTextField getTxtSearch() {
		return txtSearch;
	}

	public JTable getTaskListTable() {
		return taskListTable;
	}
	
	

}
