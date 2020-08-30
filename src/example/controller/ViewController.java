package example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import example.view.FirstPage;
import example.view.LoginPanel;

public class ViewController {
	// STATIC FUNCTIONS
	public static FirstPage loadFirstPage() {
		FirstPage fp = new FirstPage();
		fp.getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameController.changePanel(new LoginPanel());
			}
		});
		
		FrameController.changePanel(fp);
		
		return fp;
	}
}
