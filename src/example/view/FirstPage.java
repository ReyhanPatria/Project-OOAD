package example.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import example.Main;
import example.controller.MainController;

public class FirstPage extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel background;
	
	public FirstPage() {
		
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		//Login Button
		JButton loginButton = new JButton("");
		loginButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Loginbutton.png"));
		loginButton.setBounds(984, 417, 230, 80);
		loginButton.setBorderPainted(false);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusable(false);
//		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.changePanel(new LoginPanel());
			}
		});
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 12;
		add(loginButton);
		
		background = new JLabel("background");
		background.setToolTipText("");
		background.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\LandingPage.png"));
		background.setBounds(0, 0, 1350, 700);
		this.add(background);
	
	}

}