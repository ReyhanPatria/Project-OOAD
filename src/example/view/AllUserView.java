package example.view;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllUserView extends JPanel implements ViewPanel{
	// STATIC ATTRIBUTES
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	// NON-STATIC ATTRIBUTES
	private JLabel bground;
	
	private JButton resetPasswordButton;
	private JButton homeButton;
	private JButton profileButton;
	private JButton deleteUserButton;
	private JButton createButton;
	
	private JTable userDataTable;

	
	
	
	
	// NON-STATIC ATTRIBUTES
	public AllUserView() {
		
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		// user Table
		userDataTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(userDataTable);
		tableScrollPane.setBounds(75, 170, 950, 500);
		this.add(tableScrollPane);
		
		// Reset button
		resetPasswordButton = new JButton("");
		resetPasswordButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ResetPassword.png"));
		resetPasswordButton.setBounds(1063, 594, 276, 62);
		resetPasswordButton.setBorderPainted(false);
		resetPasswordButton.setOpaque(false);
		resetPasswordButton.setContentAreaFilled(false);
		resetPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		resetPasswordButton.setFocusable(false);
		this.add(resetPasswordButton);
		
		// delete user button
		deleteUserButton = new JButton("");
		deleteUserButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\DeleteUser.png"));
		deleteUserButton.setBounds(1063, 460, 276, 62);
		deleteUserButton.setBorderPainted(false);
		deleteUserButton.setOpaque(false);
		deleteUserButton.setContentAreaFilled(false);
		deleteUserButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteUserButton.setFocusable(false);
		this.add(deleteUserButton);
		
		// create user button
		createButton = new JButton("");
		createButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\CreateUser.png"));
		createButton.setBounds(1063, 324, 276, 62);
		createButton.setBorderPainted(false);
		createButton.setOpaque(false);
		createButton.setContentAreaFilled(false);
		createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		createButton.setFocusable(false);
		this.add(createButton);
		
		// profile button
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1205, 22, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		profileButton.setFocusable(false);
		this.add(profileButton);

		//home button
		homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Home.png"));
		homeButton.setBounds(1044, 22, 134, 48);	
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(homeButton);
		
		// Label gambar background 
		bground = new JLabel("");
		bground.setBounds(0, 0, 1440, 1024);
		bground.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\POLOSAN.png"));
		bground.setToolTipText("");
		this.add(bground);
	}

	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getDeleteUserButton() {
		return deleteUserButton;
	}

	public JButton getResetPasswordButton() {
		return resetPasswordButton;
	}

	public JTable getUserDataTable() {
		return userDataTable;
	}

	public JLabel getBground() {
		return bground;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public JButton getCreateButton() {
		return createButton;
	}
}
