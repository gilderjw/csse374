package edu.rosehulman.numberguessing;

import java.util.Scanner;

public class NumberGuessingGame {
	private boolean end;
	private boolean win;
	private String user;
	
	private int high;
	private int low;
	private int mid;
	
	private Scanner scanner;
	
	private int attempt;
	
	public NumberGuessingGame() {
		this.win = false;
		this.end = false;
		scanner = new Scanner(System.in);
	}
	
	private void readUserName() {
		System.out.println("Enter your name: ");
		this.user = scanner.nextLine();
		
	}
	
	private void makePlay() {
		System.out.format("[Attempt #%d] Is your number %d? Guess [high / low / correct] ", ++this.attempt, this.mid);
		String option = scanner.nextLine();
		option = option.toLowerCase();
		if(option.startsWith("h")) {
			low = mid;
		}
		else if(option.startsWith("l")) {
			high = mid;
		}
		else {
			this.win = true;
			this.end = true;
		}
		
		int delta = high - low;
		if(delta > 0) {
			this.mid = this.low + delta / 2;
		}
		else {
			this.win = false;
			this.end = true;
		}
	}
	
	private void init() {
		System.out.println("Welcome to the Number Guessing Game!");

		this.readUserName();

		System.out.println("Howdy, " + this.user + "! Think of a number between 1-100 and I will guess it within 7 attempts.");
		System.out.println("Press return if you are ready!");
		this.scanner.nextLine();
		
		this.high = 100;
		this.low = 0;
		this.mid = (high - low) / 2;
		
		this.attempt = 0;
	}
	
	public void playGuess() {
		this.init();
		
		while(!this.isEndOfGame()) {
			this.makePlay();
		}
		this.showResult();
	}
	
	private boolean isEndOfGame() {
		return this.end;
	}
	
	private void showResult() {
		if(this.win) {
			System.out.format("Hurrah! I won, " + this.user + "!");
		}
		else {
			System.out.format("Hey, " + this.user + "! You won! Congratulations!");
		}
	}
}
