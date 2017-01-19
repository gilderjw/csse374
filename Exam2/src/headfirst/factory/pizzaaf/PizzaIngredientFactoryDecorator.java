package headfirst.factory.pizzaaf;

public abstract class PizzaIngredientFactoryDecorator implements PizzaIngredientFactory {
	private PizzaIngredientFactory inner;
	private Runnable doughCommand, sauceCommand, cheeseCommand, veggieCommand, pepperoniCommand, clamCommand;
	public Dough d;
	public Sauce s;
	public Cheese c;
	public Veggies[] v;
	public Pepperoni p;
	public Clams clam;
	
	public PizzaIngredientFactoryDecorator(PizzaIngredientFactory inner) {
		this.inner = inner;
	}
	
	public void setDoughCommand(Runnable r){
		this.doughCommand = r;
	}
	
	public void setSauceCommand(Runnable r){
		this.sauceCommand = r;
	}
	
	public void setCheeseCommand(Runnable r) {
		this.cheeseCommand = r;
	}
	
	public void setVeggieCommand(Runnable r) {
		this.veggieCommand = r;
	}
	
	public void setPepperoniCommand(Runnable r) {
		this.pepperoniCommand = r;
	}
	
	public void setClamCommand(Runnable r) {
		this.clamCommand = r;
	}
	
	public Dough createDough() {
		this.d = this.inner.createDough();
		this.doughCommand.run();
		return this.d;
	}
	
	public Sauce createSauce() {
		this.s = this.inner.createSauce();
		this.sauceCommand.run();
		return this.s;
		
	}
	
	public Cheese createCheese() {
		this.c = this.inner.createCheese();
		this.cheeseCommand.run();
		return this.c;		
	}
	
	public Veggies[] createVeggies() {
		this.v = this.inner.createVeggies();
		this.veggieCommand.run();
		return this.v;		
	}

	public Pepperoni createPepperoni() {
		this.p = this.inner.createPepperoni();
		this.pepperoniCommand.run();
		return this.p;		
	}
	
	public Clams createClam() {
		this.clam = this.inner.createClam();
		this.clamCommand.run();
		return this.clam;
		
	}
	
	
}
