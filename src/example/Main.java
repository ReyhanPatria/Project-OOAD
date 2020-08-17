package example;

import example.controller.MainController;
import example.view.LoginPanel;

public class Main {
	public Main() {
		MainController.changePanel(new LoginPanel());
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
