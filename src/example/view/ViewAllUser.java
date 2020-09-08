package example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ViewAllUser extends JPanel implements ViewPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton deleteuser;
	private JButton resetpass;
	private JTable dataTable;

	public ViewAllUser() {
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new BorderLayout());
		
		// top action panel
		JPanel topActionPanel = new JPanel();
		topActionPanel.setLayout(new FlowLayout());
		
		deleteuser = new JButton("Delete User");
		resetpass = new JButton("Reset Password");
		
		topActionPanel.add(deleteuser);
		topActionPanel.add(resetpass);
		
		//data panel
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		
		dataTable = new JTable();
		
		String[][] userDummy = {{"12345-abc", "test", "admin", "surabaya", "2000-09-09", "0897654321"}};
		String[] tableHeader = {"id","username", "role", "address", "DOB", "telp"};
		dataTable = new JTable(userDummy, tableHeader);
		
		dataPanel.add(new JScrollPane(dataTable), BorderLayout.CENTER);
		
		this.add(topActionPanel, BorderLayout.NORTH);
		this.add(dataPanel, BorderLayout.CENTER);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getDeleteuser() {
		return deleteuser;
	}

	public JButton getResetpass() {
		return resetpass;
	}

	public JTable getDataTable() {
		return dataTable;
	}
	
	

}
