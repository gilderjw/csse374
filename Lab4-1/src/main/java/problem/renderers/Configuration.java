package problem.renderers;

import java.util.Scanner;

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
			System.out.println("Which os are you using?");
			Scanner scan = new Scanner(System.in);
			this.defaultOS = scan.nextLine().substring(0, 3).toLowerCase();
			scan.close();
		}
	}

	public String getDefaultOS() {
		return this.defaultOS;
	}

	public String getOS() {
		if(this.test) {
			return this.defaultOS.toLowerCase();
		} else {
			return System.getProperty("os.name").substring(0,3).toLowerCase();
		}
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
