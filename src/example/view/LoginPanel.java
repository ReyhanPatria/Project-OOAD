package example.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import example.controller.UserController;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField usernameTextField;
	private JTextField passwordTextField;

	public LoginPanel() {
		/*
		 * Initialize GridBagLayout on panel
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 118, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 30, 0, 0, 30, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/*
		 * Adding Username TextField and Label
		 */
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.WEST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 1;
		add(usernameLabel, gbc_usernameLabel);
		
		usernameTextField = new JTextField();
		usernameLabel.setLabelFor(usernameTextField);
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 2;
		add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		
		/*
		 * Adding Password TextField and Label
		 */
		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 4;
		add(passwordLabel, gbc_passwordLabel);
		
		passwordTextField = new JTextField();
		passwordLabel.setLabelFor(passwordTextField);
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTextField.gridx = 1;
		gbc_passwordTextField.gridy = 5;
		add(passwordTextField, gbc_passwordTextField);
		passwordTextField.setColumns(10);
		
		/*
		 * Adding Login Button
		 */
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();
				
				try {
					UserController.login(username, password);
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 7;
		add(loginButton, gbc_loginButton);
	}
}
