package edu.rosehulman.client;

import java.util.Random;
import java.util.Scanner;

import edu.rosehulman.gaming.GameTemplate;

public class CoinGame extends GameTemplate {
	
	private boolean isActive;
	private boolean win;
	
	@Override
	protected void init() {
		this.isActive = true;
		this.win = false;
	}

	@Override
	protected boolean isActive() {
		return this.isActive;
	}

	@Override
	protected void takeTurn() {
		boolean heads = Math.random() < .5;
		Scanner scan = new Scanner(System.in);
				
		String s;
		do {
			System.out.println("heads or tails?");
			s = scan.next();
		} while (!s.startsWith("h") && !s.startsWith("t"));
		
		if (s.startsWith("h") && heads){
			this.win = true;
			this.isActive = false;
			return;
		}
		
		if(s.startsWith("t") && !heads){
			this.win = true;
			this.isActive = false;
			return;
		}
		
		System.out.println("Nope, you suck!");
	}

	@Override
	protected void showResults() {
		if (win) {
			System.out.println("Yay, you win.");
		} else {
			System.out.println("This should never happen");
		}
	}
}
