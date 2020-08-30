package example.view;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPage extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel backgroundImage;
	private JButton loginButton;
	
	public FirstPage() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		// Login Button
		this.loginButton = new JButton("");
		this.loginButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Loginbutton.png"));
		this.loginButton.setBounds(984, 417, 230, 80);
		this.loginButton.setBorderPainted(false);
		this.loginButton.setOpaque(false);
		this.loginButton.setContentAreaFilled(false);
		this.loginButton.setFocusable(false);
		this.loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 12;
		this.add(this.loginButton);
		
		// Background image
		this.backgroundImage = new JLabel("background");
		this.backgroundImage.setToolTipText("");
		this.backgroundImage.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\LandingPage.png"));
		this.backgroundImage.setBounds(0, 0, 1350, 700);
		this.add(this.backgroundImage);
	}

	// GETTERS
	public JLabel getBackgroundImage() {
		return backgroundImage;
	}

	public JButton getLoginButton() {
		return loginButton;
	}
}