import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.sql.Date;
import com.toedter.calendar.JDateChooser;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Name;
	private JTextField textField_Number;
	private JTextField textField_Email;
	private JTextField textField_Username;
	private JPasswordField passwordField;
	private JLabel close;
	private JLabel lblRegisterGIF;
	private JLabel lblBackground;
	private JLabel lblName;
	private JLabel lblTelephoneNumber;
	private JLabel lblEmail;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JButton btnOK;
	private JButton btnCancel;
	private JLabel takenUsername;
	private JLabel takenEmail;
	private JLabel takenNumber;
	private JLabel invalidPassword;
	private JLabel invalidEmail;
	private JLabel invalidUsername;
	private JLabel invalidPhone;
	private JLabel invalidName;
	private JLabel invalidRegistration;
	private JPanel panel;
	
	public Register() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Jadi fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1390, 744);
		
		// Panel Utama | Panel Background
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		FileHandler.readFile();
//		Vector<Member> listMember = FileHandler.getListMember(); // Data member di Users.txt
		
		// Panel Background (Atasnya Panel Utama)
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// Label untuk invalid registrasi
		invalidRegistration = new JLabel("Invalid data!");
		invalidRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		invalidRegistration.setForeground(Color.RED);
		invalidRegistration.setBounds(708, 563, 121, 14);
		panel.add(invalidRegistration);
		invalidRegistration.setVisible(false);
		
		// Label untuk invalid name
		invalidName = new JLabel("Name must be at least 4 characters!");
		invalidName.setForeground(Color.RED);
		invalidName.setBounds(843, 101, 310, 14);
		panel.add(invalidName);
		invalidName.setVisible(false);
		
		// Label untuk invalid telephone number
		invalidPhone = new JLabel("Number must be between 11-13 characters!");
		invalidPhone.setForeground(Color.RED);
		invalidPhone.setBounds(187, 113, 310, 14);
		panel.add(invalidPhone);
		invalidPhone.setVisible(false);
		
		// Label untuk invalid username
		invalidUsername = new JLabel("Username must be at least 8 characters!");
		invalidUsername.setForeground(Color.RED);
		invalidUsername.setBounds(187, 285, 310, 14);
		panel.add(invalidUsername);
		invalidUsername.setVisible(false);
//		
//		// Label untuk invalid email
//		invalidEmail = new JLabel("Email at least 10 characters & ends with @gmail.com!");
//		invalidEmail.setForeground(Color.RED);
//		invalidEmail.setBounds(187, 303, 310, 14);
//		panel.add(invalidEmail);
//		invalidEmail.setVisible(false);
		
		// Label untuk invalid password
		invalidPassword = new JLabel("Password must be at least 6 characters!");
		invalidPassword.setForeground(Color.RED);
		invalidPassword.setBounds(187, 478, 310, 14);
		panel.add(invalidPassword);
		invalidPassword.setVisible(false);
		
		// Label untuk number has already taken
		takenNumber = new JLabel("Number has already taken!");
		takenNumber.setHorizontalAlignment(SwingConstants.LEFT);
		takenNumber.setForeground(Color.RED);
		takenNumber.setBounds(843, 101, 203, 14);
		panel.add(takenNumber);
		takenNumber.setVisible(false);
		
//		// Label untuk email has already taken
//		takenEmail = new JLabel("Email has already taken!");
//		takenEmail.setHorizontalAlignment(SwingConstants.LEFT);
//		takenEmail.setForeground(Color.RED);
//		takenEmail.setBounds(187, 303, 203, 14);
//		panel.add(takenEmail);
//		takenEmail.setVisible(false);
		
		// Label untuk username has already taken
		takenUsername = new JLabel("Username has already taken!");
		takenUsername.setForeground(Color.RED);
		takenUsername.setHorizontalAlignment(SwingConstants.LEFT);
		takenUsername.setBounds(187, 285, 190, 14);
		panel.add(takenUsername);
		takenUsername.setVisible(false);
		
		// Button Cancel GIF (Direct ke Menu Awal)
		btnCancel = new JButton("");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainmenu menuAwal = new mainmenu();
				menuAwal.setUndecorated(true);
				menuAwal.setVisible(true);
				Register.this.dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(Register.class.getResource("/IMAGE/cancel.jpg")));
		btnCancel.setBounds(1085, 683, 237, 88);
		panel.add(btnCancel);
		
		// Label Register GIF
