package edu.rosehulman.csse374.pizza.apps;

import edu.rosehulman.csse374.pizza.factories.PizzaMetaFactory;
import edu.rosehulman.csse374.pizza.pizzas.Pizza;
import edu.rosehulman.csse374.wired.annotations.Autowired;
import edu.rosehulman.csse374.wired.annotations.Provider;
import edu.rosehulman.csse374.wired.api.IApplication;
import edu.rosehulman.csse374.wired.api.IFactory;
import edu.rosehulman.csse374.wired.api.IWiredFramework;
import edu.rosehulman.csse374.wired.core.WiredConfiguration;
import edu.rosehulman.csse374.wired.core.WiredFramework;

@Provider(factories = PizzaMetaFactory.class)
public class NYPizzaStore implements IApplication {
	@Autowired(selector="ny")
	IFactory pizzaFactory;
	
	@Override
	public void execute(String... args) throws Exception {
		for(String pizzaSelector: args) {
			Pizza pizza = pizzaFactory.create(Pizza.class, pizzaSelector);
			
			pizza.setName(String.format("%s Style %s Pizza", "New York", pizzaSelector.toUpperCase()));
			System.out.println("--- Making a " + pizza.getName() + " ---");

			pizza.prepare();
			// The baking for NY vs Chicago now differs, but that's buried in the "dough" ingredient
			pizza.bake();
			// Added the cutting type here, because Chicago pizzas are now different - square slices
			pizza.setCutting("Cutting the pizza into diagonal slices");
			pizza.cut();
			pizza.box();
			
			System.out.println("--- " + pizza.getName() + " is Ready! ---");
			System.out.println(pizza);
		}
	}
	
	public static void main(String... args) throws Exception {
		// We will not inject any dependencies for classes in java or javax packages
		WiredConfiguration configurations = WiredConfiguration.getInstance();
		configurations.addExclusion("java", "javax");
		
		IWiredFramework framework = WiredFramework.getInstance();
		framework.boot(NYPizzaStore.class, "cheese", "clam", "pepperoni", "veggie");
	}
}
