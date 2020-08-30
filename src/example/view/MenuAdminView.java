package example.view;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuAdminView extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel backgroundImage;
	
	private JButton registerButton;
	private JButton viewButton;
	private JButton logOutButton;
	
	public MenuAdminView() {
		this.setSize(new Dimension(1440,1024));
		setLayout(null);
		
		registerButton = new JButton("Register User");
		registerButton.setBounds(508, 132, 106, 28);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(registerButton);
		
		viewButton = new JButton("View All User");
		viewButton.setBounds(508, 202, 106, 28);
		viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(viewButton);
		
		logOutButton = new JButton("Log Out");
		logOutButton.setBounds(508, 264, 106, 28);
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		return viewButton;
	}

	public JButton getLogOutButton() {
		return logOutButton;
	}
}