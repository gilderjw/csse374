package problem;

import java.util.Map;

/**
 * This class provides utility methods to run the application
 * in either test or release mode.
 *
 * In test mode, user can supply the default operating system
 * to get corresponding look and feel.
 *
 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 */
public class Configuration {

	static Map<String, IRendererFactory> osToFactory;

	public static final String WINDOWS = "windows";

	public static final String LINUX = "ubuntu";

	public static final String OSX = "osx";

	private static boolean test = false;
	private static String defaultOS = LINUX;


	//	static String getOS(){
	//		return null;
	//	}
	public static String getOS() {
		return defaultOS;
	}

	static IRendererFactory getRendererFactory(){
		return osToFactory.get(defaultOS);
	}

	public static void setDefaultOS(String os) {
		defaultOS = os;
	}

	public static void setTestMode(boolean mode) {
		test = mode;
	}


}
