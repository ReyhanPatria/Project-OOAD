package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class ProfileAdminView extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel bg;
	
	private JTextField		usernameField;
	private JTextField		addressField;
	private JDateChooser	dobDateChooser;
	private JTextField		phoneNumberField;
	
	private JButton homeButton;
	private JButton changePasswordButton;
	private JButton editProfileButton;

	public ProfileAdminView() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		// Home button
		homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Home.png"));
		homeButton.setBounds(1202, 11, 132, 39);
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homeButton.setFocusable(false);
		this.add(homeButton);
		
		// Change password button
		changePasswordButton = new JButton("");
		changePasswordButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\ChangePassword.png"));
		changePasswordButton.setBounds(733, 583, 285, 45);
		changePasswordButton.setBorderPainted(false);
		changePasswordButton.setOpaque(false);
		changePasswordButton.setContentAreaFilled(false);
		changePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		changePasswordButton.setFocusable(false);
		this.add(changePasswordButton);
		
		// Edit profile button
		editProfileButton = new JButton("");
		editProfileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\EditProfile.png"));
		editProfileButton.setBounds(353, 583, 285, 45);
		editProfileButton.setBorderPainted(false);
		editProfileButton.setOpaque(false);
		editProfileButton.setContentAreaFilled(false);
		editProfileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editProfileButton.setFocusable(false);
		this.add(editProfileButton);
		
		// Username text field
		usernameField = new JTextField();
		usernameField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		usernameField.setBounds(593, 232, 566, 50);
		this.add(usernameField);
		usernameField.setColumns(10);
		
		// Address text field
		addressField = new JTextField();
		addressField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		addressField.setColumns(10);
		addressField.setBounds(593, 304, 566, 50);
		this.add(addressField);
		
		// DOB chooser
		dobDateChooser = new JDateChooser(new java.util.Date());
		dobDateChooser.setBounds(440, 316, 488, 30);
		dobDateChooser.getCalendarButton().setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		this.add(dobDateChooser);
		
		// Phone number text field
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		phoneNumberField.setColumns(10);
		phoneNumberField.setBounds(593, 444, 566, 50);
		this.add(phoneNumberField);
		
		// Background image
		bg = new JLabel("");
		bg.setBounds(0, 0, 1367, 737);
		bg.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		bg.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\ProfilePageAdmin2.png"));
		bg.setToolTipText("");
		this.add(bg);	
	}
	
	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBg() {
		return bg;
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

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public JDateChooser getDobDateChooser() {
		return dobDateChooser;
	}

	public JTextField getPhoneNumberField() {
		return phoneNumberField;
	}
}
