package edu.rosehulman.csse374.pizza.ingredients;

public class ThickCrustDough extends Dough {
	private static final String doughBaking = "Bake for 35 minutes at 350";
	
	public String toString() {
		return "ThickCrust style extra thick crust dough";
	}

	@Override
	public String getBaking(){
		return doughBaking;
	}
}