//		lblRegisterGIF = new JLabel("");
//		lblRegisterGIF.setIcon(new ImageIcon(Register.class.getResource("/IMAGE/register.gif")));
//		lblRegisterGIF.setBounds(564, 276, 631, 205);
//		panel.add(lblRegisterGIF);
		
		// Logo silang untuk quit
		close = new JLabel("X");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		close.setForeground(Color.BLACK);
		close.setFont(new Font("Nova Square", Font.BOLD, 24));
		close.setBounds(1323, 0, 27, 37);
		panel.add(close);

		// Label untuk Password
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(255, 255, 153));
		lblPassword.setFont(new Font("Gabriola", Font.PLAIN, 18));
		lblPassword.setBounds(187, 422, 116, 14);
		panel.add(lblPassword);
		
		
//		Confirm Password field and label 
		JLabel confirmPasswordLabel = new JLabel("Confim Password");
		GridBagConstraints gbc_confirmPasswordLabel = new GridBagConstraints();
		gbc_confirmPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPasswordLabel.anchor = GridBagConstraints.WEST;
		gbc_confirmPasswordLabel.gridx = 1;
		gbc_confirmPasswordLabel.gridy = 5;
		getContentPane().add(confirmPasswordLabel, gbc_confirmPasswordLabel);
		
		JPasswordField confirmPasswordField = new JPasswordField();
		GridBagConstraints gbc_confirmPasswordField = new GridBagConstraints();
		gbc_confirmPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmPasswordField.gridx = 2;
		gbc_confirmPasswordField.gridy = 5;
		getContentPane().add(confirmPasswordField, gbc_confirmPasswordField);
		
//		Role combo box and label
		JLabel roleLabel = new JLabel("Role");
		GridBagConstraints gbc_roleLabel = new GridBagConstraints();
		gbc_roleLabel.anchor = GridBagConstraints.WEST;
		gbc_roleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_roleLabel.gridx = 1;
		gbc_roleLabel.gridy = 6;
		getContentPane().add(roleLabel, gbc_roleLabel);
		
//		JComboBox<Object> roleComboBox = new JComboBox<Object>(UserController.selectableRoleList);
		GridBagConstraints gbc_roleComboBox = new GridBagConstraints();
		gbc_roleComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_roleComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_roleComboBox.gridx = 2;
		gbc_roleComboBox.gridy = 6;
//		add(roleComboBox, gbc_roleComboBox);
		
//		Address text field and label
		JLabel addressLabel = new JLabel("Address");
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.anchor = GridBagConstraints.WEST;
		gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressLabel.gridx = 1;
		gbc_addressLabel.gridy = 7;
		getContentPane().add(addressLabel, gbc_addressLabel);
		
		JTextField addressTextField = new JTextField();
		GridBagConstraints gbc_addressTextField = new GridBagConstraints();
		gbc_addressTextField.insets = new Insets(0, 0, 5, 5);
		gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTextField.gridx = 2;
		gbc_addressTextField.gridy = 7;
		getContentPane().add(addressTextField, gbc_addressTextField);
		addressTextField.setColumns(10);
		
//		DOB date chooser and Label
		JLabel dobLabel = new JLabel("Date of Birth");
		GridBagConstraints gbc_dobLabel = new GridBagConstraints();
		gbc_dobLabel.anchor = GridBagConstraints.WEST;
		gbc_dobLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dobLabel.gridx = 1;
		gbc_dobLabel.gridy = 8;
		getContentPane().add(dobLabel, gbc_dobLabel);
		
		JDateChooser dobDateChooser = new JDateChooser(new java.util.Date());
		dobLabel.setLabelFor(dobDateChooser);
		GridBagConstraints gbc_dobDateChooser = new GridBagConstraints();
		gbc_dobDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dobDateChooser.fill = GridBagConstraints.BOTH;
		gbc_dobDateChooser.gridx = 2;
		gbc_dobDateChooser.gridy = 8;
		getContentPane().add(dobDateChooser, gbc_dobDateChooser);
		
		// Label untuk Username
		lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(new Color(255, 255, 153));
		lblUsername.setFont(new Font("Gabriola", Font.PLAIN, 18));
		lblUsername.setBounds(187, 233, 116, 14);
		panel.add(lblUsername);
		
		
