package example.view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AllTaskViewSupervisor extends JPanel implements ViewPanel {
	// STATIC ATTRIBUTES
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	// NON-STATIC ATTRIBUTES
	private JLabel bg;
	
	private JButton menuButton;
	private JButton profileButton;
	private JButton notifButton;
	private JButton backButton;
	private JButton approveTaskButton;
	private JButton deleteTaskButton;
	private JButton requestRevisionButton;
	private JButton updateTaskButton;
	
	private JTextField searchTextField;
	private JButton searchButton;
	
	private JTable taskListTable;
	private JComboBox<Object> sortByComboBox;
	private JComboBox<Object> sortDirectionComboBox;
	private JButton sortButton;
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	public AllTaskViewSupervisor() {		
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		this.setLayout(null);
		

		//All Task Table
		taskListTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(taskListTable);
		tableScrollPane.setBounds(325, 250, 700, 300);
		this.add(tableScrollPane);
		
		// Sort by combo box
		sortByComboBox = new JComboBox<Object>();
		sortByComboBox.setBounds(50, 160, 200, 40);
		sortByComboBox.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		this.add(sortByComboBox);
		
		sortDirectionComboBox = new JComboBox<Object>();
		sortDirectionComboBox.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		sortDirectionComboBox.setBounds(50, 235, 200, 40);
		this.add(sortDirectionComboBox);
		
		// search field
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		searchTextField.setText("Search...");
		searchTextField.setBounds(405, 15, 400, 50);
		searchTextField.setColumns(10);
		searchTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(searchTextField.getText().equals("Search...")) {
					searchTextField.setText("");
				}
			}
		});
		this.add(searchTextField);
		
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
		
		// Search button
		searchButton = new JButton("");
		searchButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Search.png"));
		searchButton.setOpaque(false);
		searchButton.setFocusable(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setBounds(825, 15, 134, 50);
		this.add(searchButton);
		
		// Sort button
		sortButton = new JButton("");
		sortButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Sortby.png"));
		sortButton.setOpaque(false);
		sortButton.setFocusable(false);
		sortButton.setContentAreaFilled(false);
		sortButton.setBorderPainted(false);
		sortButton.setBounds(50, 315, 213, 55);
		this.add(sortButton);
		
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
		backButton.setBounds(1050, 160, 134, 48);
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

	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBg() {
		return bg;
	}

	public JButton getApproveTaskButton() {
		return approveTaskButton;
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

	public JButton getMenuButton() {
		return menuButton;
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

	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JTable getTaskListTable() {
		return taskListTable;
	}

	public JComboBox<Object> getSortByComboBox() {
		return sortByComboBox;
	}

	public JComboBox<Object> getSortDirectionComboBox() {
		return sortDirectionComboBox;
	}

	public JButton getSortButton() {
		return sortButton;
	}
}
