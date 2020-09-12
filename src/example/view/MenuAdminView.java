package example.view;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class MenuAdminView extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel backgroundImage;
	private JButton viewButton;
	private JButton logOutButton;
	private JButton profileButton;
	
	public MenuAdminView() {
		this.setSize(new Dimension(1440,1024));
		setLayout(null);
		
		viewButton = new JButton("");
		viewButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\ViewAllUser.png"));
		viewButton.setBounds(366, 250, 308, 308);
		viewButton.setBorderPainted(false);
		viewButton.setOpaque(false);
		viewButton.setContentAreaFilled(false);
		viewButton.setFocusable(false);
		this.viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(viewButton);
		
		logOutButton = new JButton("");
		logOutButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Logout.png"));
		logOutButton.setBounds(804, 250, 308, 308);
		logOutButton.setBorderPainted(false);
		logOutButton.setOpaque(false);
		logOutButton.setContentAreaFilled(false);
		logOutButton.setFocusable(false);
		this.logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(logOutButton);				
		
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1214, 21, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setFocusable(false);
		this.profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(profileButton);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\POLOSAN.png"));
		backgroundImage.setBounds(0, 0, 1440, 1024);
		add(backgroundImage);
	}

	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBackgroundImage() {
		return backgroundImage;
	}

	public JButton getViewButton() {
		return viewButton;
	}

	public JButton getLogOutButton() {
		return logOutButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}
	
	
}