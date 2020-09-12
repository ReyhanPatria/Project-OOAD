package example.view;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuSupervisorView extends JPanel implements ViewPanel {
	// STATIC ATTRIBUTES
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	// NON-STATIC ATTRIBUTES
	private JLabel backgroundImage;
	private JButton viewTaskRequestButton;
	private JButton logOutButton;
	private JButton profileButton;
	private JButton viewAllTaskButton;
	private JButton createTaskButton;
	private JButton notifButton;
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	public MenuSupervisorView() {
		this.setSize(new Dimension(1440,1024));
		this.setLayout(null);
		
		notifButton = new JButton("");
		notifButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Notifications.png"));
		notifButton.setBounds(991, 15, 50, 50);
		notifButton.setBorderPainted(false);
		notifButton.setOpaque(false);
		notifButton.setContentAreaFilled(false);
		notifButton.setFocusable(false);
		notifButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(notifButton);
		
		viewTaskRequestButton = new JButton("");
		viewTaskRequestButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ViewTaskRequest.png"));
		viewTaskRequestButton.setBounds(370, 350, 308, 308);
		viewTaskRequestButton.setBorderPainted(false);
		viewTaskRequestButton.setOpaque(false);
		viewTaskRequestButton.setContentAreaFilled(false);
		viewTaskRequestButton.setFocusable(false);
		viewTaskRequestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(viewTaskRequestButton);
		
		viewAllTaskButton = new JButton("");
		viewAllTaskButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ViewAllTask.png"));
		viewAllTaskButton.setBounds(25, 350, 308, 308);
		viewAllTaskButton.setBorderPainted(false);
		viewAllTaskButton.setOpaque(false);
		viewAllTaskButton.setContentAreaFilled(false);
		viewAllTaskButton.setFocusable(false);
		viewAllTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(viewAllTaskButton);
		
		createTaskButton = new JButton("");
		createTaskButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\CreateTask.png"));
		createTaskButton.setBounds(710, 350, 308, 308);
		createTaskButton.setBorderPainted(false);
		createTaskButton.setOpaque(false);
		createTaskButton.setContentAreaFilled(false);
		createTaskButton.setFocusable(false);
		createTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(createTaskButton);
		
		logOutButton = new JButton("");
		logOutButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Logout.png"));
		logOutButton.setBounds(1050, 350, 308, 308);
		logOutButton.setBorderPainted(false);
		logOutButton.setOpaque(false);
		logOutButton.setContentAreaFilled(false);
		logOutButton.setFocusable(false);
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(logOutButton);				
		
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1070, 15, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setFocusable(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(profileButton);
		
		backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\POLOSANMenuSupervisorWorker.png"));
		backgroundImage.setBounds(0, 0, 1440, 1024);
		this.add(backgroundImage);
	}

	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBackgroundImage() {
		return backgroundImage;
	}

	public JButton getViewTaskRequestButton() {
		return viewTaskRequestButton;
	}

	public JButton getLogOutButton() {
		return logOutButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public JButton getViewAllTaskButton() {
		return viewAllTaskButton;
	}

	public JButton getCreateTaskButton() {
		return createTaskButton;
	}

	public JButton getNotifButton() {
		return notifButton;
	}
}