package edu.rosehulman.csse374.pizza.pizzas;

import edu.rosehulman.csse374.pizza.ingredients.Clams;
import edu.rosehulman.csse374.wired.annotations.Autowired;

public class ClamPizza extends Pizza {
	@Autowired Clams clam;
 
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(super.toString());

		if (clam != null) {
			result.append(clam);
			result.append("\n");
		}
		
		return result.toString();
	}

}
