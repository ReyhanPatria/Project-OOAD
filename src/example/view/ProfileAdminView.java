package example.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import example.controller.FrameController;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ProfileAdminView extends JPanel implements ViewPanel {
	private JLabel bg;
	private JButton homeButton;
	private JButton changePasswordButton;
	private JButton editProfileButton;
	private JTextField usernameField;
	private JTextField addressField;
	private JTextField dobField;
	private JTextField phoneNumberField;

	public ProfileAdminView() {
		// TODO Auto-generated constructor stub
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Home.png"));
		homeButton.setBounds(1202, 11, 132, 39);
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameController.changePanel(new MenuAdminView());
			}
		});
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusable(false);
		this.add(homeButton);
		
		changePasswordButton = new JButton("");
		changePasswordButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ChangePassword.png"));
		changePasswordButton.setBounds(733, 583, 285, 45);
		changePasswordButton.setBorderPainted(false);
		changePasswordButton.setOpaque(false);
		changePasswordButton.setContentAreaFilled(false);
		changePasswordButton.setFocusable(false);
		this.add(changePasswordButton);
		
		editProfileButton = new JButton("");
		editProfileButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\EditProfile.png"));
		editProfileButton.setBounds(353, 583, 285, 45);
		editProfileButton.setBorderPainted(false);
		editProfileButton.setOpaque(false);
		editProfileButton.setContentAreaFilled(false);
		editProfileButton.setFocusable(false);
		this.add(editProfileButton);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		usernameField.setBounds(593, 232, 566, 50);
		this.add(usernameField);
		usernameField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		addressField.setColumns(10);
		addressField.setBounds(593, 304, 566, 50);
		this.add(addressField);
		
		dobField = new JTextField();
		dobField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		dobField.setColumns(10);
		dobField.setBounds(593, 372, 566, 50);
		this.add(dobField);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		phoneNumberField.setColumns(10);
		phoneNumberField.setBounds(593, 444, 566, 50);
		this.add(phoneNumberField);
		
		bg = new JLabel("");
		bg.setBounds(0, 0, 1367, 737);
		bg.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		bg.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ProfilePageAdmin2.png"));
		bg.setToolTipText("");
		this.add(bg);
		
	}
}
