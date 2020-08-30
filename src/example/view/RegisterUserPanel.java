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
	private JButton cancelButton;
	private JButton profileButton;
	
	public RegisterUserPanel() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		// Text Field untuk isi username
		usernameTextField = new JTextField("Username");
		usernameTextField.setBounds(440, 172, 488, 30);
		usernameTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		usernameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(usernameTextField);
		usernameTextField.setColumns(10);
		usernameTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				usernameTextField.setText("");
			}
		});

		// Choose role combo box
		roleComboBox = new JComboBox<Object>(UserController.selectableRoleList);
		roleComboBox.setBounds(440, 245, 488, 30);
		roleComboBox.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(roleComboBox);
		
		
		// Address text field
		addressTextField = new JTextField();
		addressTextField.setBounds(440, 387, 488, 30);
		addressTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(addressTextField);

		// DOB chooser
		dobDateChooser = new JDateChooser(new java.util.Date());
		dobDateChooser.setBounds(440, 316, 488, 30);
		dobDateChooser.getCalendarButton().setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		this.add(dobDateChooser);

		// Telp text field
		telpTextField = new JTextField();
		telpTextField.setBounds(440, 461, 488, 30);
		telpTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		this.add(telpTextField);
		telpTextField.setColumns(10);
		
		// Register button
		registerButton = new JButton("");
		registerButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Register.png"));
		registerButton.setBounds(555, 502, 257, 38);
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
		profileButton.setBounds(1201, 11, 136, 30);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		profileButton.setFocusable(false);
		this.add(profileButton);

		// Cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		cancelButton.setBounds(1267, 549, 89, 23);	
		this.add(cancelButton);
		
		// Label gambar background 
		bground = new JLabel("");
		bground.setBounds(0, 0, 1367, 587);
		bground.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		bground.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\RegisterUserAdmin2.png"));
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
		return cancelButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}
}
