package example.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import example.controller.MainController;
import example.controller.UserController;

public class RegisterUserPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField telpTextField;
	private JTextField addressTextField;

	public RegisterUserPanel() {
		/*
		 * Set panel's preferred size
		 */
		this.setPreferredSize(MainFrame.PREFERRED_SIZE);
		
		/*
		 * Create GridBagLayout on panel
		 */
		setPreferredSize(new Dimension(800, 450));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 110, 200, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 15, 15, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/*
		 * Panel title label
		 */
		JLabel panelTitleLabel = new JLabel("Register New User");
		GridBagConstraints gbc_panelTitleLabel = new GridBagConstraints();
		gbc_panelTitleLabel.gridwidth = 2;
		gbc_panelTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_panelTitleLabel.gridx = 1;
		gbc_panelTitleLabel.gridy = 1;
		add(panelTitleLabel, gbc_panelTitleLabel);
		
		/*
		 * Username text field and label
		 */
		
		/*
		 * Role combo box and label
		 */
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.WEST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 5;
		add(usernameLabel, gbc_usernameLabel);
		
		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.gridx = 2;
		gbc_usernameTextField.gridy = 5;
		add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		usernameLabel.setLabelFor(usernameTextField);
		JLabel roleLabel = new JLabel("Role");
		GridBagConstraints gbc_roleLabel = new GridBagConstraints();
		gbc_roleLabel.anchor = GridBagConstraints.WEST;
		gbc_roleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_roleLabel.gridx = 1;
		gbc_roleLabel.gridy = 6;
		add(roleLabel, gbc_roleLabel);
		
		JComboBox<Object> roleComboBox = new JComboBox<Object>(UserController.selectableRoleList);
		GridBagConstraints gbc_roleComboBox = new GridBagConstraints();
		gbc_roleComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_roleComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_roleComboBox.gridx = 2;
		gbc_roleComboBox.gridy = 6;
		add(roleComboBox, gbc_roleComboBox);
		
		/*
		 * Address text field and label
		 */
		JLabel addressLabel = new JLabel("Address");
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.anchor = GridBagConstraints.WEST;
		gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressLabel.gridx = 1;
		gbc_addressLabel.gridy = 7;
		add(addressLabel, gbc_addressLabel);
		
		addressTextField = new JTextField();
		GridBagConstraints gbc_addressTextField = new GridBagConstraints();
		gbc_addressTextField.insets = new Insets(0, 0, 5, 5);
		gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTextField.gridx = 2;
		gbc_addressTextField.gridy = 7;
		add(addressTextField, gbc_addressTextField);
		addressTextField.setColumns(10);
		
		/*
		 * DOB date chooser and Label
		 */
		JLabel dobLabel = new JLabel("Date of Birth");
		GridBagConstraints gbc_dobLabel = new GridBagConstraints();
		gbc_dobLabel.anchor = GridBagConstraints.WEST;
		gbc_dobLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dobLabel.gridx = 1;
		gbc_dobLabel.gridy = 8;
		add(dobLabel, gbc_dobLabel);
		
		JDateChooser dobDateChooser = new JDateChooser(new java.util.Date());
		dobLabel.setLabelFor(dobDateChooser);
		GridBagConstraints gbc_dobDateChooser = new GridBagConstraints();
		gbc_dobDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dobDateChooser.fill = GridBagConstraints.BOTH;
		gbc_dobDateChooser.gridx = 2;
		gbc_dobDateChooser.gridy = 8;
		add(dobDateChooser, gbc_dobDateChooser);
		
		/*
		 * Telp label and text field
		 */
		JLabel telpLabel = new JLabel("Telp");
		GridBagConstraints gbc_telpLabel = new GridBagConstraints();
		gbc_telpLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telpLabel.anchor = GridBagConstraints.WEST;
		gbc_telpLabel.gridx = 1;
		gbc_telpLabel.gridy = 9;
		add(telpLabel, gbc_telpLabel);
		
		telpTextField = new JTextField();
		telpLabel.setLabelFor(telpTextField);
		GridBagConstraints gbc_telpTextField = new GridBagConstraints();
		gbc_telpTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telpTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telpTextField.gridx = 2;
		gbc_telpTextField.gridy = 9;
		add(telpTextField, gbc_telpTextField);
		telpTextField.setColumns(10);
		
		/*
		 * Adding Register Button
		 */
		JButton registerButton = new JButton("Register");
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
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.insets = new Insets(0, 0, 5, 5);
		gbc_registerButton.gridx = 2;
		gbc_registerButton.gridy = 11;
		this.add(registerButton, gbc_registerButton);
		
		/*
		 * cancel button
		 */
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.changePanel(new MenuAdminView());
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 5, 0);
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 11;
		this.add(cancelButton, gbc_cancelButton);
	}
}
