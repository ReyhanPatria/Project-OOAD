package example;

import example.controller.ViewController;

public class Main {
	public Main() {
		ViewController.getInstance().loadFirstPage();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
