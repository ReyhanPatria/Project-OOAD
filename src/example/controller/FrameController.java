package example.controller;

import javax.swing.JPanel;

import example.view.MainFrame;

public class FrameController {
	// STATIC ATTRIBUTES
	private static FrameController instance;
	
	
	
	
	// STATIC FUNCTIONS
	// Get instance of frame controller
	public static FrameController getInstance() {
		if(instance == null) {
			instance = new FrameController();
		}
		return instance;
	}
	
	
	
	
	
	// NON-STATIC FUNCTIONS
	// Constructor
	public FrameController() {}
	
	// Changed MainFrame panel to a new panel
	public void changePanel(JPanel newPanel) {
		MainFrame.getInstance().invalidate();
		MainFrame.getInstance().setContentPane(newPanel);
		MainFrame.getInstance().revalidate();
		MainFrame.getInstance().repaint();
	}
}
