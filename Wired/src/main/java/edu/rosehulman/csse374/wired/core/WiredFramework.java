package edu.rosehulman.csse374.wired.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rosehulman.csse374.wired.annotations.Autowired;
import edu.rosehulman.csse374.wired.annotations.Provider;
import edu.rosehulman.csse374.wired.api.IApplication;
import edu.rosehulman.csse374.wired.api.IFactory;
import edu.rosehulman.csse374.wired.api.IWiredFramework;


/**
 * This class implements the {@link IWiredFramework} and provides default
 * implementations for all interface methods.
 * 
 * It is implemented as a thread-safe singleton. To get an instance, use
 * {@link #getInstance()}. To reset the framework for a new application, 
 * use {@link #reset()}. The framework can only run one {@link IApplication}
 * at a time.
 * 
 * @see {@link IWiredFramework}
 */
public class WiredFramework implements IWiredFramework {
	private volatile static WiredFramework core;
	 
	/**
	 * Returns a thread-safe instance of {@link IWiredFramework} singleton.
	 */
	public static IWiredFramework getInstance() {
		if (core == null) {
			synchronized (WiredFramework.class) {
				if (core == null) {
					core = new WiredFramework();
				}
			}
		}
		return core;
	}
	
	/**
	 * Resets the core for booting a new application.
	 */
	public static void reset() {
		core = null;
	}
	
	private IApplication app;
	private Map<Class<?>, Object> classToObjectCache;
	private WiredConfiguration configurations;

	
	private WiredFramework() {
		this.classToObjectCache = new HashMap<>();
		this.configurations = WiredConfiguration.getInstance();
	}
	
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
	public void boot(Class<? extends IApplication> appClass, String... args) throws Exception {
		this.app = appClass.newInstance();
		
		// Inject dependencies of the application object
		injectDependencies(this.app, null);
		
		// Run the application
		this.app.execute(args);
	}
	
	/**
	 * Saves the given object to the cache implemented as a map, that
	 * maps the concrete class to the object.
	 *  
	 * @param object The object to be cached.
	 */
	public void saveToCache(Object object) {
		this.classToObjectCache.put(object.getClass(), object);
	}
	
	/**
	 * Returns the cached object based on the supplied key or null.
	 * 
	 * @param clazz The {@link Class} key for lookup.
	 */
	public Object getFromCache(Class<?> clazz) {
		return this.classToObjectCache.get(clazz);
	}
	
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
	public Object getOrCreateFromCache(Class<?> clazz, Class<?>[] factories) throws Exception {
		Object object = classToObjectCache.get(clazz);
		if(object == null) {
			object = clazz.newInstance();
			this.injectDependencies(object, factories);
		}
		return object;
	}
	
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
	public void injectDependencies(Object component, Class<?>[] factoryClasses) throws Exception {
		Class<?> clazz = component.getClass();

		if(factoryClasses == null || factoryClasses.length == 0) {
			Provider provider = clazz.getAnnotation(Provider.class);
			
			if(provider == null) {
				factoryClasses = new Class<?>[]{ DefaultFactory.class };
			}
			else {
				factoryClasses = provider.factories();
			}
		}

		// Get all (inherited + declared) injectable private, protected, default, and public fields of this class
		List<Field> fieldList = new ArrayList<>();
		this.populateAllInjectableFields(clazz, fieldList);

		
		for(Field field: fieldList) {
			Autowired autowired = field.getAnnotation(Autowired.class);
			
			// Suppress Java access control, to allow us to set a value for private/protected/default fields
			field.setAccessible(true);
			
			Class<?> fieldClass = field.getType();
			Object fieldObject = null;
			
			for(Class<?> factoryClass: factoryClasses) {
				IFactory factory = (IFactory)getOrCreateFromCache(factoryClass, null);
				
				try {
					fieldObject = factory.create(fieldClass, autowired.selector());
				}
				catch(Exception e){}
				
				if(fieldObject != null)
					break;
			}

			if(fieldObject != null)
				field.set(component, fieldObject);
			else
				throw new AutojectCoreException(String.format("Cannot inject dependency for the field: [%s]. "
						+ "The factories for this containing class, %s, were not able to intialize the fields type.",
						field.toString(), clazz.getName()));
		}
	}
	
	protected void populateAllInjectableFields(Class<?> clazz, List<Field> fieldList) {
		if(clazz == null)
			return;

		String className = clazz.getName();
		if(configurations.isExcluded(className))
				return;
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field: fields) {
			Autowired autowired = field.getAnnotation(Autowired.class); 
			if(autowired != null)
				fieldList.add(field);
		}
		
		populateAllInjectableFields(clazz.getSuperclass(), fieldList);
	}
}
