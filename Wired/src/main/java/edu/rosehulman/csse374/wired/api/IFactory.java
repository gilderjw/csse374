package edu.rosehulman.csse374.wired.api;

import edu.rosehulman.csse374.wired.core.AbstractFactory;

/**
 * Creates a concrete object of the given type based on the registered concrete subtypes (including the supplied type) 
 * of the supplied types. If the creation process requires a switch (selector), then the {@link #create(Class, String)}
 * method should supply a non-empty selector.
 * 
 * It is recommended to extend {@link AbstractFactory} rather than implementing this interface
 * from scratch.
 */
public interface IFactory {
	/**
	 * Creates an object of the supplied type and injects all of the dependencies that need to be autowired to
	 * the created object.
	 * 
	 * 
	 * @param <T> The super type. 
	 * @param clazz 
	 * 				The type of object to be created. The actual type of the created object can be a subtype 
	 * 				or the same type as the supplied type.
	 * @param selector
	 * 				A string selector for creating object in a special way. Selector based initialization is used for
	 * 				creating polymorphic objects that need to be dynamically created based on the value of the selector.
	 * 				A null or empty string can be used to ignore selector based initialization. The factory must be 
	 * 				mapped with the selector as a key to the concrete class that needs to be intialized for this to work.
	 * @return	
	 * 			A concreter object of the supplied type.
	 * @throws Exception when initialization fails.
	 */
	<T> T create(Class<T> clazz, String selector) throws Exception;
}
