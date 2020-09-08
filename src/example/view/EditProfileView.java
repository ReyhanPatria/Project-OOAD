package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProfileView extends JPanel implements ViewPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel bg;
	
	private JTextField	addressField;
	private JTextField	phoneNumberField;
	private JTextField 	usernameField;
	
	private JDateChooser dobDateChooser;
	
	private JButton homeButton;
	private JButton updateButton;
	private JButton profileButton;
	private JButton backButton;

	public EditProfileView() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		//back button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Back.png"));
		backButton.setBounds(59, 82, 134, 48);
		backButton.setBorderPainted(false);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.setFocusable(false);
		add(backButton);
		
		//username field
		usernameField = new JTextField();
		usernameField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		usernameField.setBounds(593, 238, 565, 45);
		add(usernameField);
		
		// Home button
		homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Home.png"));
		homeButton.setBounds(1045, 8, 134, 48);
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homeButton.setFocusable(false);
		this.add(homeButton);
		
		// Change password button
		updateButton = new JButton("");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		updateButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Update.png"));
		updateButton.setBounds(530, 575, 306, 62);
		updateButton.setBorderPainted(false);
		updateButton.setOpaque(false);
		updateButton.setContentAreaFilled(false);
		updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateButton.setFocusable(false);
		this.add(updateButton);
		
		// Edit profile button
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1199, 8, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		profileButton.setFocusable(false);
		this.add(profileButton);
				
		// Address text field
		addressField = new JTextField();
		addressField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		addressField.setBounds(593, 309, 565, 45);
		this.add(addressField);
		
		// DOB chooser
		// DOB chooser
		dobDateChooser = new JDateChooser(new java.util.Date());
		dobDateChooser.getCalendarButton().setFont(new Font("Cambria Math", Font.PLAIN, 20));
		dobDateChooser.setBounds(593, 380, 565, 45);
		this.add(dobDateChooser);
		
		// Phone number text field
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		phoneNumberField.setBounds(593, 451, 565, 45);
		this.add(phoneNumberField);
		
		// Background image
		bg = new JLabel("");
		bg.setBounds(0, 0, 1367, 737);
		bg.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		bg.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\EditProfilePageAdmin2.png"));
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
		return updateButton;
	}

	public JButton getEditProfileButton() {
		return profileButton;
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
