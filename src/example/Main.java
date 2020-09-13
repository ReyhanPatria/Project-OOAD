package example;

import example.controller.ViewController;

public class Main {
	public Main() {
		// Loads app's landing page
		ViewController.getInstance().loadFirstPage();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
