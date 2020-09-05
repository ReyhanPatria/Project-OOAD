package example.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NotificationSupervisorAndWorker extends JPanel implements ViewPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel bg;
	
	private JLabel	showNotif;
	
	private JButton homeButton;
	private JButton markButton;
	private JButton profileButton;
	private JButton taskButton;
	
	private JTextField txtSearch;
	
	public NotificationSupervisorAndWorker() {
		// TODO Auto-generated constructor stub
		
			// Set panel's preferred size
			this.setPreferredSize(MainFrame.SCREEN_SIZE);
					setLayout(null);
			
			//search field
			txtSearch = new JTextField();
			txtSearch.setFont(new Font("Cambria Math", Font.PLAIN, 20));
			txtSearch.setText("Search...");
			txtSearch.setBounds(404, 11, 537, 48);
			this.add(txtSearch);
			txtSearch.setColumns(10);
			
			//Button for create a task
			taskButton = new JButton("");
			taskButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\CreateTask.png"));
			taskButton.setBounds(963, 11, 50, 50);
			taskButton.setBorderPainted(false);
			taskButton.setOpaque(false);
			taskButton.setContentAreaFilled(false);
			taskButton.setFocusable(false);
			this.taskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.add(taskButton);
			
			//profile button
			profileButton = new JButton("");
			profileButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Profile.png"));
			profileButton.setBounds(1201, 11, 134, 48);
			profileButton.setBorderPainted(false);
			profileButton.setOpaque(false);
			profileButton.setContentAreaFilled(false);
			profileButton.setFocusable(false);
			this.profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.add(profileButton);
			
			// home button
			homeButton = new JButton("");	
			homeButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Home.png"));
			homeButton.setBounds(1043, 11, 134, 48);
			homeButton.setBorderPainted(false);
			homeButton.setOpaque(false);
			homeButton.setContentAreaFilled(false);
			homeButton.setFocusable(false);
			this.homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.add(homeButton);
			
			// mark as read button
			markButton = new JButton("");
			markButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\MarkAsRead.png"));
			markButton.setBounds(1029, 114, 306, 62);
			markButton.setBorderPainted(false);
			markButton.setOpaque(false);
			markButton.setContentAreaFilled(false);
			markButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			markButton.setFocusable(false);
			this.add(markButton);
			
			
			// notif
			showNotif = new JLabel();
			showNotif.setFont(new Font("Cambria Math", Font.PLAIN, 20));
			showNotif.setBounds(227, 196, 910, 50);
			this.add(showNotif);
			
			// Label gambar background 
			bg = new JLabel("");
			bg.setToolTipText("");
			bg.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\NotificationsPage2.png"));
			bg.setBounds(0, 0, 1365, 735);
			this.add(bg);
		}
	
	//GETTERS

	
}