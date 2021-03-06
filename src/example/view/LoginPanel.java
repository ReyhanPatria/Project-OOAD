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

public class LoginPanel extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JLabel lbl_Invalid;
	private JLabel lblBackground;
	private JButton loginButton;
	private JButton backButton;
	
	public LoginPanel() {
		// Set panel's preferred size
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
				if(usernameTextField.getText().equals("Input your username")) {
					usernameTextField.setText("");
				}
			}
		});
		
		// Login button
		loginButton = new JButton("");	
		loginButton.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\LogMeIn.png"));
		loginButton.setBounds(582, 543, 276, 62);
		loginButton.setBorderPainted(false);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusable(false);
		this.loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(loginButton);
		
		// Password field untuk isi password
		passwordTextField = new JPasswordField();
		passwordTextField.setToolTipText("Input your password");
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordTextField.setBounds(460, 467, 520, 50);
		this.add(passwordTextField);
		
		//validasi data username sama password ada di db atau nggak hrsnya di controller
		
		//Back Button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(srcFilePath+"\\example\\IMAGE\\Back2.png"));
		backButton.setBounds(1150, 650, 173, 61);
		backButton.setBorderPainted(false);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusable(false);
		this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(backButton);
		
		// Label gambar background 
		lblBackground = new JLabel("");
		lblBackground.setToolTipText("");
		lblBackground.setIcon(new ImageIcon(srcFilePath + "\\example\\IMAGE\\LoginPage.png"));
		lblBackground.setBounds(0, 0, 1365, 735);
		this.add(lblBackground);
	}

	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JPasswordField getPasswordTextField() {
		return passwordTextField;
	}

	public JLabel getLbl_Invalid() {
		return lbl_Invalid;
	}

	public JLabel getLblBackground() {
		return lblBackground;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getBackButton() {
		return backButton;
	}
}
