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
	private String defaultOS = "lin";

	private Configuration() {
		if(this.test) {
			this.setDefaultOS(System.getProperty("os.name").substring(0,3).toLowerCase());
		}
		//		return System.getProperty("os.name").substring(0,3).toLowerCase();	//
	}

	public String getDefaultOS() {
		return this.defaultOS;
	}

	public String getOS() {
		return this.defaultOS;
		//		if(this.test) {
		//			return this.defaultOS.toLowerCase();
		//		} else {
		//		return System.getProperty("os.name").substring(0,3).toLowerCase();
		//		}
	}

	public boolean isTest() {
		return this.test;
	}

	public void setDefaultOS(String os) {
		this.defaultOS = os;
	}

	public void setTest(boolean t) {
		this.test = t;
	}
}
