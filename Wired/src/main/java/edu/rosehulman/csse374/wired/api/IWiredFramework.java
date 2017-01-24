package edu.rosehulman.csse374.wired.api;

import edu.rosehulman.csse374.wired.annotations.Autowired;
import edu.rosehulman.csse374.wired.annotations.Provider;
import edu.rosehulman.csse374.wired.core.AbstractFactory;
import edu.rosehulman.csse374.wired.core.WiredFramework;

/**
 * <p>
 * This interface provides the core functionality of the wired framework.
 * The default implementation of the interface provides a thread-safe 
 * <strong>singleton</strong>, see the {@link WiredFramework} for further details.
 * The framework can only run one application at a time.
 * </p>
 * 
 * <p>
 * The {@link #boot(Class, String...)} method initializes the application class, inject 
 * all of the dependencies in the object hierarchy and runs the application by supplying
 * the runtime application parameters as a String variable argument array.
 * </p>
 * 
 * <p>
 * This interface also provides cache service for sharing object among various classes. The
 * subtypes of {@link AbstractFactory} are cached by default. An {@link IApplication} 
 * implementation can use {@link #saveToCache(Object)}, {@link #getFromCache(Class)}, 
 * and {@link #getOrCreateFromCache(Class)} methods for cache related functionalities.
 * </p>
 * 
 * <p>
 * Finally, this class can inject dependencies for an object dynamically. In order to specify 
 * dependencies, a class can use {@link Autowired} and {@link Provider} annotations.
 * </p>
 * 
 * @see 
 * 		{@link AbstractFactory}
 * 		{@link Autowired}
 * 		{@link Provider}
 * 		{@link IApplication}
 */
public interface IWiredFramework {
	/**
	 * Boots an application by initializing the supplied application class. 
	 * It injects all of the object dependencies specified by the {@link Autowired}
	 * annotations in the process of booting the application. 
	 * 
	 * @param appClass
	 * 			The {@link IApplication} class to be initialized.
	 * @param args
	 * 			The runtime argument received from operating system.
	 * 
	 * @throws Exception
	 */
	public void boot(Class<? extends IApplication> appClass, String... args) throws Exception;
	
	/**
	 * Saves the given object to the cache implemented as a map, that
	 * maps the concrete class to the object.
	 *  
	 * @param object The object to be cached.
	 */
	public void saveToCache(Object object);

	/**
	 * Returns the cached object based on the supplied key or null.
	 * 
	 * @param clazz The {@link Class} key for lookup.
	 */
	public Object getFromCache(Class<?> clazz);
	
	/**
	 * Returns the cached object based on the supplied key. If the object was not found,
	 * then it initializes the object using the supplied {@link Class} and caches the 
	 * newly created object before returning it.
	 * 
	 * @param clazz 
	 * 			The {@link Class} key for lookup.
	 * @param factories 
	 * 			The factories to be used for resolving dependencies
	 * @throws Exception
	 */
	public Object getOrCreateFromCache(Class<?> clazz, Class<?>[] factories) throws Exception;

	/**
	 * This method dynamically injects dependencies in the object hierarchy of the
	 * supplied object. The dependencies are supplied using the {@link Autowired} annotation.
	 * If a factory ({@link IFactory}) is to be used to resolve a dependency and to create
	 * the object, then the class of the supplied object must statically specify so in the
	 * {@link Provider} annotation.
	 * 
	 * @param component The supplied object with unresolved dependencies
	 * @throws Exception
	 */
	public void injectDependencies(Object component, Class<?>[] factoryClasses) throws Exception;
}
