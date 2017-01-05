package edu.rosehulman.csse374.pizza.apps;

import java.util.Scanner;

import edu.rosehulman.csse374.pizza.factories.PizzaMetaFactory;
import edu.rosehulman.csse374.pizza.pizzas.Pizza;
import edu.rosehulman.csse374.wired.annotations.Autowired;
import edu.rosehulman.csse374.wired.api.IApplication;
import edu.rosehulman.csse374.wired.api.IFactory;
import edu.rosehulman.csse374.wired.api.IWiredFramework;
import edu.rosehulman.csse374.wired.core.WiredConfiguration;
import edu.rosehulman.csse374.wired.core.WiredFramework;

// Notice there is no provider here
public class DynamicPizzaStore implements IApplication {

	// And the autowiring still works, why?
	@Autowired 
	PizzaMetaFactory pizzaMetaFactory;
	
	@Override
	public void execute(String... args) throws Exception {
		System.out.println("Welcome to the Wired (not Weird) Pizza Store!");
		Scanner scanner = new Scanner(System.in);
		while(this.takeOrder(scanner));
		scanner.close();
		System.out.println("\n=================================================\n");
		System.out.println("Thanks for shopping! Enjoy the wired food! ;-)");
	}
	
	private boolean takeOrder(Scanner scanner) throws Exception {
		System.out.println("\n=================================================\n");
		
		System.out.println("Please select a pizza family: [ny / chicago] ");
		String pizzaFamily = scanner.nextLine().trim().toLowerCase();
		
		System.out.println("Please select a type within the family: [cheese /clam / pepperoni / veggie] ");
		String pizzaType = scanner.nextLine().trim().toLowerCase();
		
		IFactory pizzaFactory = pizzaMetaFactory.create(IFactory.class, pizzaFamily);

		Pizza pizza = pizzaFactory.create(Pizza.class, pizzaType);
		pizza.setName(String.format("%s Style %s Pizza", pizzaFamily.toUpperCase(), pizzaType.toUpperCase()));

		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		System.out.println("--- " + pizza.getName() + " is Ready! ---");
		System.out.println(pizza);

		System.out.println("Would you like to make another order? [y/n]? ");
		String next = scanner.nextLine().trim().toLowerCase();
		
		if(next.startsWith("y"))
			return true;
		
		return false;
	}
	
	
	public static void main(String... args) throws Exception {
		// We will not inject any dependencies for classes in java or javax packages
		WiredConfiguration configurations = WiredConfiguration.getInstance();
		configurations.addExclusion("java", "javax");
		
		IWiredFramework core = WiredFramework.getInstance();
		core.boot(DynamicPizzaStore.class);
	}
}
