package edu.rosehulman.csse374.pizza.pizzas;

import edu.rosehulman.csse374.pizza.ingredients.Veggies;
import edu.rosehulman.csse374.wired.annotations.Autowired;

public class VeggiePizza extends Pizza {
	@Autowired Veggies veggies[];

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(super.toString());
		
		if (veggies != null) {
			for (int i = 0; i < veggies.length; i++) {
				result.append(veggies[i]);
				if (i < veggies.length-1) {
					result.append(", ");
				}
			}
			result.append("\n");
		}

		return result.toString();
	}	
}