//		// Label untuk email
//		lblEmail = new JLabel("E-MAIL");
//		lblEmail.setForeground(new Color(255, 255, 153));
//		lblEmail.setFont(new Font("Gabriola", Font.PLAIN, 18));
//		lblEmail.setBounds(187, 251, 116, 14);
//		panel.add(lblEmail);
		
		// Label untuk nomor telpon
		lblTelephoneNumber = new JLabel("TELEPHONE NUMBER");
		lblTelephoneNumber.setForeground(new Color(255, 255, 153));
		lblTelephoneNumber.setFont(new Font("Gabriola", Font.PLAIN, 18));
		lblTelephoneNumber.setBounds(843, 45, 203, 14);
		panel.add(lblTelephoneNumber);
		
		// Label untuk nama user
		lblName = new JLabel("NAME");
		lblName.setForeground(new Color(255, 255, 153));
		lblName.setFont(new Font("Gabriola", Font.PLAIN, 18));
		lblName.setBounds(187, 58, 116, 14);
		panel.add(lblName);
		
		// Text Field untuk password
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tempPass = passwordField.getText();
				Boolean isPasswordValid = false;
				if(tempPass.length() < 6) { // Validasi panjang password
					invalidPassword.setVisible(true);
					isPasswordValid = false;
				}
				else {
					invalidPassword.setVisible(false);
					isPasswordValid = true;
				}
			}
		});
		passwordField.setColumns(10);
		passwordField.setBackground(new Color(153, 204, 204));
		passwordField.setBounds(187, 447, 310, 20);
		panel.add(passwordField);
		
		// Text Field untuk Username
		textField_Username = new JTextField();
		textField_Username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tempUsername = textField_Username.getText(); // Username
				Boolean isUsernameExist = false; // Validasi username
				Boolean isUsernameValid = false;
				if(tempUsername.length() < 8) {
					takenUsername.setVisible(false);
					invalidUsername.setVisible(true);
					isUsernameValid = false;
				}
				else {
					invalidUsername.setVisible(false);
//					for(Member m : listMember) {
//						if(m.authUser(tempUsername)) {
//							isUsernameExist = true; // Berarti sudah ada -> TIDAK BOLEH REGISTER
//							takenUsername.setVisible(true);
//							isUsernameValid = false;
//							break;
//						}
//					}
					if(!isUsernameExist) {
						takenUsername.setVisible(false);
						isUsernameValid = true;
					}
				}
			}
		});
		textField_Username.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		textField_Username.setColumns(10);
		textField_Username.setBackground(new Color(153, 204, 204));
		textField_Username.setBounds(187, 254, 310, 20);
		panel.add(textField_Username);
		
		// Text Field untuk Email
