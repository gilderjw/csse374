package problem;

import java.io.IOException;
import java.nio.file.Paths;

public class AppLauncherApplication {



	static String baseDir = "./input_output";

	static String srcExeFile = baseDir + "/test_files/putty.exe";
	static String srcHtmlFile = baseDir + "/test_files/test.html";
	static String srcTxtFile = baseDir + "/test_files/test.txt";
	static String destExeFile = baseDir + "/putty.exe";

	static DirectoryEvent textEvent = new DirectoryEvent("add", Paths.get(srcTxtFile), null);
	static DirectoryEvent htmlEvent = new DirectoryEvent("add", Paths.get(srcHtmlFile), null);
	static DirectoryEvent exeEvent = new DirectoryEvent("add", Paths.get(srcExeFile), null);

	public static void main(String[] args) throws IOException {
		//create monitor service
		IDirectoryMonitorService service = new DirectoryMonitorService(Paths.get(baseDir));

		//create directory listeners
		IApplicationLauncher launcher =  new ApplicationLauncher();
		IDirectoryListener logger = new DirectoryChangeLogger();

		//create individual process runners
		ProcessRunner eRunner = new ExecutableFileRunner();
		ProcessRunner dRunner =  new DataFileRunner();
		ProcessRunner hRunner = new HtmlFileRunner();

		// subscribe the process runners to the application launcher
		launcher.addRunner(".exe", eRunner);
		launcher.addRunner(".txt", dRunner);
		launcher.addRunner(".html", hRunner);

		// subscribe the application launcher and logger to the directory monitoring service
		service.addListener(launcher);
		service.addListener(logger);

		service.start();


	}
}
