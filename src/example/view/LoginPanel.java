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

import example.controller.MainController;
import example.controller.UserController;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;

	public LoginPanel() {
		/*
		 * Set panel's preferred size
		 */
		this.setPreferredSize(MainFrame.PREFERRED_SIZE);
		
		/*
		 * Initialize GridBagLayout on panel
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 118, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 30, 0, 0, 30, 0, 0, 30, 0, 30, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/*
		 * Panel title label
		 */
		JLabel panelTitleLabel = new JLabel("Login");
		GridBagConstraints gbc_panelTitleLabel = new GridBagConstraints();
		gbc_panelTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_panelTitleLabel.gridx = 1;
		gbc_panelTitleLabel.gridy = 1;
		add(panelTitleLabel, gbc_panelTitleLabel);
		
		/*
		 * Adding Username TextField and Label
		 */
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.WEST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 3;
		add(usernameLabel, gbc_usernameLabel);
		
		usernameTextField = new JTextField();
		usernameLabel.setLabelFor(usernameTextField);
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 4;
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
		gbc_passwordLabel.gridy = 6;
		add(passwordLabel, gbc_passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordLabel.setLabelFor(passwordTextField);
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTextField.gridx = 1;
		gbc_passwordTextField.gridy = 7;
		add(passwordTextField, gbc_passwordTextField);
		passwordTextField.setColumns(10);
		
		/*
		 * Adding Login Button
		 */
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				String password = String.valueOf(passwordTextField.getPassword());
				
				try {
					UserController.login(username, password);
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 9;
		add(loginButton, gbc_loginButton);
		
		/*
		 * Added signup label and button
		 */
		JLabel signupLabel = new JLabel("Don't have an account?");
		GridBagConstraints gbc_signupLabel = new GridBagConstraints();
		gbc_signupLabel.insets = new Insets(0, 0, 5, 5);
		gbc_signupLabel.gridx = 1;
		gbc_signupLabel.gridy = 11;
		add(signupLabel, gbc_signupLabel);
		
		JButton signupButton = new JButton("Sign up");
		signupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().changePanel(new RegisterUserPanel());
			}
		});
		GridBagConstraints gbc_signupButton = new GridBagConstraints();
		gbc_signupButton.insets = new Insets(0, 0, 5, 5);
		gbc_signupButton.gridx = 1;
		gbc_signupButton.gridy = 12;
		add(signupButton, gbc_signupButton);
	}
}
