package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ChangePasswordView extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel bground;
	
	private JPasswordField	oldPassField;
	private JPasswordField	newPassField;
	
	private JButton updateButton;
	private JButton menuButton;
	private JButton profileButton;
	private JButton notifButton;
	private JButton backButton;
	
	public ChangePasswordView() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		//back button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Back.png"));
		backButton.setBounds(61, 85, 134, 48);
		backButton.setBorderPainted(false);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(backButton);
		
		//notification button
		notifButton = new JButton("");
		notifButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Notifications.png"));
		notifButton.setBounds(992, 6, 50, 50);
		notifButton.setBorderPainted(false);
		notifButton.setOpaque(false);
		notifButton.setContentAreaFilled(false);
		notifButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(notifButton);
		
		// Text Field untuk isi old password
		oldPassField = new JPasswordField("");
		oldPassField.setBounds(440, 335, 488, 37);
		oldPassField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		oldPassField.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(oldPassField);
		oldPassField.setColumns(10);
		
		
		// new password text field
		newPassField = new JPasswordField();
		newPassField.setBounds(440, 425, 488, 37);
		newPassField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(newPassField);
		
		// Register button
		updateButton = new JButton("");
		updateButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Update.png"));
		updateButton.setBounds(542, 482, 276, 62);
		updateButton.setBorderPainted(false);
		updateButton.setOpaque(false);
		updateButton.setContentAreaFilled(false);
		updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateButton.setFocusable(false);
		this.add(updateButton);
		
		// Back to profile button
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1208, 8, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		profileButton.setFocusable(false);
		this.add(profileButton);

		// menu button
		menuButton = new JButton("");
		menuButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Menu.png"));
		menuButton.setBounds(1064, 8, 134, 48);	
		menuButton.setBorderPainted(false);
		menuButton.setOpaque(false);
		menuButton.setContentAreaFilled(false);
		menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(menuButton);
		
		// Label gambar background 
		bground = new JLabel("");
		bground.setBounds(0, -10, 1366, 756);		
		bground.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ChangePasswordPage2.png"));
		bground.setToolTipText("");
		this.add(bground);
	}

	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBground() {
		return bground;
	}

	public JTextField getUsernameTextField() {
		return oldPassField;
	}


	public JTextField getAddressTextField() {
		return newPassField;
	}


	public JButton getRegisterButton() {
		return updateButton;
	}

	public JButton getCancelButton() {
		return menuButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public JTextField getOldPassField() {
		return oldPassField;
	}

	public JTextField getNewPassField() {
		return newPassField;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public JButton getMenuButton() {
		return menuButton;
	}

	public JButton getNotifButton() {
		return notifButton;
	}

	public JButton getBackButton() {
		return backButton;
	}
	
	
	
}
