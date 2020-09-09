package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class NotificationView extends JPanel implements ViewPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel bg;
	
	private JButton homeButton;
	private JButton markButton;
	private JButton profileButton;
	
	private JTable notifDataTable;
	
	public NotificationView() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		this.setLayout(null);
		
		// notif Table
		notifDataTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(notifDataTable);
		tableScrollPane.setBounds(125, 250, 1100, 450);
		this.add(tableScrollPane);
		
		// profile button
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1201, 11, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setFocusable(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(profileButton);
		
		// home button
		homeButton = new JButton("");	
		homeButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Home.png"));
		homeButton.setBounds(1043, 11, 134, 48);
		homeButton.setBorderPainted(false);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusable(false);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(homeButton);
		
		// mark as read button
		markButton = new JButton("");
		markButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\MarkAsRead.png"));
		markButton.setBounds(1029, 150, 306, 62);
		markButton.setBorderPainted(false);
		markButton.setOpaque(false);
		markButton.setContentAreaFilled(false);
		markButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		markButton.setFocusable(false);
		this.add(markButton);
		
		// Label gambar background 
		bg = new JLabel("");
		bg.setToolTipText("");
		bg.setIcon(new ImageIcon("C:\\Users\\HP\\Documents\\Cawu 3\\Periode 2\\Object Oriented Analysis and Design\\Aslab\\Project-OOAD\\src\\example\\IMAGE\\POLOSANNotificationsPage.png"));
		bg.setBounds(0, 0, 1440, 1024);
		this.add(bg);
	}
	
	
	//GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBg() {
		return bg;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public JButton getMarkButton() {
		return markButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public JTable getNotifDataTable() {
		return notifDataTable;
	}
	
	


}
