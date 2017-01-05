package edu.rosehulman.csse374.pizza.factories;

import edu.rosehulman.csse374.pizza.ingredients.FreshClams;
import edu.rosehulman.csse374.pizza.ingredients.Garlic;
import edu.rosehulman.csse374.pizza.ingredients.MarinaraSauce;
import edu.rosehulman.csse374.pizza.ingredients.Mushroom;
import edu.rosehulman.csse374.pizza.ingredients.Onion;
import edu.rosehulman.csse374.pizza.ingredients.RedPepper;
import edu.rosehulman.csse374.pizza.ingredients.ReggianoCheese;
import edu.rosehulman.csse374.pizza.ingredients.SlicedPepperoni;
import edu.rosehulman.csse374.pizza.ingredients.ThinCrustDough;
import edu.rosehulman.csse374.pizza.ingredients.Veggies;
import edu.rosehulman.csse374.wired.annotations.Factory;

@Factory(provides = {
		ThinCrustDough.class,
		MarinaraSauce.class,
		ReggianoCheese.class,
		SlicedPepperoni.class,
		FreshClams.class
})
public class NYPizzaFactory extends AbstractPizzaFactory {
	public NYPizzaFactory() {
		// We have mapped pizza types using selector in AbstractPizzaFactory
		// See its constructor
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createSpecial(Class<T> clazz, String selector) throws Exception {
		if(Veggies[].class.isAssignableFrom(clazz)) {
			Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
			return (T)veggies;
		}
		
		return null;
	}
}
