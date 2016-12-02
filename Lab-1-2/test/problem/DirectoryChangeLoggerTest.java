package problem;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Test;

public class DirectoryChangeLoggerTest {

	private String baseDir = "./input_output";


	@Test
	public void testDirectoryChanged(){
		DirectoryChangeLogger logger = new DirectoryChangeLogger();
		assertEquals(0, logger.getLogged());
		DirectoryEvent event = new DirectoryEvent("FILE_TEST", Paths.get(this.baseDir), null);
		logger.directoryChanged(event);
		assertEquals(1, logger.getLogged());
	}
}
