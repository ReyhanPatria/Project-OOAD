package example.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import example.controller.UserController;

public class UserView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel usernameLabel = new JLabel("Username");
	private JTextField usernameTextField = new JTextField(15);
	
	private JLabel passwordLabel = new JLabel("Password");
	private JTextField passwordTextField = new JTextField(15);
	
	private JButton submitButton = new JButton("Login");

	public UserView(int width, int height, UserController userController) {
		this.setSize(width, height);
		
		System.out.println(passwordTextField.getText());
		
		this.add(usernameLabel);
		this.add(usernameTextField);
		
		this.add(passwordLabel);
		this.add(passwordTextField);
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					userController.login(usernameTextField.getText(), passwordTextField.getText());
					
					System.out.println("Login Successful");
				}
				catch(IllegalArgumentException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		
		this.add(submitButton);
	}
}
