package edu.rosehulman.csse374.pizza.factories;

import edu.rosehulman.csse374.pizza.pizzas.CheesePizza;
import edu.rosehulman.csse374.pizza.pizzas.ClamPizza;
import edu.rosehulman.csse374.pizza.pizzas.PepperoniPizza;
import edu.rosehulman.csse374.pizza.pizzas.VeggiePizza;
import edu.rosehulman.csse374.pizza.pizzas.HawaiianPizza;
import edu.rosehulman.csse374.wired.core.AbstractFactory;

public abstract class AbstractPizzaFactory extends AbstractFactory {

	public AbstractPizzaFactory() {
		// Mapping selector based classes for initialization
		this.map("cheese", CheesePizza.class);
		this.map("veggie", VeggiePizza.class);
		this.map("clam", ClamPizza.class);
		this.map("pepperoni", PepperoniPizza.class);
		this.map("hawaiian", HawaiianPizza.class);
	}
	
}
