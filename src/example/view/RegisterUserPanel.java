package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import example.controller.UserController;

public class RegisterUserPanel extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel bground;
	
	private JTextField			usernameTextField;
	private JTextField			telpTextField;
	private JTextField			addressTextField;
	private JComboBox<Object>	roleComboBox;
	private JDateChooser		dobDateChooser;
	
	private JButton registerButton;
	private JButton homeButton;
	private JButton profileButton;
	
	public RegisterUserPanel() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		// Text Field untuk isi username
		usernameTextField = new JTextField("");
		usernameTextField.setBounds(440, 206, 488, 43);
		usernameTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		usernameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(usernameTextField);
		usernameTextField.setColumns(10);


		// Choose role combo box
		roleComboBox = new JComboBox<Object>(UserController.selectableRoleList);
		roleComboBox.setBounds(440, 298, 488, 43);
		roleComboBox.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(roleComboBox);
		
		
		// Address text field
		addressTextField = new JTextField();
		addressTextField.setBounds(440, 476, 488, 36);
		addressTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(addressTextField);

		// DOB chooser
		dobDateChooser = new JDateChooser(new java.util.Date());
		dobDateChooser.setBounds(440, 386, 488, 43);
		dobDateChooser.getCalendarButton().setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		this.add(dobDateChooser);

		// Telp text field
		telpTextField = new JTextField();
		telpTextField.setBounds(440, 568, 488, 36);
		telpTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(telpTextField);
		telpTextField.setColumns(10);
		
		// Register button
		registerButton = new JButton("");
		registerButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Register.png"));
		registerButton.setBounds(552, 615, 276, 62);
		registerButton.setBorderPainted(false);
		registerButton.setOpaque(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.setFocusable(false);
		this.add(registerButton);
		
		//benerin registernya karena password awal itu generate ke DOB
		//generate UUID nya
		//masukkin data nya ke DB
		
		// Back to profile button
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1200, 0, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		profileButton.setFocusable(false);
		this.add(profileButton);

		// Home button
		homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Home.png"));
		homeButton.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		homeButton.setBounds(1027, 0, 134, 48);	
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(homeButton);
		
		// Label gambar background 
		bground = new JLabel("");
		bground.setBounds(0, -20, 1366, 756);
		bground.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		bground.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\RegisterUserAdmin2.png"));
		bground.setToolTipText("");
		this.add(bground);
	}

	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBground() {
		return bground;
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JTextField getTelpTextField() {
		return telpTextField;
	}

	public JTextField getAddressTextField() {
		return addressTextField;
	}

	public JComboBox<Object> getRoleComboBox() {
		return roleComboBox;
	}

	public JDateChooser getDOBDateChooser() {
		return dobDateChooser;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getCancelButton() {
		return homeButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}
}
