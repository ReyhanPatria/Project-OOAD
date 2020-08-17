import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainmenu extends JFrame {
	
	private JPanel contentPane;
	private JLabel close;
	private JLabel labelTix;
	private JButton btnRegister;
	private JLabel labelMovieTickets;
	private JPanel panel;
	private JLabel labelAle;
	private JLabel labelFindYour;
	private JButton btnLogin;
	private JPanel panel_1;

	public mainmenu() {
//		FileHandler.readFile();
//		FileHandler.readListMovie();
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Jadi fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1446, 788);
		
		// Panel Utama | Panel Background (Menu Awal)
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Panel sebelah kiri
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(238,87,3));
		panel_1.setBounds(0, 0, 689, 812);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		// Label sebelah kiri (Gambar find your GIF) -> Panel kiri
//		labelFindYour = new JLabel("");
//		labelFindYour.setIcon(new ImageIcon(mainmenu.class.getResource("/IMAGE/rsz_tulisan1.gif")));
//		labelFindYour.setBounds(383, 388, 306, 118);
//		panel_1.add(labelFindYour);
//		
		// Label sebelah kiri (Gambar background ALE warna kuning) -> Panel kiri
		labelAle = new JLabel("");
		labelAle.setIcon(new ImageIcon(mainmenu.class.getResource("/IMAGE/home1.png")));
		labelAle.setBounds(210, 11, 689, 753);
		panel_1.add(labelAle);
		
		// Panel sebelah kanan
		panel = new JPanel();
		panel.setBounds(690, 0, 680, 812);
		panel.setBackground(new Color(241,174,3));
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Label sebelah kanan (Gambar movie tickets! GIF) -> Panel kanan
//		labelMovieTickets = new JLabel("");
//		labelMovieTickets.setIcon(new ImageIcon(mainmenu.class.getResource("/IMAGE/rsz_tulisan2.gif")));
//		labelMovieTickets.setBounds(0, 411, 283, 84);
//		panel.add(labelMovieTickets);
		
		// Button registraion GIF -> Panel kanan
		btnRegister = new JButton("");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register registerScreen = new Register(); 
				registerScreen.setUndecorated(true);
				registerScreen.setVisible(true); // Direct to Register Screen
				mainmenu.this.dispose();
			}
		});
		btnRegister.setForeground(new Color(51, 153, 255));
		btnRegister.setIcon(new ImageIcon(mainmenu.class.getResource("/IMAGE/reg.jpg")));
		btnRegister.setBackground(new Color(255, 255, 102));
		btnRegister.setBounds(123, 418, 254, 62);	
		panel.add(btnRegister);
		
		// Label sebelah kanan (Gambar background TIX warna biru) -> Panel kanan
		labelTix = new JLabel("");
		labelTix.setIcon(new ImageIcon(mainmenu.class.getResource("/IMAGE/home2.png")));
		labelTix.setBounds(0, -60, 696, 894);
		panel.add(labelTix);
		
		// Label silang (Pojok kanan atas) -> Panel kanan
		close = new JLabel("X");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0); // Exit Program
			}
		});
		close.setForeground(new Color(255, 255, 255));
		close.setFont(new Font("Nova Square", Font.BOLD, 25));
		close.setBounds(638, 0, 32, 31);
		panel.add(close);
		
		// Button Login GIF (Direct ke login screen) -> Panel kiri
		btnLogin = new JButton("");
		btnLogin.setBounds(123, 289, 254, 62);
		panel.add(btnLogin);
		btnLogin.setIcon(new ImageIcon(mainmenu.class.getResource("/IMAGE/log.jpg")));
		btnLogin.setForeground(new Color(51, 153, 255));
		btnLogin.setBackground(new Color(255, 255, 102));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				login menuLogin = new login(); 
				menuLogin.setUndecorated(true);
				menuLogin.setVisible(true); // Direct to Login Screen
				mainmenu.this.dispose();
			}
		});
	}

}
