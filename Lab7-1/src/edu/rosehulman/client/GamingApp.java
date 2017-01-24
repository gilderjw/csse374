package edu.rosehulman.client;

import java.io.IOException;

import edu.rosehulman.gaming.GameFramework;

public class GamingApp {

	public static void main(String[] args) throws IOException {
		GameFramework framework = new GameFramework();
		framework.play("numberguessing");
	}
}
