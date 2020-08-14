package example.view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static MainFrame instance;
	
	public final static Dimension SCREEN_SIZE = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
	
	private final static Integer MINIMUM_SIZE_WIDTH = (int) (SCREEN_SIZE.getWidth() * 0.5);
	private final static Integer MINIMUM_SIZE_HEIGHT = (int) (SCREEN_SIZE.getHeight() * 0.5);
	public final static Dimension MINIMUM_SIZE = new Dimension(MINIMUM_SIZE_WIDTH, MINIMUM_SIZE_HEIGHT);
	
	private final static Integer PREFERRED_SIZE_WIDTH = (int) (SCREEN_SIZE.getWidth() * 0.8);
	private final static Integer PREFERRED_SIZE_HEIGHT = (int) (SCREEN_SIZE.getHeight() * 0.8);
	public final static Dimension PREFERRED_SIZE = new Dimension(PREFERRED_SIZE_WIDTH, PREFERRED_SIZE_HEIGHT);
	
	
	public MainFrame() {
		this.setSize(PREFERRED_SIZE);
		this.setMinimumSize(MINIMUM_SIZE);
		this.setPreferredSize(PREFERRED_SIZE);
		this.setMaximumSize(SCREEN_SIZE);
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
