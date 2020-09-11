package example.view;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TaskRequestView extends JPanel implements ViewPanel {
	// STATIC ATTRIBUTES
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	// NON-STATIC ATTRIBUTES
	private JLabel bg;
	
	private JButton menuButton;
	private JButton acceptButton;
	private JButton profileButton;
	private JButton notifButton;
	private JButton backButton;
	private JButton rejectButton;
	
	private JTable taskRequestTable;
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public TaskRequestView() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		this.setLayout(null);
		
		//All Task Request Table
		taskRequestTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(taskRequestTable);
		tableScrollPane.setBounds(50, 250, 850, 450);
		this.add(tableScrollPane);
		
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
		
		// Accept Task Request button
		acceptButton = new JButton("");
		acceptButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Accept.png"));
		acceptButton.setBounds(970, 250, 158, 58);
		acceptButton.setBorderPainted(false);
		acceptButton.setOpaque(false);
		acceptButton.setContentAreaFilled(false);
		acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		acceptButton.setFocusable(false);
		this.add(acceptButton);
		
		// Reject Task Request button
		rejectButton = new JButton("");
		rejectButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Reject.png"));
		rejectButton.setBounds(1155, 250, 158, 58);
		rejectButton.setBorderPainted(false);
		rejectButton.setOpaque(false);
		rejectButton.setContentAreaFilled(false);
		rejectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rejectButton.setFocusable(false);
		this.add(rejectButton);
		
		
		// Label gambar background 
		bg = new JLabel("");
		bg.setToolTipText("");
		bg.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\POLOSANTaskRequest.png"));
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

	public JButton getMenuButton() {
		return menuButton;
	}

	public JButton getAcceptButton() {
		return acceptButton;
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

	public JButton getRejectButton() {
		return rejectButton;
	}

	public JTable getTaskRequestTable() {
		return taskRequestTable;
	}
}
