package problem;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationLauncherTest {

	private ApplicationLauncher launcher;
	private ExecutableFileRunner eRunner;
	private DataFileRunner dRunner;
	private HtmlFileRunner hRunner;

	private String baseDir = "./input_output";
	private String srcExeFile = this.baseDir + "/test_files/putty.exe";
	private String srcHtmlFile = this.baseDir + "/test_files/test.html";
	private String srcTxtFile = this.baseDir + "/test_files/test.txt";

	private String destExeFile = this.baseDir + "/putty.exe";

	private DirectoryEvent textEvent = new DirectoryEvent("add", Paths.get(this.srcTxtFile), null);
	private DirectoryEvent htmlEvent = new DirectoryEvent("add", Paths.get(this.srcHtmlFile), null);
	private DirectoryEvent exeEvent = new DirectoryEvent("add", Paths.get(this.srcExeFile), null);


	@Before
	public void setUp() throws Exception {
		this.launcher = new ApplicationLauncher();

		this.eRunner = new ExecutableFileRunner();
		this.dRunner = new DataFileRunner();
		this.hRunner = new HtmlFileRunner();


		this.launcher.addRunner(".exe", this.eRunner);
		this.launcher.addRunner(".txt", this.dRunner);
		this.launcher.addRunner(".html", this.hRunner);

		Files.deleteIfExists(Paths.get(this.destExeFile));

		Path dir = Paths.get(this.baseDir);

	}

	@After
	public void tearDown() throws Exception {
		Files.deleteIfExists(Paths.get(this.destExeFile));

		this.launcher.shutDown();

	}

	@Test
	public final void testLaunchDirectoryExeChanged() throws Exception {
		assertEquals(0, this.launcher.processes.get(".exe").getProcesses().size());
		this.launcher.directoryChanged(this.exeEvent);
		assertEquals(1, this.launcher.processes.get(".exe").getProcesses().size());
	}

	@Test
	public final void testLaunchDirectoryHtmlChanged() throws Exception {
		assertEquals(0, this.launcher.processes.get(".html").getProcesses().size());
		this.launcher.directoryChanged(this.htmlEvent);
		assertEquals(1, this.launcher.processes.get(".html").getProcesses().size());
	}

	@Test
	public final void testLaunchDirectoryTextChanged() throws Exception {
		assertEquals(0, this.launcher.processes.get(".txt").getProcesses().size());
		this.launcher.directoryChanged(this.textEvent);
		assertEquals(1, this.launcher.processes.get(".txt").getProcesses().size());
	}
}
