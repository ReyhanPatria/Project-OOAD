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
	
	private JLabel	usernameField;
	private JLabel	addressField;
	private JLabel	dobDateChooser;
	private JLabel	phoneNumberField;
	
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
		homeButton.setBounds(1201, 11, 134, 48);
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homeButton.setFocusable(false);
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
		
		// Username text field
		usernameField = new JLabel();
		usernameField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		usernameField.setBounds(593, 232, 566, 50);
		
		// Address text field
		addressField = new JLabel();
		addressField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		addressField.setBounds(593, 304, 566, 50);
		this.add(addressField);
		
		// DOB chooser
		dobDateChooser = new JLabel();
		dobDateChooser.setBounds(593, 377, 566, 45);
		dobDateChooser.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		this.add(dobDateChooser);
		
		// Phone number text field
		phoneNumberField = new JLabel();
		phoneNumberField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
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

	public JLabel getUsernameField() {
		return usernameField;
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
}
