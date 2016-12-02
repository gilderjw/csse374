package problem;

import java.io.IOException;
import java.nio.file.Path;

public class HtmlFileRunnerLinux extends ProcessRunner {

	public HtmlFileRunnerLinux(){
		this.command = "google-chrome-stable";
	}


	@Override
	void execute(Path p) {
		ProcessBuilder processBuilder = new ProcessBuilder(this.command, p.toAbsolutePath().toString());
		try {
			Process process = processBuilder.start();
			this.processes.add(process);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
