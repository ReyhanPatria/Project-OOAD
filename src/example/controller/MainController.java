package example.controller;

import javax.swing.JPanel;

import example.view.MainFrame;

public class MainController {
	// START OF STATIC FUNCTIONS
	// Changed MainFrame panel to a new panel
	public static void changePanel(JPanel newPanel) {
		MainFrame.getInstance().invalidate();
		MainFrame.getInstance().setContentPane(newPanel);
		MainFrame.getInstance().revalidate();
		MainFrame.getInstance().repaint();
	}
}
