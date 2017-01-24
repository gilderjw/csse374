package edu.rosehulman.csse374.wired.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.SetMultimap;

import edu.rosehulman.csse374.wired.annotations.Factory;
import edu.rosehulman.csse374.wired.api.IFactory;
import edu.rosehulman.csse374.wired.api.IWiredFramework;

import com.google.common.collect.HashMultimap;

/**
 * <p>
 * This factory provides default mechanism for object factories to 
 * initialize objects as well as to inject dependencies for the 
 * created objects.
 * </p>
 * 
 * <p>
 * If subtypes of this class need to hook in a different way to create objects, then they 
 * can do so by overriding the implementation of the {@link #createSpecial(Class, String)}
 * method.
 * </p>
 * <p>
 * This class use {@link WiredFramework} for injecting dependencies.
 * </p>
 * 
 * <p>
 * <strong>Note that objects of all subtypes of AbstractFactory are cached in {@link WiredFramework} by default.</strong>
 * </p>
 * @see {@link WiredFramework}
 */
public abstract class AbstractFactory implements IFactory {
	private Class<?>[] provides;
	private SetMultimap<Class<?>, Class<?>> typeToSuperTypesInclusive;
	private Map<String, Class<?>> selectorToType;
	private IWiredFramework core;
	private WiredConfiguration configuration;
	
	public AbstractFactory() {
		this.core = WiredFramework.getInstance();
		this.configuration = WiredConfiguration.getInstance();
		core.saveToCache(this);
		
		this.provides = null;
		this.typeToSuperTypesInclusive = HashMultimap.create();
		this.selectorToType = new HashMap<>();
	}

	/**
	 * This is the hook for the subtypes of {@link AbstractFactory} to provides special 
	 * mechanism to create objects. This method should return <code>null</code> if the subtypes
	 * want to use the default mechanism of creating object implemented by {@link AbstractFactory}
	 * .  
	 * @param <T> The super type of the class being  created.
	 * 
	 * @param clazz	
	 * 			The type to be initialized.
	 * @param selector 
	 * 			The selector to be used for initialization.
	 * @return 
	 * 			The initialized object or null to trigger {@link AbstractFactory}'s behavior 
	 * 			of initializing objects.
	 * @throws Exception
	 */
	protected <T> T createSpecial(Class<T> clazz, String selector) throws Exception {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final <T> T create(Class<T> clazz, String selector) throws Exception {
		Object o = core.getFromCache(clazz);
		if(o != null)
			return (T)o;

		if(provides == null) {
			Factory factory = this.getClass().getDeclaredAnnotation(Factory.class);
			
			if(factory != null) {
				provides = factory.provides();
				
				// Retrieve all super types of the provided type for lookups later
				// TODO: Use fork join here
				for(Class<?> providingClass: provides) {
					this.populateSuperTypes(providingClass, providingClass);
				}
			}
		}
		
		T object = this.createSpecial(clazz, selector);
	
		if(object == null) {
			if(selector == null || selector.length() == 0)
				object = this.resolveDefault(clazz);
			else
				object = this.resolveSelector(clazz, selector);
		}

		if(object != null ) {
			if(!this.configuration.isExcluded(object.getClass().getName())) {
				core.injectDependencies(object, new Class<?>[]{this.getClass()});
			}
		}
		
		return object;
	}
	
	/**
	 * To be used by subtype to provide mapping of string selectors to concrete types for dynamically choosing 
	 * a class for initialization based on the supplied string selector.
	 * 
	 * @param selector 
	 * 			A string key
	 * @param clazz 
	 * 			Concrete class that should be initialized for the provided selector.
	 */
	protected final void map(String selector, Class<?> clazz) {
		this.selectorToType.put(selector, clazz);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T resolveDefault(Class<T> clazz) throws Exception {
		for(Class<?> providedClass: provides) {
			Set<Class<?>> providesFor = this.typeToSuperTypesInclusive.get(providedClass);

			if(providesFor.contains(clazz)) {
				Object o = core.getFromCache(providedClass);
				if(o != null)
					return (T)o;

				return (T)providedClass.newInstance();
			}
		}
		
		throw new AutojectCoreException(String.format("Cannot intialize %s. The factory for this class, %s, does not provide any concrete subtypes of the supplied type.",
				clazz.getName(), this.getClass().getName()));
	}
	
	@SuppressWarnings("unchecked")
	private <T> T resolveSelector(Class<T> clazz, String selector) throws Exception {
		Class<?> concreteType = this.selectorToType.get(selector);
		
		if(concreteType == null)
			throw new AutojectCoreException(String.format("Cannot intialize %s. The factory for this class, %s, does not provide any class for the supplied selector",
					clazz.getName(), this.getClass().getName()));
		
		if(!clazz.isAssignableFrom(concreteType))
			throw new AutojectCoreException(String.format("Cannot intialize %s. The factory for this class, %s, provide a concrete class (for the selector), %s, "
					+ "which is not a subtype of the autowired field type.",
					clazz.getName(), this.getClass().getName(), concreteType.getName()));
		
		Object o = core.getFromCache(concreteType);
		if(o != null)
			return (T)o;

		return (T)concreteType.newInstance();
	}
	
	protected void populateSuperTypes(Class<?> providedClass, Class<?> toBeMappedClass) {
		// Exclude null
		if(toBeMappedClass == null)
			return;
		
		String className = toBeMappedClass.getName();

		// Check if this class should be excluded
		if(WiredConfiguration.getInstance().isExcluded(className))
			return;
		
		// Detect and stop cycles
		Set<Class<?>> mappedClasses = this.typeToSuperTypesInclusive.get(providedClass);
		if(mappedClasses.contains(toBeMappedClass))
			return;
		
		// All good, add the map entry
		this.typeToSuperTypesInclusive.put(providedClass, toBeMappedClass);
		
		// Now process super classes and interfaces
		Class<?> superType = toBeMappedClass.getSuperclass();
		populateSuperTypes(providedClass, superType);
		
		for(Class<?> interfaceType: toBeMappedClass.getInterfaces()) {
			populateSuperTypes(providedClass, interfaceType);
		}
	}
}
