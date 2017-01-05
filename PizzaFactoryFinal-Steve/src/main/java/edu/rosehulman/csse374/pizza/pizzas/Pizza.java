package edu.rosehulman.csse374.pizza.pizzas;

import edu.rosehulman.csse374.pizza.ingredients.Cheese;
import edu.rosehulman.csse374.pizza.ingredients.Dough;
import edu.rosehulman.csse374.pizza.ingredients.Sauce;
import edu.rosehulman.csse374.wired.annotations.Autowired;

public abstract class Pizza {
	String name;
	String cutting; // made variable so Chicago could be square vs diagonal slices

	@Autowired Dough dough;
	@Autowired Sauce sauce;
	@Autowired Cheese cheese;

	// Some of the fields have been moved to subclasses

	// Changed from abstract factory shown in the book, to provide default implementation.
	// Pizzas are prepared by factories through dependency injection
	public void prepare() {
		System.out.println("Preparing " + name);
	}
	
// The time to bake REALLY depends on the dough type, not on the store location:
	public void bake() {
		System.out.println(dough.getBaking());
	}
	
// How they are cut, on the other hand, depends on NY vs Chicago, and is set in those stores:
	public void cut() {
		System.out.println(this.cutting);
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// Added getter and setter for cutting, used by Stores because it varies by location
	
	public void setCutting(String cutting) {
		this.cutting = cutting;
	}

	public String getCutting() {
		return cutting;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("---- " + name + " ----\n");
		if (dough != null) {
			result.append(dough);
			result.append("\n");
		}
		if (sauce != null) {
			result.append(sauce);
			result.append("\n");
		}
		if (cheese != null) {
			result.append(cheese);
			result.append("\n");
		}
		return result.toString();
	}
}
