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

// Added Chicago Pizza Store -- by Steve

@Provider(factories = PizzaMetaFactory.class)
public class ChicagoPizzaStore implements IApplication {
	@Autowired(selector="chicago")
	IFactory pizzaFactory;
	
	@Override
	public void execute(String... args) throws Exception {
		for(String pizzaSelector: args) {
			Pizza pizza = pizzaFactory.create(Pizza.class, pizzaSelector);
			
			pizza.setName(String.format("%s Style %s Pizza", "Chicago", pizzaSelector.toUpperCase()));
			System.out.println("--- Making a " + pizza.getName() + " ---");

			pizza.prepare();
			// The baking for NY vs Chicago now differs, but that's buried in the "dough" ingredient
			pizza.bake();
			// Made the change suggested in the book -- Chicago pizzas have square slices
			pizza.setCutting("Cutting the pizza into square slices");
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
		
		// Added a second complex type besides "veggie", namely "hawaiian", and tried it out here.
		// See ChicagoPizzaFactory for details.
		IWiredFramework framework = WiredFramework.getInstance();
		framework.boot(ChicagoPizzaStore.class, "cheese", "clam", "pepperoni", "veggie", "hawaiian");
	}
}
