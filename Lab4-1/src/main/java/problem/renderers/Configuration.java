package problem.renderers;

public class Configuration {
	private static volatile Configuration config;

	public static Configuration getInstance() {
		if (config == null) {
			synchronized (Configuration.class) {
				if (config == null) {
					config = new Configuration();
				}
			}
		}
		return config;
	}
	
	private boolean test = true;
	private String defaultOS = "linux";
	
	private Configuration() {
		
	}
	
	public String getDefaultOS() {
		return defaultOS;
	}

	public boolean isTest() {
		return test;
	}
	
	public void setDefaultOS(String os) {
		defaultOS = os;
	}
	
	public void setTest(boolean t) {
		test = t;
	}

	public String getOS() {
		if(test)
			return defaultOS.toLowerCase();
		else
			return System.getProperty("os.name").toLowerCase();
	}
}
