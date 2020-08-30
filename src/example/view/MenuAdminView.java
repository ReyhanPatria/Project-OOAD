package example.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuAdminView extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel backgroundImage;
	private JButton registerButton;
	private JButton ViewButton;
	private JButton logOutButton;
	
	public MenuAdminView() {
		
		this.setSize(new Dimension(1440,1024));
		setLayout(null);
		
		registerButton = new JButton("Register User");
		registerButton.setBounds(508, 132, 106, 28);
		this.add(registerButton);
		
		ViewButton = new JButton("View All User");
		ViewButton.setBounds(508, 202, 106, 28);
		this.add(ViewButton);
		
		logOutButton = new JButton("Log Out");
		logOutButton.setBounds(508, 264, 106, 28);
		this.add(logOutButton);				
	}

	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBackgroundImage() {
		return backgroundImage;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getViewButton() {
		return ViewButton;
	}

	public JButton getLogOutButton() {
		return logOutButton;
	}
}