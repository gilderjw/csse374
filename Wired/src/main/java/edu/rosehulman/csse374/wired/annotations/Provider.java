package edu.rosehulman.csse374.wired.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import edu.rosehulman.csse374.wired.api.IFactory;
import edu.rosehulman.csse374.wired.core.DefaultFactory;
/**
 * <p>
 * This annotation is used on a concrete class to supply a factory
 * as a provider for resolving dependency and creating dependent objects.
 * </p>
 * 
 * <p>
 * It is used together with {@link Autowired}, an annotation used on field for
 * dependency injection. The supplied {@link #factories()} will resolve and
 * initialize the object of the field type.
 * </p>
 * 
 * <p>
 * If the annotation does not supply a factory then the {@link DefaultFactory} is used
 * for instantiating auto-wired fields.
 * </p>
 * 
 * <strong>Note:</strong> <br>
 * <ol>
 * <li>If any two factories support creating object of the same type, then the first factory
 * in the supplied list of factories will be responsible for creating the concrete object.</li>
 * <li>This annotation is not inheritable and using it on interfaces or abstract types has
 * no effect.</li>
 * </ol>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Provider {
	/**
	 * Returns a subtype of {@link IFactory} to be used for resolving and initializing
	 * objects for {@link Autowired} fields in the classes that are marked as component.
	 */
	Class<? extends IFactory>[] factories() default {DefaultFactory.class};
}
