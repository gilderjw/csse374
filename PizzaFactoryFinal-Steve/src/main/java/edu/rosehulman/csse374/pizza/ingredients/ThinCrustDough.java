package edu.rosehulman.csse374.pizza.ingredients;

public class ThinCrustDough extends Dough {
	private static final String doughBaking = "Bake for 25 minutes at 375";
	
	public String toString() {
		return "Thin Crust Dough";
	}
	@Override
	public String getBaking(){
		return doughBaking;
	}
}
