package problem;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExecutableFileRunnerTest {
	//TODO: Refactor this test to match with the new implementation.
	// The general idea is you should create a TestClass per Java class.

	// TODO: You need to test the addition of handler logic also

	private ProcessRunner runner;
	private String baseDir = "./input_output";
	private String srcExeFile = this.baseDir + "/test_files/putty.exe";
	private String destExeFile = this.baseDir + "/putty.exe";


	@Before
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(this.destExeFile));

		Path dir = Paths.get(this.baseDir);
		this.runner = new ExecutableFileRunner();
	}

	@After
	public void tearDown() throws Exception {
		Files.deleteIfExists(Paths.get(this.destExeFile));
		for(Process p: this.runner.getProcesses()){
			p.destroy();
		}
	}

	@Test
	public final void testLaunchPutty() throws Exception {
		assertEquals(0, this.runner.getProcesses().size());

		this.runner.execute(Paths.get(this.srcExeFile));

		assertEquals(1, this.runner.getProcesses().size());
	}

}
