package edu.rosehulman.client.wordguessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class WordGuessingGame {
	private boolean active;
	private int winner;
	private int users;
	
	private int maxAttempts;
	private int[] userAttempts;
	
	private String plainTextWord;
	private String maskedWord;
	
	private Scanner scanner;
	
	
	public WordGuessingGame() {
		this.winner = -1;
		this.active = true;
		scanner = new Scanner(System.in);
	}
	
	private void readUsers() {
		System.out.println("Enter how many players: ");
		this.users = Integer.parseInt(scanner.nextLine());
	}
	
	
	private void retrieveWordOnline() throws IOException {
		URL url = new URL("http://randomword.setgetgo.com/get.php");
		InputStream inStream = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
		this.plainTextWord = reader.readLine().toLowerCase();
		reader.close();
	}
	
	private void codify() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.plainTextWord.charAt(0));
		Random random = new Random();
		for(int i = 1; i < this.plainTextWord.length(); ++i) {
			if(random.nextBoolean()) 
				buffer.append(this.plainTextWord.charAt(i));
			else
				buffer.append('_');
		}
		this.maskedWord = buffer.toString();
	}
	
	
	private void initialize(int maxAttempts) throws IOException {
		System.out.println("Welcome to the Word Guessing game!");
		this.readUsers();
		this.retrieveWordOnline();
		this.codify();

		this.maxAttempts = maxAttempts;
		this.userAttempts = new int[this.users];
	}

	
	private void nextTurn(int user) {
		int attempt = this.userAttempts[user];
		
		System.out.println("Here is the partly completed word: " + this.maskedWord);
		System.out.format("[User %d, Guess %d of %d]%nWhat is the word? ", user, ++attempt, this.maxAttempts);
		String option = scanner.nextLine();
		option = option.toLowerCase();
		
		this.userAttempts[user] = attempt;
		
		if(option.equals(this.plainTextWord)) {
			this.active = false;
			this.winner = user;
		}
		else {
			System.out.println("Not quite right!");
		}

		System.out.println();
	}
	
	
	public void launch(int maxAttempt) throws IOException {
		this.initialize(maxAttempt);
		int user = 0;
		int attempt = 0;
		while(this.isActive() && attempt < maxAttempt) {
			this.nextTurn(user);
			user = (user + 1) % this.users;
			++attempt;
		}
		this.showResult();
	}
	
	private boolean isActive() {
		return this.active;
	}
	
	private void showResult() {
		if(this.winner >= 0) {
			System.out.println("Well done, User" + this.winner + "!");
		}
		else {
			System.out.println("Oops, nobody won! The correct answer is: " + this.plainTextWord);
		}
	}
}
