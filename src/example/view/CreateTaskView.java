package example.view;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class CreateTaskView extends JPanel implements ViewPanel {
	// STATIC ATTRIBUTES
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	// NON-STATIC ATTRRIBUTES
	private JLabel bground;
	
	private JTextField titleTextField;
	
	private JTextArea noteField;
	
	private JComboBox<Object> workerComboBox;
	private JComboBox<Object> supervisorComboBox;
	
	private JButton createButton;
	private JButton menuButton;
	private JButton profileButton;
	private JButton backButton;
	private JButton notifButton;
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public CreateTaskView() {
		// Set panel's preferred size
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		setLayout(null);
		
		// Text Field untuk isi title
		titleTextField = new JTextField("");
		titleTextField.setBounds(565, 240, 565, 50);
		titleTextField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		titleTextField.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(titleTextField);
		titleTextField.setColumns(10);


		// Choose role combo box
		workerComboBox = new JComboBox<Object>();
		workerComboBox.setBounds(565, 379, 565, 50);
		workerComboBox.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(workerComboBox);
		
		
		supervisorComboBox = new JComboBox<Object>();
		supervisorComboBox.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		supervisorComboBox.setBounds(565, 310, 565, 50);
		this.add(supervisorComboBox);

		// note text field
		noteField = new JTextArea();
		noteField.setBounds(565, 453, 565, 85);
		noteField.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.add(noteField);
		noteField.setColumns(10);
		
		// create button
		createButton = new JButton("");
		createButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Create.png"));
		createButton.setBounds(565, 590, 208, 55);
		createButton.setBorderPainted(false);
		createButton.setOpaque(false);
		createButton.setContentAreaFilled(false);
		createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		createButton.setFocusable(false);
		this.add(createButton);
		
		// Back to profile button
		profileButton = new JButton("");
		profileButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\Profile.png"));
		profileButton.setBounds(1215, 8, 134, 48);
		profileButton.setBorderPainted(false);
		profileButton.setOpaque(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		profileButton.setFocusable(false);
		this.add(profileButton);

		// menu button
		menuButton = new JButton("");
		menuButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Menu.png"));
		menuButton.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		menuButton.setBounds(1065, 8, 134, 48);	
		menuButton.setBorderPainted(false);
		menuButton.setOpaque(false);
		menuButton.setContentAreaFilled(false);
		menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(menuButton);
		
		//back button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Back.png"));
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setBounds(42, 78, 134, 48);
		this.add(backButton);
		
		//notification button
		notifButton = new JButton("");
		notifButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Notifications.png"));
		notifButton.setOpaque(false);
		notifButton.setContentAreaFilled(false);
		notifButton.setBorderPainted(false);
		notifButton.setBounds(990, 8, 50, 50);
		this.add(notifButton);
		
		// Label gambar background 
		bground = new JLabel("");
		bground.setBounds(0, -10, 1366, 756);
		bground.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\CreateTaskPageSupervisor2.png"));
		bground.setToolTipText("");
		this.add(bground);
	}
	
	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getBground() {
		return bground;
	}

	public JTextField getTitleTextField() {
		return titleTextField;
	}

	public JTextArea getNoteField() {
		return noteField;
	}

	public JComboBox<Object> getWorkerComboBox() {
		return workerComboBox;
	}

	public JComboBox<Object> getSupervisorComboBox() {
		return supervisorComboBox;
	}

	public JButton getCreateButton() {
		return createButton;
	}

	public JButton getMenuButton() {
		return menuButton;
	}

	public JButton getProfileButton() {
		return profileButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getNotifButton() {
		return notifButton;
	}
}
