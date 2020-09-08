package example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllUserView extends JPanel implements ViewPanel{
	// STATIC ATTRIBUTES
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	// NON-STATIC ATTRIBUTES
	private JButton deleteUserButton;
	private JButton resetPasswordButton;
	private JTable userDataTable;

	
	
	
	
	// NON-STATIC ATTRIBUTES
	public AllUserView() {
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new BorderLayout());
		
		// top action panel
		JPanel topActionPanel = new JPanel();
		topActionPanel.setLayout(new FlowLayout());
		
		deleteUserButton = new JButton("Delete User");
		resetPasswordButton = new JButton("Reset Password");
		
		topActionPanel.add(deleteUserButton);
		topActionPanel.add(resetPasswordButton);
		
		//data panel
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		
		// user data table
		userDataTable = new JTable();
		dataPanel.add(new JScrollPane(userDataTable), BorderLayout.CENTER);
		
		this.add(topActionPanel, BorderLayout.NORTH);
		this.add(dataPanel, BorderLayout.CENTER);
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
}
