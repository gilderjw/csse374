package edu.rosehulman.csse374.pizza.pizzas;

import edu.rosehulman.csse374.pizza.ingredients.Hawaiian;
import edu.rosehulman.csse374.wired.annotations.Autowired;

public class HawaiianPizza extends Pizza {
	@Autowired Hawaiian hawaiian[];

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(super.toString());
		
		if (hawaiian != null) {
			for (int i = 0; i < hawaiian.length; i++) {
				result.append(hawaiian[i]);
				if (i < hawaiian.length-1) {
					result.append(", ");
				}
			}
			result.append("\n");
		}

		return result.toString();
	}	
}