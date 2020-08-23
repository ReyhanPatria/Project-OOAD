package example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import example.controller.MainController;
import example.controller.UserController;
import javax.swing.JPasswordField;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Font;

//public class LoginPanel extends JPanel {
//	private static final long serialVersionUID = 1L;
//
//	private static final String MAXIMIZED_BOTH = null;
//	
//	private JTextField usernameTextField;
//	private JPasswordField passwordTextField;

	public class LoginPanel extends JPanel {

		private JPanel contentPane;
		private JPanel panel;
		private JTextField usernameTextField;
		private JPasswordField passwordTextField;
		private JLabel close;
		private JLabel lbl_Invalid;
		private JLabel back;
		private JLabel loginButton;
		private JLabel lblBackground;
		private JLabel lbl_loginScreen;
		private JButton btnNewButton;
		private JButton btnNewButton_1;
	
	public LoginPanel() {
		/*
		 * Set panel's preferred size
		 */
		this.setPreferredSize(MainFrame.SCREEN_SIZE);
		
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
		setLayout(null);
		
		btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setBounds(673, 496, 89, 23);
		add(btnNewButton_1);
				
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
		usernameTextField.setBounds(643, 163, 149, 29);
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
		
				loginButton = new JLabel("");
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
								loginButton.setIcon(new ImageIcon("C:\\Users\\HP\\Documents\\Cawu 3\\Periode 2\\Object Oriented Analysis and Design\\Aslab\\Project\\Project-OOAD\\src\\example\\IMAGE\\log.jpg"));
								loginButton.setBounds(507, 50, -112, -31);
								this.add(loginButton);
//		JLabel passwordLabel = new JLabel("Password");
//		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
//		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
//		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
//		gbc_passwordLabel.gridx = 1;
//		gbc_passwordLabel.gridy = 6;
//		add(passwordLabel, gbc_passwordLabel);
//		
//		passwordTextField = new JPasswordField();
//		passwordLabel.setLabelFor(passwordTextField);
//		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
//		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
//		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
//		gbc_passwordTextField.gridx = 1;
//		gbc_passwordTextField.gridy = 7;
//		add(passwordTextField, gbc_passwordTextField);
//		passwordTextField.setColumns(10);
		
		// Password field untuk isi password
		passwordTextField = new JPasswordField();
		passwordTextField.setToolTipText("Input your password");
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordTextField.setBounds(643, 434, 149, 21);
		this.add(passwordTextField);

		
		/*
		 * Adding Login Button
		 */
		
//		/*
//		 * Added signup label and button
//		 */
//		JLabel signupLabel = new JLabel("Don't have an account?");
//		GridBagConstraints gbc_signupLabel = new GridBagConstraints();
//		gbc_signupLabel.insets = new Insets(0, 0, 5, 5);
//		gbc_signupLabel.gridx = 1;
//		gbc_signupLabel.gridy = 11;
//		add(signupLabel, gbc_signupLabel);
//		
//		JButton signupButton = new JButton("Sign up");
//		signupButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MainController.changePanel(new RegisterUserPanel());
//			}
//		});
//		GridBagConstraints gbc_signupButton = new GridBagConstraints();
//		gbc_signupButton.insets = new Insets(0, 0, 5, 5);
//		gbc_signupButton.gridx = 1;
//		gbc_signupButton.gridy = 12;
//		add(signupButton, gbc_signupButton);
		

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		
//		// Panel Utama | Panel Background (Login Screen)
//		contentPane = new JPanel();
//		this.setBackground(Color.WHITE);
//		this.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		
		// Panel Background (Atasnya Panel Utama)
//		panel = new JPanel();
//		panel.setBounds(0, 0, 0, 0);
//		panel.setBackground(Color.WHITE);
//		this.add(panel);
//		this.setLayout(null);
//		
		// Logo silang untuk quit
//		close = new JLabel("X");
//		close.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.exit(0); // Exit Program
//			}
//		});
//		close.setForeground(Color.BLACK);
//		close.setFont(new Font("Nova Square", Font.BOLD, 24));
//		close.setBounds(1323, 0, 27, 37);
//		this.add(close);
		
		// Label invalid (Untuk menunjukkan bahwa username atau password invalid)
		lbl_Invalid = new JLabel("Invalid username or password");
		lbl_Invalid.setForeground(Color.RED);
		lbl_Invalid.setFont(new Font("Segoe Print", Font.PLAIN, 24));
		lbl_Invalid.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Invalid.setBounds(536, 220, 380, 44);
		this.add(lbl_Invalid);
		
//		back.setHorizontalAlignment(SwingConstants.CENTER);
//		back.setIcon(new ImageIcon("C:\\Users\\HP\\Documents\\Cawu 3\\Periode 2\\Object Oriented Analysis and Design\\Aslab\\Project\\Project-OOAD\\src\\example\\IMAGE\\cancel.jpg"));
//		back.setBounds(956, 547, 209, 53);
//		this.add(back);
		
		// Label gambar background 
		lblBackground = new JLabel("");
		lblBackground.setToolTipText("");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\HP\\Documents\\Cawu 3\\Periode 2\\Object Oriented Analysis and Design\\Aslab\\Project\\Project-OOAD\\src\\example\\IMAGE\\loginuser.png"));
		lblBackground.setBounds(0, 0, 960, 540);
		this.add(lblBackground);
		lbl_Invalid.setVisible(false);
		
		
	}
}
