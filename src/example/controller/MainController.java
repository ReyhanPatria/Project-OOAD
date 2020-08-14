package example.controller;

import javax.swing.JPanel;

import example.view.MainFrame;

public class MainController {
	private static MainController instance;
	
	private MainFrame mainFrame;
	
	public MainController() {
		mainFrame = MainFrame.getInstance();
	}
	
	public static MainController getInstance() {
		if(instance == null) {
			instance = new MainController();
		}
		return instance;
	}
	
	public void changePanel(JPanel newPanel) {
		mainFrame.invalidate();
		mainFrame.setContentPane(newPanel);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	public MainFrame getMainFrame() {
		return mainFrame;
	}
}
