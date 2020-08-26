package example.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import example.Main;
import example.controller.MainController;

public class LoginPanel extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JLabel lbl_Invalid;
	private JLabel loginButton;
	private JLabel lblBackground;
	private JLabel lbl_loginScreen;
	
	public LoginPanel() {
		/*
		 * Set panel's preferred size
		 */
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
				setLayout(null);
		
		// Label invalid (Untuk menunjukkan bahwa username atau password invalid)
		lbl_Invalid = new JLabel("Invalid username or password");
		lbl_Invalid.setForeground(Color.RED);
		lbl_Invalid.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lbl_Invalid.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Invalid.setBounds(460, 388, 197, 23);
		this.add(lbl_Invalid);
		lbl_Invalid.setVisible(false);
		
		/*
		 * Initialize GridBagLayout on panel
		 */
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{0, 118, 0, 0};
//		gridBagLayout.rowHeights = new int[]{0, 0, 30, 0, 0, 30, 0, 0, 30, 0, 30, 0, 0, 0, 0};
//		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
//		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
//		setLayout(gridBagLayout);
		
		/*
		 * Panel title label
		 */
//		JLabel panelTitleLabel = new JLabel("Login");
//		GridBagConstraints gbc_panelTitleLabel = new GridBagConstraints();
//		gbc_panelTitleLabel.insets = new Insets(0, 0, 5, 5);
//		gbc_panelTitleLabel.gridx = 1;
//		gbc_panelTitleLabel.gridy = 1;
//		add(panelTitleLabel, gbc_panelTitleLabel);
//		
		/*
		 * Adding Username TextField and Label
		 */
		
//		JButton loginButton = new JButton("Login");
//		loginButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String username = usernameTextField.getText();
//				String password = String.valueOf(passwordTextField.getPassword());
//				
//				try {
//					UserController.login(username, password);
//				}
//				catch(Exception e1) {
//					System.out.println(e1.getMessage());
//					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
//					e1.printStackTrace();
//				}
//			}
//		});
				
//		GridBagConstraints gbc_loginButton = new GridBagConstraints();
//		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
//		gbc_loginButton.gridx = 1;
//		gbc_loginButton.gridy = 9;
//		add(loginButton, gbc_loginButton);
//				
//				loginButton.setHorizontalAlignment(SwingConstants.CENTER);
				
//		JLabel usernameLabel = new JLabel("Username");
//		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
//		gbc_usernameLabel.anchor = GridBagConstraints.WEST;
//		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
//		gbc_usernameLabel.gridx = 1;
//		gbc_usernameLabel.gridy = 3;
//		add(usernameLabel, gbc_usernameLabel);
//		
		// Text Field untuk isi username
		usernameTextField = new JTextField("Input your username");
		usernameTextField.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		usernameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameTextField.setBounds(460, 339, 520, 50);
		this.add(usernameTextField);
		usernameTextField.setColumns(10);
		usernameTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				usernameTextField.setText("");
			}
		});

		
//		usernameTextField = new JTextField();
//		usernameLabel.setLabelFor(usernameTextField);
//		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
//		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
//		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
//		gbc_usernameTextField.gridx = 1;
//		gbc_usernameTextField.gridy = 4;
//		add(usernameTextField, gbc_usernameTextField);
//		usernameTextField.setColumns(10);
		
		/*
		 * Adding Password TextField and Label
		 */
		
		JButton loginButton = new JButton("");
				//				loginButton.addMouseListener(new MouseAdapter() {
				//					@Override
				//					public void mouseClicked(MouseEvent e) {
				//						// Validasi
				//						String username = usernameTextField.getText();
				//				String password = String.valueOf(passwordTextField.getPassword());
				//				
				//				try {
				//					UserController.login(username, password);
				//				}
				//				catch(Exception e1) {
				//					System.out.println(e1.getMessage());
				//					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				//					e1.printStackTrace();
				//				}
				//					}
				//				});
				//				
		loginButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\LogMeIn.png"));
		loginButton.setBounds(582, 543, 276, 62);
		loginButton.setBorderPainted(false);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusable(false);
		this.add(loginButton);
		
		// Password field untuk isi password
		passwordTextField = new JPasswordField();
		passwordTextField.setToolTipText("Input your password");
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordTextField.setBounds(460, 467, 520, 50);
		this.add(passwordTextField);
		
		//Back Button
		JButton backButton = new JButton("Cancel");
		backButton.setBounds(1238, 681, 89, 23);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.changePanel(new FirstPage());
			}
		});
		this.add(backButton);
		
		
		// Label gambar background 
		lblBackground = new JLabel("");
		lblBackground.setToolTipText("");
		lblBackground.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\LoginPage.png"));
		lblBackground.setBounds(0, 0, 1365, 735);
		this.add(lblBackground);

		
	}
}
