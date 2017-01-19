package headfirst.factory.pizzaaf;

public class PrintDecorator extends PizzaIngredientFactoryDecorator{

	private String VeggiesToString(Veggies[] v){
		String s = "";
		for(Veggies vl : v){
			s = s + vl.toString() + ", ";
		}
		return s.substring(0, s.length() - 2);
	}
	
	public PrintDecorator(PizzaIngredientFactory inner) {
		super(inner);
		this.setDoughCommand(() -> System.out.println("Prepared " + this.d.toString() + " with robot"));
		this.setSauceCommand(() -> System.out.println("Prepared " + this.s.toString() + " with robot"));
		this.setCheeseCommand(() -> System.out.println("Prepared " + this.c.toString() + " with robot"));
		this.setVeggieCommand(() -> System.out.println("Prepared " + this.VeggiesToString(this.v) +" with robot"));
		this.setPepperoniCommand(() -> System.out.println("Prepared " + this.p.toString() + " with robot"));
		this.setClamCommand(() -> System.out.println("Prepared " + this.clam.toString() + " with robot"));
	}	
}
