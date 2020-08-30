package example.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import example.controller.FrameController;

public class MenuAdminView extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel background;
	
	public MenuAdminView() {
		
		this.setSize(new Dimension(1440,1024));
		setLayout(null);
		
		JButton registerButton = new JButton("Register User");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameController.changePanel(new RegisterUserPanel());
			}
		});
		registerButton.setBounds(508, 132, 106, 28);
		add(registerButton);
		
		JButton ViewButton = new JButton("View All User");
		ViewButton.setBounds(508, 202, 106, 28);
		add(ViewButton);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.setBounds(508, 264, 106, 28);
		logOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameController.changePanel(new FirstPage());
			}
		});
		add(logOutButton);
					
	}

}