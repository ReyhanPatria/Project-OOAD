package example.view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static MainFrame instance;
	
	private static Integer WIDTH = 800;
	private static Integer HEIGHT = 450;
	
	public MainFrame() {
		this.setSize(WIDTH, HEIGHT);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
}
