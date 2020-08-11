package example;

import example.controller.MainController;
import example.view.LoginPanel;

public class Main {
	public Main() {
//		MainController.getInstance();
		MainController.getInstance().changePanel(new LoginPanel());
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
