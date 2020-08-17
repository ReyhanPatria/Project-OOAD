package example.controller;

import javax.swing.JPanel;

import example.view.MainFrame;

public class MainController {
	public static void changePanel(JPanel newPanel) {
		MainFrame.getInstance().invalidate();
		MainFrame.getInstance().setContentPane(newPanel);
		MainFrame.getInstance().revalidate();
		MainFrame.getInstance().repaint();
	}
}
