package edu.rosehulman.csse374.pizza.factories;

import edu.rosehulman.csse374.wired.core.AbstractFactory;

public class PizzaMetaFactory extends AbstractFactory {

	public PizzaMetaFactory() {
		super();
		this.map("ny", NYPizzaFactory.class);
		this.map("chicago", ChicagoPizzaFactory.class);
	}
}
