package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileSupervisorAndWorker extends JPanel implements ViewPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel bg;
	
	private JLabel	addressField;
	private JLabel	dobDateChooser;
	private JLabel	phoneNumberField;
	
	private JButton homeButton;
	private JButton changePasswordButton;
	private JButton editProfileButton;
	private JButton backButton;
	private JButton notifButton;
	private JButton taskButton;
	
	private JTextField txtSearch;
	private JLabel usernameField;
	
	public ProfileSupervisorAndWorker() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		this.setLayout(null);
		
		// username txt
		usernameField = new JLabel("");
		usernameField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		usernameField.setBounds(603, 230, 556, 48);
		this.add(usernameField);
		
		// search field
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		txtSearch.setText("Search...");
		txtSearch.setBounds(404, 11, 537, 48);
		txtSearch.setColumns(10);
		this.add(txtSearch);
		
		// Button for create a task
		taskButton = new JButton("");
		taskButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\CreateTask.png"));
		taskButton.setBounds(963, 11, 50, 50);
		taskButton.setBorderPainted(false);
		taskButton.setOpaque(false);
		taskButton.setContentAreaFilled(false);
		taskButton.setFocusable(false);
		taskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(taskButton);
		
		// button notif
		notifButton = new JButton("");
		notifButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Notifications.png"));
		notifButton.setBounds(1129, 9, 50, 50);
		notifButton.setBorderPainted(false);
		notifButton.setOpaque(false);
		notifButton.setContentAreaFilled(false);
		notifButton.setFocusable(false);
		notifButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(notifButton);
		
		// Login button
		homeButton = new JButton("");	
		homeButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Home.png"));
		homeButton.setBounds(1201, 11, 134, 48);
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusable(false);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(homeButton);
		
		// Change password button
		changePasswordButton = new JButton("");
		changePasswordButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\ChangePassword.png"));
		changePasswordButton.setBounds(731, 583, 306, 62);
		changePasswordButton.setBorderPainted(false);
		changePasswordButton.setOpaque(false);
		changePasswordButton.setContentAreaFilled(false);
		changePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		changePasswordButton.setFocusable(false);
		this.add(changePasswordButton);
		
		// Edit profile button
		editProfileButton = new JButton("");
		editProfileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\EditProfile.png"));
		editProfileButton.setBounds(339, 583, 306, 62);
		editProfileButton.setBorderPainted(false);
		editProfileButton.setOpaque(false);
		editProfileButton.setContentAreaFilled(false);
		editProfileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editProfileButton.setFocusable(false);
		this.add(editProfileButton);
		
		
		// Address text field
		addressField = new JLabel();
		addressField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		addressField.setBounds(603, 304, 556, 50);
		this.add(addressField);
		
		// DOB chooser
		dobDateChooser = new JLabel();
		dobDateChooser.setBounds(603, 377, 556, 45);
		dobDateChooser.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		this.add(dobDateChooser);
		
		// Phone number text field
		phoneNumberField = new JLabel();
		phoneNumberField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		phoneNumberField.setBounds(603, 444, 556, 50);
		this.add(phoneNumberField);
		
		//Back Button
		backButton = new JButton("Cancel");
		backButton.setBounds(1238, 681, 89, 23);
		this.add(backButton);
		
		// Label gambar background 
		bg = new JLabel("");
		bg.setToolTipText("");
		bg.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\ProfilePage2.png"));
		bg.setBounds(0, 0, 1365, 735);
		this.add(bg);
	}

	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBg() {
		return bg;
	}

	public JLabel getAddressField() {
		return addressField;
	}

	public JLabel getDobDateChooser() {
		return dobDateChooser;
	}

	public JLabel getPhoneNumberField() {
		return phoneNumberField;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public JButton getChangePasswordButton() {
		return changePasswordButton;
	}

	public JButton getEditProfileButton() {
		return editProfileButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getNotifButton() {
		return notifButton;
	}

	public JButton getTaskButton() {
		return taskButton;
	}

	public JTextField getTxtSearch() {
		return txtSearch;
	}

	public JLabel getUsernameField() {
		return usernameField;
	}
}
