package problem;

import java.lang.reflect.Proxy;

public class UniversalObjectFactory {
	/**
	 * Generates implementation of all getter, setters, numerical incrementers, and collection appenders 
	 * for the specified interface.
	 * 
	 * @param <T> The type of interface whose concrete class needs to be created.
	 * @param interfaze The interface to implement. See the assignment spec.
	 * @return Returns an instance of the newly created class.
	 */
	public static <T> T create(Class<T> interfaze){
		// TODO: Test first, then implement this method
		// NOTE: NO CREDIT if you couple this code to Team, Player, or Roster.
		return (T) Proxy.newProxyInstance(interfaze.getClassLoader(), new Class[]{interfaze}, new BasicInvocationHandler());
	}
}
