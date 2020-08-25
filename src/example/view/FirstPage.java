package example.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import example.controller.MainController;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FirstPage extends JPanel {
	
	private JLabel background;

	public FirstPage() {
		
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		//Login Button
		JButton loginButton = new JButton("");
		loginButton.setIcon(new ImageIcon("C:\\Users\\HP\\Documents\\Cawu 3\\Periode 2\\Object Oriented Analysis and Design\\Aslab\\Project\\Project-OOAD\\src\\example\\IMAGE\\Loginbutton.png"));
		loginButton.setBounds(984, 417, 230, 80);
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
		background.setIcon(new ImageIcon("C:\\Users\\HP\\Documents\\Cawu 3\\Periode 2\\Object Oriented Analysis and Design\\Aslab\\Project\\Project-OOAD\\src\\example\\IMAGE\\LandingPage.png"));
		background.setBounds(0, 0, 1350, 700);
		this.add(background);
	
	}

}