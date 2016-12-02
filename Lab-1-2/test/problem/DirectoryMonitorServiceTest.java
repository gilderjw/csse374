package problem;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DirectoryMonitorServiceTest {
	private ApplicationLauncher launcher;

	DirectoryMonitorService service;
	private ExecutableFileRunner eRunner = new ExecutableFileRunner();
	private DataFileRunner dRunner = new DataFileRunner();
	private HtmlFileRunner hRunner = new HtmlFileRunner();

	private String baseDir = "./input_output";
	private String srcExeFile = this.baseDir + "/test_files/putty.exe";
	private String srcHtmlFile = this.baseDir + "/test_files/test.html";
	private String srcTxtFile = this.baseDir + "/test_files/test.txt";

	private String destExeFile = this.baseDir + "/putty.exe";
	private String destHtmlFile = this.baseDir + "/test.html";
	private String destTxtFile = this.baseDir + "/test.txt";

	@Before
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(this.destExeFile));
		Files.deleteIfExists(Paths.get(this.destTxtFile));
		Files.deleteIfExists(Paths.get(this.destHtmlFile));

		this.service = new DirectoryMonitorService(Paths.get(this.baseDir));

		this.launcher = new ApplicationLauncher();
		this.launcher.addRunner(".exe", this.eRunner);
		this.launcher.addRunner(".txt", this.dRunner);
		this.launcher.addRunner(".html", this.hRunner);

		this.service.addListener(this.launcher);

		assertEquals(false, this.service.isRunning());
		this.service.start();

		Thread.sleep(500);

		assertEquals(true, this.service.isRunning());
	}

	@After
	public void tearDown() throws Exception {
		assertEquals(true, this.service.isRunning());
		this.service.stopGracefully();
		Thread.sleep(100);
		assertEquals(false, this.service.isRunning());
	}

	@Test
	public final void testLaunchDirectoryExeChanged() throws Exception {
		assertEquals(0, this.eRunner.processes.size());
		Files.copy(Paths.get(this.srcExeFile), Paths.get(this.destExeFile));
		// this.launcher.directoryChanged(this.exeEvent);
		Thread.sleep(1000);
		assertEquals(1, this.eRunner.processes.size());
	}

	@Test
	public final void testLaunchDirectoryHtmlChanged() throws Exception {
		assertEquals(0, this.hRunner.processes.size());
		Files.copy(Paths.get(this.srcHtmlFile), Paths.get(this.destHtmlFile));
		//this.launcher.directoryChanged(this.htmlEvent);
		Thread.sleep(1000);
		assertEquals(1, this.hRunner.processes.size());
	}

	@Test
	public final void testLaunchDirectoryTextChanged() throws Exception {
		assertEquals(0, this.dRunner.processes.size());
		Files.copy(Paths.get(this.srcTxtFile), Paths.get(this.destTxtFile));
		//this.launcher.directoryChanged(this.textEvent);
		Thread.sleep(1000);
		assertEquals(1, this.dRunner.processes.size());
	}
}
