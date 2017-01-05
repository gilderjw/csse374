package edu.rosehulman.csse374.pizza.factories;

import edu.rosehulman.csse374.pizza.ingredients.BlackOlives;
import edu.rosehulman.csse374.pizza.ingredients.DicedHam;
import edu.rosehulman.csse374.pizza.ingredients.Eggplant;
import edu.rosehulman.csse374.pizza.ingredients.FrozenClams;
import edu.rosehulman.csse374.pizza.ingredients.MozzarellaCheese;
import edu.rosehulman.csse374.pizza.ingredients.Onion;
import edu.rosehulman.csse374.pizza.ingredients.Pineapple;
import edu.rosehulman.csse374.pizza.ingredients.PlumTomatoSauce;
import edu.rosehulman.csse374.pizza.ingredients.RedPepper;
import edu.rosehulman.csse374.pizza.ingredients.SlicedPepperoni;
import edu.rosehulman.csse374.pizza.ingredients.Spinach;
import edu.rosehulman.csse374.pizza.ingredients.ThickCrustDough;
import edu.rosehulman.csse374.pizza.ingredients.Veggies;
import edu.rosehulman.csse374.pizza.ingredients.Hawaiian;
import edu.rosehulman.csse374.wired.annotations.Factory;

// Chicago pizzas all use these special ingredients, which are utilized thanks to autoWiring:
@Factory(provides = {
		ThickCrustDough.class,
		PlumTomatoSauce.class,
		MozzarellaCheese.class,
		SlicedPepperoni.class,
		FrozenClams.class
})
public class ChicagoPizzaFactory extends AbstractPizzaFactory {

	public ChicagoPizzaFactory() {
		// We have mapped pizza types using selector in AbstractPizzaFactory
		// See its constructor
		super();
	}

	// These are special types for Chicago, but needed arrays to describe them -- this takes
	// the "createSpecial" method of autoWiring, to make that happen:
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T createSpecial(Class<T> clazz, String selector) throws Exception {
		if(Veggies[].class.isAssignableFrom(clazz)) {
			Veggies veggies[] = { new BlackOlives(), new Spinach(), new Eggplant() };
			return (T)veggies;
		}
		// Added Hawaiian pizza, which has onions, diced ham, pineapple, and red pepper.
		// So, this just continues as another structure, in the "createSpecial" method.
		if(Hawaiian[].class.isAssignableFrom(clazz)) {
				Hawaiian hawaiian[] = { new Onion(), new DicedHam(), new Pineapple(), new RedPepper() };
				return (T)hawaiian;
		}
		
		return null;
	}

}
