package edu.rosehulman.csse374.wired.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Factory {
	/**
	 * Concrete classes that should be initialized by this factory.
	 */
	Class<?>[] provides() default {};
}
