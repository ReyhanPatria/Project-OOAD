package example;

import example.controller.MainController;
import example.view.FirstPage;

public class Main {
	public Main() {
		MainController.changePanel(new FirstPage());
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
