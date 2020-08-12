package example.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import example.controller.UserController;

public class RegisterUserPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField telpTextField;

	public RegisterUserPanel() {
		/*
		 * Initialize GridBagLayout on panel
		 */
		setPreferredSize(new Dimension(800, 450));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 110, 200, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/*
		 * Adding Panel Title Label
		 */
		JLabel panelTitleLabel = new JLabel("Register New User");
		GridBagConstraints gbc_panelTitleLabel = new GridBagConstraints();
		gbc_panelTitleLabel.gridwidth = 2;
		gbc_panelTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_panelTitleLabel.gridx = 1;
		gbc_panelTitleLabel.gridy = 1;
		add(panelTitleLabel, gbc_panelTitleLabel);
		
		/*
		 * Adding Username Text Field and Label
		 */
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.WEST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 3;
		add(usernameLabel, gbc_usernameLabel);
		usernameLabel.setLabelFor(usernameTextField);
		
		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.gridx = 2;
		gbc_usernameTextField.gridy = 3;
		add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		
		/*
		 * Adding Password Field and Label
		 */
		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 5;
		add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 5;
		add(passwordField, gbc_passwordField);
		
		/*
		 * Adding Confirm Password Field and Label
		 */
		JLabel confirmPasswordLabel = new JLabel("Confim Password");
		GridBagConstraints gbc_confirmPasswordLabel = new GridBagConstraints();
		gbc_confirmPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPasswordLabel.anchor = GridBagConstraints.WEST;
		gbc_confirmPasswordLabel.gridx = 1;
		gbc_confirmPasswordLabel.gridy = 7;
		add(confirmPasswordLabel, gbc_confirmPasswordLabel);
		
		confirmPasswordField = new JPasswordField();
		GridBagConstraints gbc_confirmPasswordField = new GridBagConstraints();
		gbc_confirmPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmPasswordField.gridx = 2;
		gbc_confirmPasswordField.gridy = 7;
		add(confirmPasswordField, gbc_confirmPasswordField);
		
		/*
		 * Adding Role Combo Box and Label
		 */
		JLabel roleLabel = new JLabel("Role");
		GridBagConstraints gbc_roleLabel = new GridBagConstraints();
		gbc_roleLabel.anchor = GridBagConstraints.WEST;
		gbc_roleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_roleLabel.gridx = 1;
		gbc_roleLabel.gridy = 9;
		add(roleLabel, gbc_roleLabel);
		
		JComboBox<Object> roleComboBox = new JComboBox<Object>(UserController.selectableRoleList);
		GridBagConstraints gbc_roleComboBox = new GridBagConstraints();
		gbc_roleComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_roleComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_roleComboBox.gridx = 2;
		gbc_roleComboBox.gridy = 9;
		add(roleComboBox, gbc_roleComboBox);
		
		/*
		 * Adding Address Text Area and Label
		 */
		JLabel addressLabel = new JLabel("Address");
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.anchor = GridBagConstraints.WEST;
		gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressLabel.gridx = 1;
		gbc_addressLabel.gridy = 11;
		add(addressLabel, gbc_addressLabel);
		
		JTextArea addressTextArea = new JTextArea();
		GridBagConstraints gbc_addressTextArea = new GridBagConstraints();
		gbc_addressTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_addressTextArea.fill = GridBagConstraints.BOTH;
		gbc_addressTextArea.gridx = 2;
		gbc_addressTextArea.gridy = 11;
		add(addressTextArea, gbc_addressTextArea);
		
		/*
		 * Adding DOB Date Chooser and Label
		 */
		JLabel dobLabel = new JLabel("Date of Birth");
		GridBagConstraints gbc_dobLabel = new GridBagConstraints();
		gbc_dobLabel.anchor = GridBagConstraints.WEST;
		gbc_dobLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dobLabel.gridx = 1;
		gbc_dobLabel.gridy = 13;
		add(dobLabel, gbc_dobLabel);
		
		JDateChooser dobDateChooser = new JDateChooser();
		dobLabel.setLabelFor(dobDateChooser);
		GridBagConstraints gbc_dobDateChooser = new GridBagConstraints();
		gbc_dobDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dobDateChooser.fill = GridBagConstraints.BOTH;
		gbc_dobDateChooser.gridx = 2;
		gbc_dobDateChooser.gridy = 13;
		add(dobDateChooser, gbc_dobDateChooser);
		
		/*
		 * Adding Telp Text Field and Label
		 */
		Label telpLabel = new Label("Telp");
		GridBagConstraints gbc_telpLabel = new GridBagConstraints();
		gbc_telpLabel.anchor = GridBagConstraints.WEST;
		gbc_telpLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telpLabel.gridx = 1;
		gbc_telpLabel.gridy = 15;
		add(telpLabel, gbc_telpLabel);
		
		telpTextField = new JTextField();
		GridBagConstraints gbc_telpTextField = new GridBagConstraints();
		gbc_telpTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telpTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telpTextField.gridx = 2;
		gbc_telpTextField.gridy = 15;
		add(telpTextField, gbc_telpTextField);
		telpTextField.setColumns(10);
		
		/*
		 * Adding Register Button
		 */
		JButton registerButton = new JButton("Register");
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.gridwidth = 2;
		gbc_registerButton.insets = new Insets(0, 0, 5, 5);
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 17;
		add(registerButton, gbc_registerButton);
	}
}
