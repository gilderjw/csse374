package edu.rosehulman.csse374.pizza.ingredients;

public abstract class Dough {
	private static final String doughBaking = "Unknown";

	public abstract String toString();

	public String getBaking() {
		return doughBaking;
	}
}
