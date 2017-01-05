package edu.rosehulman.csse374.pizza.pizzas;

import edu.rosehulman.csse374.pizza.ingredients.Pepperoni;
import edu.rosehulman.csse374.wired.annotations.Autowired;

public class PepperoniPizza extends Pizza {
	@Autowired Pepperoni pepperoni;

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(super.toString());

		if (pepperoni != null) {
			result.append(pepperoni);
			result.append("\n");
		}
		
		return result.toString();
	}
}
