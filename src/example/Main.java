package example;

import example.controller.FrameController;
import example.view.FirstPage;

public class Main {
	public Main() {
		FrameController.changePanel(new FirstPage());
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
