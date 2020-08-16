package example.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import example.controller.UserController;
import example.model.User;

public class UserProfilePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public UserProfilePanel() {
		UUID id;
		User user = null;
		
		try {
			id = UserController.getInstance().getCurrentUserId();
			user = UserController.getUser(id);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		/*
		 * Set panel's preferred size
		 */
		this.setPreferredSize(MainFrame.PREFERRED_SIZE);
		
		/*
		 * Create GridBagLayout on panel
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 100, 73, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/*
		 * UserID
		 */
		JLabel userIdLabel = new JLabel("UserID");
		GridBagConstraints gbc_userIdLabel = new GridBagConstraints();
		gbc_userIdLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userIdLabel.gridx = 1;
		gbc_userIdLabel.gridy = 1;
		add(userIdLabel, gbc_userIdLabel);
		
		JLabel userIdValueLabel = new JLabel(user.getId().toString());
		GridBagConstraints gbc_userIdValueLabel = new GridBagConstraints();
		gbc_userIdValueLabel.anchor = GridBagConstraints.WEST;
		gbc_userIdValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userIdValueLabel.gridx = 2;
		gbc_userIdValueLabel.gridy = 1;
		add(userIdValueLabel, gbc_userIdValueLabel);
		
		/*
		 * Username
		 */
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 2;
		add(usernameLabel, gbc_usernameLabel);
		
		JLabel usernameValueLabel = new JLabel(user.getUsername());
		GridBagConstraints gbc_usernameValueLabel = new GridBagConstraints();
		gbc_usernameValueLabel.anchor = GridBagConstraints.WEST;
		gbc_usernameValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameValueLabel.gridx = 2;
		gbc_usernameValueLabel.gridy = 2;
		add(usernameValueLabel, gbc_usernameValueLabel);
		
		/*
		 * Password
		 */
		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 3;
		add(passwordLabel, gbc_passwordLabel);
		
		JLabel passwordValueLabel = new JLabel(user.getPassword());
		GridBagConstraints gbc_passwordValueLabel = new GridBagConstraints();
		gbc_passwordValueLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordValueLabel.gridx = 2;
		gbc_passwordValueLabel.gridy = 3;
		add(passwordValueLabel, gbc_passwordValueLabel);
		
		/*
		 * Role
		 */
		JLabel roleLabel = new JLabel("Role");
		GridBagConstraints gbc_roleLabel = new GridBagConstraints();
		gbc_roleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_roleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_roleLabel.gridx = 1;
		gbc_roleLabel.gridy = 4;
		add(roleLabel, gbc_roleLabel);
		
		JLabel roleValueLabel = new JLabel(user.getRole());
		GridBagConstraints gbc_roleValueLabel = new GridBagConstraints();
		gbc_roleValueLabel.anchor = GridBagConstraints.WEST;
		gbc_roleValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_roleValueLabel.gridx = 2;
		gbc_roleValueLabel.gridy = 4;
		add(roleValueLabel, gbc_roleValueLabel);
		
		/*
		 * Address
		 */
		JLabel addressLabel = new JLabel("Address");
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressLabel.gridx = 1;
		gbc_addressLabel.gridy = 5;
		add(addressLabel, gbc_addressLabel);
		
		JLabel addressValueLabel = new JLabel(String.format("<html>%s</html>", user.getAddress()));
		GridBagConstraints gbc_addressValueLabel = new GridBagConstraints();
		gbc_addressValueLabel.anchor = GridBagConstraints.WEST;
		gbc_addressValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressValueLabel.gridx = 2;
		gbc_addressValueLabel.gridy = 5;
		add(addressValueLabel, gbc_addressValueLabel);
		
		/*
		 * DOB
		 */
		JLabel DOBLabel = new JLabel("Date of Birth");
		GridBagConstraints gbc_DOBLabel = new GridBagConstraints();
		gbc_DOBLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_DOBLabel.insets = new Insets(0, 0, 5, 5);
		gbc_DOBLabel.gridx = 1;
		gbc_DOBLabel.gridy = 6;
		add(DOBLabel, gbc_DOBLabel);
		
		JLabel DOBValueLabel = new JLabel(user.getDOB().toString());
		GridBagConstraints gbc_DOBValueLabel = new GridBagConstraints();
		gbc_DOBValueLabel.anchor = GridBagConstraints.WEST;
		gbc_DOBValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_DOBValueLabel.gridx = 2;
		gbc_DOBValueLabel.gridy = 6;
		add(DOBValueLabel, gbc_DOBValueLabel);
		
		/*
		 * Telp
		 */
		JLabel telpLabel = new JLabel("Telp");
		GridBagConstraints gbc_telpLabel = new GridBagConstraints();
		gbc_telpLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_telpLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telpLabel.gridx = 1;
		gbc_telpLabel.gridy = 7;
		add(telpLabel, gbc_telpLabel);
		
		JLabel telpValueLabel = new JLabel(user.getTelp());
		GridBagConstraints gbc_telpValueLabel = new GridBagConstraints();
		gbc_telpValueLabel.anchor = GridBagConstraints.WEST;
		gbc_telpValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telpValueLabel.gridx = 2;
		gbc_telpValueLabel.gridy = 7;
		add(telpValueLabel, gbc_telpValueLabel);
		
		/*
		 * Logout button
		 */
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.logout();
			}
		});
		GridBagConstraints gbc_logoutButton = new GridBagConstraints();
		gbc_logoutButton.gridwidth = 2;
		gbc_logoutButton.insets = new Insets(0, 0, 5, 5);
		gbc_logoutButton.gridx = 1;
		gbc_logoutButton.gridy = 9;
		add(logoutButton, gbc_logoutButton);
	}
}
