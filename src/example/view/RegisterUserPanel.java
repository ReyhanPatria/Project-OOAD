package example.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import example.controller.FrameController;
import example.controller.UserController;

public class RegisterUserPanel extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField telpTextField;
	private JTextField addressTextField;
	private JLabel bground;
	private JComboBox<Object> roleComboBox;
	private JButton cancelButton;
	
	public RegisterUserPanel() {
		
		// Set panel's preferred size
				this.setPreferredSize(MainFrame.SCREEN_SIZE);
				setLayout(null);
				
				// Text Field untuk isi username
				usernameTextField = new JTextField("");
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
		
				//add role
				roleComboBox = new JComboBox<Object>(UserController.selectableRoleList);
				roleComboBox.setBounds(440, 245, 488, 30);
				roleComboBox.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
				this.add(roleComboBox);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(440, 387, 488, 30);
		addressTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(addressTextField);

		/*
		 * DOB date chooser and Label
		 */
		JDateChooser dobDateChooser = new JDateChooser(new java.util.Date());
		dobDateChooser.setBounds(440, 316, 488, 30);
		dobDateChooser.getCalendarButton().setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		this.add(dobDateChooser);

//		/*
//		 * Telp label and text field
//		 */
		telpTextField = new JTextField();
		telpTextField.setBounds(440, 461, 488, 30);
		telpTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		this.add(telpTextField);
		telpTextField.setColumns(10);
//		
//		/*
//		 * Adding Register Button
//		 */
		JButton registerButton = new JButton("");
		registerButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Register.png"));
		registerButton.setBounds(555, 502, 257, 38);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String 	username 		= 	usernameTextField.getText();
				String 	password 		= 	String.valueOf(passwordField.getPassword());
				String 	confirmPassword = 	String.valueOf(confirmPasswordField.getPassword());
				String 	role 			= 	roleComboBox.getSelectedItem().toString();
				String 	address 		= 	addressTextField.getText();
				Date 	DOB 			= 	new Date(dobDateChooser.getDateEditor().getDate().getTime());
				String 	telp 			= 	telpTextField.getText();
				
				try {
					UserController.registerUser(username, password, confirmPassword, role, address, DOB, telp);
					UserController.login(username, password);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		registerButton.setBorderPainted(false);
		registerButton.setOpaque(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setFocusable(false);
		this.add(registerButton);
		
		//benerin registernya karena password awal itu generate ke DOB
		//generate UUID nya
		//masukkin data nya ke DB
//		
//		/*
//		 * profile button
//		 */
		JButton profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1201, 11, 136, 30);
		profileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameController.changePanel(new ProfileAdminView());
			}
		});		
		
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setFocusable(false);
		this.add(profileButton);

		
//		cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		cancelButton.setBounds(1267, 549, 89, 23);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameController.changePanel(new MenuAdminView());
			}
		});		
		
		this.add(cancelButton);

		
		
		
		// Label gambar background 
		bground = new JLabel("");
		bground.setBounds(0, 0, 1367, 587);
		bground.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		bground.setIcon(new ImageIcon("C:\\Users\\HP\\Documents\\Cawu 3\\Periode 2\\Object Oriented Analysis and Design\\Aslab\\Project-OOAD\\src\\example\\IMAGE\\RegisterUserAdmin2.png"));
		bground.setToolTipText("");
		this.add(bground);
	}
}
