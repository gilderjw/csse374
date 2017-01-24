package edu.rosehulman.csse374.wired.core;

import edu.rosehulman.csse374.wired.annotations.Factory;

/**
 * Default factory does not provide any concrete types for 
 * injection. It uses the default constructor of the supplied
 * class in the argument of the {@link #create(Class)} method
 * to return a new object of the supplied class.
 */
@Factory(provides = {})
public class DefaultFactory extends AbstractFactory {
	
	@Override
	protected <T> T createSpecial(Class<T> clazz, String selector) throws Exception {
		return clazz.newInstance();
	}
	
}
