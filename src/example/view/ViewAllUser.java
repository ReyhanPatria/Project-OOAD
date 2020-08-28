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

public class ViewAllUser extends JPanel{
	private JButton deleteuser;
	private JButton resetpass;
	private JTable dataTable;

	public ViewAllUser() {
		// TODO Auto-generated constructor stub
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new BorderLayout());
		
		// top action panel
		JPanel topActionPanel = new JPanel();
		topActionPanel.setLayout(new FlowLayout());
		
		deleteuser = new JButton("Delete User");
		resetpass = new JButton("Reset Password");
		dataTable = new JTable();
		
		String[][] userDummy = {{"12345-abc","admin", "abcdefg", "worker", "Surabaya", "2000-09-09", "0987654321"}};
		String[] tableHeader = {"id","username", "password", "role", "address", "DOB", "telp"};
		dataTable = new JTable(userDummy, tableHeader);

	}

}
