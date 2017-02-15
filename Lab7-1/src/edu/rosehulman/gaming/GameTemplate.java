package edu.rosehulman.gaming;

import java.util.Scanner;

public abstract class GameTemplate {
	protected Scanner scan;

	public final void play(){
		this.init();
		
		while(this.isActive()) {
			this.takeTurn();
		}
		
		this.showResults();
	}
	
	protected abstract void init();
	
	protected abstract boolean isActive();
	
	protected abstract void takeTurn();
	
	protected abstract void showResults();
}