//		textField_Email = new JTextField();
//		textField_Email.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				String tempEmail = textField_Email.getText(); // Email
//				Boolean isEmailExist = false; // Validasi email
//				Boolean isEmailValid = false;
//				if(!tempEmail.endsWith("@gmail.com") || tempEmail.length() <= 10) {
//					takenEmail.setVisible(false);
//					invalidEmail.setVisible(true);
//					isEmailValid = false;
//				}
//				else {
//					invalidEmail.setVisible(false);
//					for(Member m : listMember) {
//						if(m.authEmail(tempEmail)) {
//							isEmailExist = true; // Berarti sudah ada -> TIDAK BOLEH REGISTER
//							takenEmail.setVisible(true);
//							isEmailValid = false;
//							break;
//						}
//					}
//					if(!isEmailExist) {
//						takenEmail.setVisible(false);
//						isEmailValid = true;
//					}
//				}
//			}
//		});
//		textField_Email.setFont(new Font("Segoe Script", Font.PLAIN, 12));
//		textField_Email.setColumns(10);
//		textField_Email.setBackground(new Color(153, 204, 204));
//		textField_Email.setBounds(187, 276, 310, 20);
//		panel.add(textField_Email);
		
		// Text Field untuk Nomor Telpon
		textField_Number = new JTextField();
		textField_Number.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Validasi Nomor Telpon
				String tempTelpNumber = textField_Number.getText(); // No. Telp
				Boolean isNumberExist = false; // Cek nomor telpon
				Boolean isNumberValid = false;
				if(tempTelpNumber.length() < 11 || tempTelpNumber.length() > 13) {
					takenNumber.setVisible(false);
					invalidPhone.setVisible(true);
					isNumberValid = false;
				}
				else {
					invalidPhone.setVisible(false);
//					for(Member m : listMember) {
//						if(m.authNumber(tempTelpNumber)) {
//							isNumberExist = true; // Berarti sudah ada -> TIDAK BOLEH REGISTER
//							takenNumber.setVisible(true);
//							isNumberValid = false;
//							break;
//						}
//					}
					if(!isNumberExist) {
						takenNumber.setVisible(false);
						isNumberValid = true;
					}
				}
			}
		});
		textField_Number.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_Number.setBackground(new Color(153, 204, 204));
		textField_Number.setColumns(10);
		textField_Number.setBounds(843, 70, 310, 20);
		panel.add(textField_Number);
		
		// Text Field untuk Nama User
		textField_Name = new JTextField();
		textField_Name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tempName = textField_Name.getText(); 
				Boolean isNameValid = false;
				if(tempName.length() < 4) { // Validasi panjang nama
					invalidName.setVisible(true);
					isNameValid = false;
				}
				else {
					invalidName.setVisible(false);
					isNameValid = true;
				}
			}
		});
		textField_Name.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		textField_Name.setBackground(new Color(153, 204, 204));
		textField_Name.setBounds(187, 83, 310, 20);
		panel.add(textField_Name);
		textField_Name.setColumns(10);
		
		// Label untuk background register
		lblBackground = new JLabel("");
		lblBackground.setVerticalAlignment(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(Register.class.getResource("/IMAGE/Register.png")));
		lblBackground.setBounds(10, 0, 1340, 755);
		panel.add(lblBackground);
		
		// Button OK GIF (Validasi kemudian direct ke Menu Utama)
		btnOK = new JButton("");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Validasi 
				if(textField_Name.getText().length() == 0 && textField_Number.getText().length() == 0 && textField_Email.getText().length() == 0 && textField_Username.getText().length() == 0 && passwordField.getText().length() == 0) {
					invalidRegistration.setVisible(true); // Invalid registration
				}
				else if(!invalidName.isVisible() && !invalidPhone.isVisible() && !takenNumber.isVisible() && 
						!invalidEmail.isVisible() && !takenEmail.isVisible() && !invalidUsername.isVisible() && 
						!takenUsername.isVisible() && !invalidPassword.isVisible() && 
						textField_Name.getText().length() != 0 && textField_Number.getText().length() != 0 && 
						textField_Email.getText().length() != 0 && textField_Username.getText().length() != 0 &&
						passwordField.getText().length() != 0) {
					invalidRegistration.setVisible(false);
					
					// Simpan data dalam Users.txt
					Integer reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Registration", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION) {
//						FileHandler.writeNewUsers(textField_Name.getText(), textField_Number.getText(), textField_Email.getText(), textField_Username.getText(), passwordField.getText());
						JOptionPane.showMessageDialog(null, "Your data is saved!\nPlease login with your data in login screen!");
						
						mainmenu frame = new mainmenu();
						frame.setUndecorated(true);
						frame.setVisible(true);
						Register.this.dispose();
					}
				}
				else {
					invalidRegistration.setVisible(true); // Invalid registration
				}
			}
		});
		btnOK.setIcon(new ImageIcon(Register.class.getResource("/IMAGE/reg.jpg")));
		btnOK.setBounds(648, 464, 237, 88);
		panel.add(btnOK);
		
		
	}
}
