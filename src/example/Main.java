package example;

import javax.swing.JFrame;
import javax.swing.JPanel;

import example.view.UserView;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	private static int WIDTH = 800;
	private static int HEIGHT = 450;
	
	private JPanel viewPanel;
	
	public Main() {
		this.viewPanel = new UserView(WIDTH, HEIGHT);
		
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(viewPanel);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
