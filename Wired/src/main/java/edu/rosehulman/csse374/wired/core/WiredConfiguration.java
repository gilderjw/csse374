package edu.rosehulman.csse374.wired.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WiredConfiguration {
	private static volatile WiredConfiguration config;
	/**
	 * Returns a thread-safe instance of {@link WiredConfiguration} singleton.
	 */
	public static WiredConfiguration getInstance() {
		if (config == null) {
			synchronized (WiredConfiguration.class) {
				if (config == null) {
					config = new WiredConfiguration();
				}
			}
		}
		return config;
	}
	
	public static void reset() {
		config = null;
	}
	

	private List<String> exclusions;
	
	private WiredConfiguration() {
		this.exclusions = new ArrayList<>();
	}
	
	/**
	 * Prefix of the qualified name of packages or classes that should be excluded
	 * while computing super type hierarchy for resolving target object in factories or
	 * searching for fields that needs dependency injection.
	 *  
	 * @param packageOrClassPrefixes 
	 * 			Variable argument of package prefixes or classes.
	 * @see 
	 * 		{@link WiredFramework#injectDependencies(Object)}
	 * 		{@link AbstractFactory#populateSuperTypes(Class, Class)}
	 */
	public void addExclusion(String... packageOrClassPrefixes) {
		this.exclusions.addAll(Arrays.asList(packageOrClassPrefixes));
	}
	
	/**
	 * Returns the list of package and class exclusions.
	 */
	public Collection<String> getExclusions() {
		return Collections.unmodifiableCollection(exclusions);
	}
	
	/**
	 * Returns <code>true</code> if the supplied className or package is excluded from 
	 * being evaluated for dependency injection, otherwise <code>false.</code>
	 * 
	 * @param classNameOrPackage
	 * 			Fully qualified name of the class or package.
	 * 
	 * @see 
	 * 		{@link WiredFramework#injectDependencies(Object)}
	 * 		{@link AbstractFactory#populateSuperTypes(Class, Class)}
	 */
	public boolean isExcluded(String classNameOrPackage) {
		for(String exclusion: this.exclusions) {
			if(classNameOrPackage.startsWith(exclusion))
				return true;
		}
		return false;
	}
}
