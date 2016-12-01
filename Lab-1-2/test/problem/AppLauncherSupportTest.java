package problem;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppLauncherSupportTest {
	//TODO: Refactor this test to match with the new implementation. 
	// The general idea is you should create a TestClass per Java class.
	
	// TODO: You need to test the addition of handler logic also

	private AppLauncher launcher;
	private String baseDir = "./input_output";
	private String srcTxtFile = baseDir + "/test_files/test.txt";
	private String destTxtFile = baseDir + "/test.txt";
	private String srcHtmlFile = baseDir + "/test_files/test.html";
	private String destHtmlFile = baseDir + "/test.html";
			
	@Before
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(destTxtFile));
		Files.deleteIfExists(Paths.get(destHtmlFile));

		Path dir = Paths.get(baseDir);
		launcher = new AppLauncher(dir);
		launcher.start();		
	}

	@After
	public void tearDown() throws Exception {
		launcher.stopGracefully();
		launcher.join();
		
		// Let's delete the copied files from the base directory
		Files.deleteIfExists(Paths.get(destTxtFile));
		Files.deleteIfExists(Paths.get(destHtmlFile));
	}

	@Test
	public final void testLaunchNotepad() throws Exception {
		int count = launcher.getApplicationsCount();
		Files.copy(Paths.get(srcTxtFile), Paths.get(destTxtFile));
		int expected = count + 1;

		// Let's give some time for the app to load
		Thread.sleep(2000);
		
		// Let's check if it worked
		int actual = launcher.getApplicationsCount();
		
		assertEquals("Problem running Notepad!", expected, actual);
	}

	@Test
	public final void testLaunchDefaultBrowser() throws Exception {
		int count = launcher.getApplicationsCount();
		Files.copy(Paths.get(srcHtmlFile), Paths.get(destHtmlFile));
		int expected = count + 1;

		// Let's give some time for the app to load
		Thread.sleep(2000);
		
		// Let's check if it worked
		int actual = launcher.getApplicationsCount();
		
		assertEquals("Problem running default browser!", expected, actual);
	}
}
