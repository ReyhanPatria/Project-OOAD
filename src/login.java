
import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class login extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtInputYourUsername;
	private JPasswordField passwordField;
	private JLabel close;
	private JLabel lbl_Invalid;
	private JLabel back;
	private JLabel lbl_kuyPesen;
	private JLabel lblBackground;
	private JLabel lbl_loginScreen;

	public login() {
		setExtendedState(MAXIMIZED_BOTH); // Jadi fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1443, 900);
		
		// Panel Utama | Panel Background (Login Screen)
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Panel Background (Atasnya Panel Utama)
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// Logo silang untuk quit
		close = new JLabel("X");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0); // Exit Program
			}
		});
		close.setForeground(Color.BLACK);
		close.setFont(new Font("Nova Square", Font.BOLD, 24));
		close.setBounds(1323, 0, 27, 37);
		panel.add(close);
		
		// Label invalid (Untuk menunjukkan bahwa username atau password invalid)
		lbl_Invalid = new JLabel("Invalid username or password");
		lbl_Invalid.setForeground(Color.RED);
		lbl_Invalid.setFont(new Font("Segoe Print", Font.PLAIN, 24));
		lbl_Invalid.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Invalid.setBounds(754, 286, 383, 64);
		panel.add(lbl_Invalid);
		lbl_Invalid.setVisible(false);
		
		// Label back (Direct to menu awal - yang ada gambar panah)
		back = new JLabel("");
		back.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				mainmenu menuAwal = new mainmenu();
				menuAwal.setUndecorated(true);
				menuAwal.setVisible(true); // Direct to Menu Awal
				login.this.dispose();
			}
		});
		back.setHorizontalAlignment(SwingConstants.CENTER);
		back.setIcon(new ImageIcon(login.class.getResource("/IMAGE/cancel.jpg")));
		back.setBounds(956, 547, 209, 53);
		panel.add(back);
		
		// Text Field untuk isi username
		txtInputYourUsername = new JTextField("Input your username");
		txtInputYourUsername.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txtInputYourUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtInputYourUsername.setBounds(825, 239, 226, 26);
		panel.add(txtInputYourUsername);
		txtInputYourUsername.setColumns(10);
		txtInputYourUsername.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				txtInputYourUsername.setText("");
			}
		});
		
		// Label tulisan login GIF
//		lbl_loginScreen = new JLabel("New label");
//		lbl_loginScreen.setIcon(new ImageIcon(login.class.getResource("/IMAGE/loginscreen2.gif")));
//		lbl_loginScreen.setBounds(222, 317, 548, 107);
//		panel.add(lbl_loginScreen);
		
		// Password field untuk isi password
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Input your password");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordField.setBounds(849, 501, 182, 20);
		panel.add(passwordField);
		
		// Label untuk login (Direct to menu utama - yang ada logo kuy pesen!)
		lbl_kuyPesen = new JLabel("");
		lbl_kuyPesen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Validasi
				String tempUsername = txtInputYourUsername.getText();
				String tempPass = passwordField.getText();
				Boolean canLogin = false;
//				Vector<Member> members = FileHandler.getListMember();
//				for(Member m : members) {
//					if(m.auth(tempUsername, tempPass)) {
//						FileHandler.setCurrMember(m);
//						canLogin = true;
//						break;
//					}
//				}
				if(canLogin) {
					lbl_Invalid.setVisible(false);
					mainmenu menuUtama = new mainmenu();
					menuUtama.setUndecorated(true);
					menuUtama.setVisible(true); // Direct to Menu Utama
					login.this.dispose();
				}
				else {
					lbl_Invalid.setVisible(true);
				}
			}
		});
		lbl_kuyPesen.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_kuyPesen.setIcon(new ImageIcon(login.class.getResource("/IMAGE/log.jpg")));
		lbl_kuyPesen.setBounds(720, 547, 209, 53);
		panel.add(lbl_kuyPesen);
		
		// Label gambar background (AleTix, username, password)
		lblBackground = new JLabel("");
		lblBackground.setToolTipText("");
		lblBackground.setIcon(new ImageIcon(login.class.getResource("/IMAGE/loginuser.png")));
		lblBackground.setBounds(222, -11, 1163, 703);
		panel.add(lblBackground);
		
		
		
	}
}
